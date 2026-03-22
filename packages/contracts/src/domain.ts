export const zoneKinds = [
  'reserve_chasse',
  'parc_national',
  'zt_onf',
  'custom',
] as const;

export const observationKinds = [
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
  'pose_cage_renard',
] as const;

export const syncStatuses = [
  'local_only',
  'pending_push',
  'synced',
  'conflict',
] as const;

export const individualImportanceLevels = [
  'simple',
  'interessant',
  'important',
] as const;

export const speciesCategories = ['gibier', 'remarquable'] as const;
export const userRoles = ['owner_admin', 'member'] as const;

export type ZoneKind = (typeof zoneKinds)[number];
export type ObservationKind = (typeof observationKinds)[number];
export type SyncStatus = (typeof syncStatuses)[number];
export type IndividualImportanceLevel =
  (typeof individualImportanceLevels)[number];
export type SpeciesCategory = (typeof speciesCategories)[number];
export type UserRole = (typeof userRoles)[number];

export type PolygonGeometry = {
  type: 'Polygon';
  coordinates: number[][][];
};

export type PointGeometry = {
  type: 'Point';
  coordinates: [number, number];
};

export type LineStringGeometry = {
  type: 'LineString';
  coordinates: [number, number][];
};

export interface SpeciesDescriptor {
  id: string;
  commonName: string;
  latinName: string;
  category: SpeciesCategory;
  protectedLabel?: string;
}

export interface MapLayerDescriptor {
  id: string;
  name: string;
  family: 'basemap' | 'overlay' | 'analysis';
  provider: 'mapbox' | 'ign' | 'cynetrace';
  sourceType: 'vector' | 'raster' | 'geojson' | 'raster-dem';
  supportsOffline: boolean;
  supports3d: boolean;
  attribution: string;
}

export interface ZoneRevision {
  id: string;
  zoneId: string;
  geometry: PolygonGeometry;
  effectiveFrom: string;
  effectiveTo?: string | null;
  areaHectares: number;
  centroid: [number, number];
}

export interface Zone {
  id: string;
  parentZoneId?: string | null;
  name: string;
  kind: ZoneKind;
  seasonYear: number;
  createdBy: string;
  syncStatus: SyncStatus;
  revision: ZoneRevision;
}

export interface PopulationSnapshot {
  id: string;
  zoneId: string;
  seasonYear: number;
  speciesId: string;
  estimatedCount: number;
  confidence: 'faible' | 'moyenne' | 'forte';
}

export interface IndividualYearProfile {
  id: string;
  individualId: string;
  seasonYear: number;
  importanceLevel: IndividualImportanceLevel;
  presenceWeight: number;
  notes: string;
}

export interface Individual {
  id: string;
  speciesId: string;
  displayName: string;
  sex: 'male' | 'femelle' | 'indetermine';
  currentImportance: IndividualImportanceLevel;
  createdBy: string;
  syncStatus: SyncStatus;
  yearProfile: IndividualYearProfile;
}

export interface Observation {
  id: string;
  zoneId: string;
  individualId?: string | null;
  speciesId: string;
  seasonYear: number;
  observedAt: string;
  createdBy: string;
  syncStatus: SyncStatus;
  kind: ObservationKind;
  weatherLabel: string;
  gpsAccuracyMeters: number;
  comment: string;
  geometry: PointGeometry;
}

export interface GpsTrack {
  id: string;
  seasonYear: number;
  observedAt: string;
  createdBy: string;
  syncStatus: SyncStatus;
  geometry: LineStringGeometry;
  totalDistanceKm: number;
  dominantSpeciesId?: string | null;
}

export interface SyncChange {
  id: string;
  entityName:
    | 'zone'
    | 'zone_revision'
    | 'population_snapshot'
    | 'individual'
    | 'observation'
    | 'gps_track'
    | 'media_asset';
  entityId: string;
  operation: 'upsert' | 'delete';
  deviceId: string;
  changedAt: string;
  syncStatus: SyncStatus;
}

export interface SyncConflict {
  entityName: string;
  entityId: string;
  reason: string;
  resolutionState: 'queued' | 'accepted_remote' | 'accepted_local';
}

export interface SyncPullResponse {
  serverCursor: string;
  acceptedChanges: number;
  conflicts: SyncConflict[];
  layers: MapLayerDescriptor[];
}

export interface DashboardSnapshot {
  activeUsers: number;
  pendingUploads: number;
  aiReviewQueue: number;
  conflictQueue: number;
  trackedZones: number;
  activeCameraTraps: number;
}
