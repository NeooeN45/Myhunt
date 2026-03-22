CREATE TABLE IF NOT EXISTS app_user (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  email TEXT NOT NULL UNIQUE,
  role TEXT NOT NULL CHECK (role IN ('owner_admin', 'member')),
  display_name TEXT NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS zone (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  external_id TEXT NOT NULL UNIQUE,
  parent_zone_id UUID REFERENCES zone(id),
  name TEXT NOT NULL,
  kind TEXT NOT NULL CHECK (kind IN ('reserve_chasse', 'parc_national', 'zt_onf', 'custom')),
  season_year INTEGER NOT NULL,
  created_by UUID REFERENCES app_user(id),
  sync_status TEXT NOT NULL DEFAULT 'synced',
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS zone_revision (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  zone_id UUID NOT NULL REFERENCES zone(id) ON DELETE CASCADE,
  effective_from TIMESTAMPTZ NOT NULL,
  effective_to TIMESTAMPTZ,
  geometry_wgs84 geometry(POLYGON, 4326) NOT NULL,
  area_hectares NUMERIC(12, 2) GENERATED ALWAYS AS (
    ST_Area(ST_Transform(geometry_wgs84, 2154)) / 10000.0
  ) STORED,
  centroid_wgs84 geometry(POINT, 4326) GENERATED ALWAYS AS (
    ST_Centroid(geometry_wgs84)
  ) STORED,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS zone_revision_geom_idx
  ON zone_revision
  USING GIST (geometry_wgs84);

CREATE TABLE IF NOT EXISTS individual (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  external_id TEXT NOT NULL UNIQUE,
  species_code TEXT NOT NULL,
  display_name TEXT NOT NULL,
  sex TEXT NOT NULL CHECK (sex IN ('male', 'femelle', 'indetermine')),
  current_importance TEXT NOT NULL CHECK (current_importance IN ('simple', 'interessant', 'important')),
  created_by UUID REFERENCES app_user(id),
  sync_status TEXT NOT NULL DEFAULT 'synced',
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS individual_year_profile (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  individual_id UUID NOT NULL REFERENCES individual(id) ON DELETE CASCADE,
  season_year INTEGER NOT NULL,
  importance_level TEXT NOT NULL CHECK (importance_level IN ('simple', 'interessant', 'important')),
  presence_weight SMALLINT NOT NULL CHECK (presence_weight BETWEEN 1 AND 3),
  notes TEXT NOT NULL DEFAULT '',
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  UNIQUE (individual_id, season_year)
);

CREATE TABLE IF NOT EXISTS observation (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  external_id TEXT NOT NULL UNIQUE,
  zone_id UUID NOT NULL REFERENCES zone(id),
  individual_id UUID REFERENCES individual(id),
  species_code TEXT NOT NULL,
  season_year INTEGER NOT NULL,
  observed_at TIMESTAMPTZ NOT NULL,
  kind TEXT NOT NULL CHECK (
    kind IN (
      'observation',
      'prelevement',
      'trace',
      'couche',
      'piege_photo',
      'zone_passage',
      'point_eau',
      'zone_nourriture',
      'couchette',
      'trouvaille',
      'animal_mort',
      'pose_collet_renard',
      'pose_cage_renard'
    )
  ),
  weather_label TEXT NOT NULL,
  gps_accuracy_meters NUMERIC(8, 2) NOT NULL,
  comment TEXT NOT NULL DEFAULT '',
  location_wgs84 geometry(POINT, 4326) NOT NULL,
  created_by UUID REFERENCES app_user(id),
  sync_status TEXT NOT NULL DEFAULT 'synced',
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS observation_location_idx
  ON observation
  USING GIST (location_wgs84);

CREATE TABLE IF NOT EXISTS gps_track (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  external_id TEXT NOT NULL UNIQUE,
  season_year INTEGER NOT NULL,
  observed_at TIMESTAMPTZ NOT NULL,
  dominant_species_code TEXT,
  geometry_wgs84 geometry(LINESTRING, 4326) NOT NULL,
  total_distance_km NUMERIC(10, 2) NOT NULL,
  created_by UUID REFERENCES app_user(id),
  sync_status TEXT NOT NULL DEFAULT 'synced',
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS gps_track_geometry_idx
  ON gps_track
  USING GIST (geometry_wgs84);

CREATE TABLE IF NOT EXISTS sync_change (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  entity_name TEXT NOT NULL,
  entity_id TEXT NOT NULL,
  operation TEXT NOT NULL CHECK (operation IN ('upsert', 'delete')),
  device_id TEXT NOT NULL,
  changed_at TIMESTAMPTZ NOT NULL,
  sync_status TEXT NOT NULL CHECK (sync_status IN ('local_only', 'pending_push', 'synced', 'conflict')),
  payload JSONB,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS audit_event (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  actor_id UUID REFERENCES app_user(id),
  action TEXT NOT NULL,
  entity_name TEXT NOT NULL,
  entity_id TEXT NOT NULL,
  metadata JSONB NOT NULL DEFAULT '{}'::JSONB,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);
