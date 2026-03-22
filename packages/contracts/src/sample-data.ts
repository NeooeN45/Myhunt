import type {
  DashboardSnapshot,
  GpsTrack,
  Individual,
  MapLayerDescriptor,
  Observation,
  PopulationSnapshot,
  SpeciesDescriptor,
  SyncChange,
  SyncPullResponse,
  Zone,
} from './domain';

export const speciesCatalog: SpeciesDescriptor[] = [
  {
    id: 'cerf',
    commonName: 'Cerf',
    latinName: 'Cervus elaphus',
    category: 'gibier',
  },
  {
    id: 'biche',
    commonName: 'Biche',
    latinName: 'Cervus elaphus',
    category: 'gibier',
  },
  {
    id: 'chevreuil',
    commonName: 'Chevreuil',
    latinName: 'Capreolus capreolus',
    category: 'gibier',
  },
  {
    id: 'sanglier',
    commonName: 'Sanglier',
    latinName: 'Sus scrofa',
    category: 'gibier',
  },
  {
    id: 'renard',
    commonName: 'Renard',
    latinName: 'Vulpes vulpes',
    category: 'gibier',
  },
  {
    id: 'loup',
    commonName: 'Loup',
    latinName: 'Canis lupus',
    category: 'remarquable',
    protectedLabel: 'espece_sensible',
  },
];

export const mapLayers: MapLayerDescriptor[] = [
  {
    id: 'mapbox-standard',
    name: 'Mapbox Standard',
    family: 'basemap',
    provider: 'mapbox',
    sourceType: 'vector',
    supportsOffline: true,
    supports3d: true,
    attribution: 'Mapbox',
  },
  {
    id: 'ign-plan',
    name: 'Plan IGN',
    family: 'overlay',
    provider: 'ign',
    sourceType: 'raster',
    supportsOffline: true,
    supports3d: false,
    attribution: 'IGN',
  },
  {
    id: 'ign-ortho',
    name: 'Orthophotos IGN',
    family: 'overlay',
    provider: 'ign',
    sourceType: 'raster',
    supportsOffline: true,
    supports3d: false,
    attribution: 'IGN',
  },
  {
    id: 'ign-cadastre',
    name: 'Cadastre',
    family: 'overlay',
    provider: 'ign',
    sourceType: 'vector',
    supportsOffline: false,
    supports3d: false,
    attribution: 'IGN',
  },
  {
    id: 'presence-heatmap',
    name: 'Indice de presence',
    family: 'analysis',
    provider: 'cynetrace',
    sourceType: 'geojson',
    supportsOffline: true,
    supports3d: false,
    attribution: 'CyneTrace',
  },
];

export const demoZones: Zone[] = [
  {
    id: 'zone-foret-haute',
    parentZoneId: null,
    name: 'Foret Haute',
    kind: 'reserve_chasse',
    seasonYear: 2026,
    createdBy: 'owner_admin',
    syncStatus: 'synced',
    revision: {
      id: 'zone-rev-1',
      zoneId: 'zone-foret-haute',
      effectiveFrom: '2026-01-01T00:00:00Z',
      effectiveTo: null,
      areaHectares: 486.3,
      centroid: [2.545, 46.112],
      geometry: {
        type: 'Polygon',
        coordinates: [
          [
            [2.53, 46.11],
            [2.56, 46.11],
            [2.57, 46.125],
            [2.54, 46.132],
            [2.53, 46.11],
          ],
        ],
      },
    },
  },
  {
    id: 'zone-combe-cerf',
    parentZoneId: 'zone-foret-haute',
    name: 'Combe du Cerf',
    kind: 'zt_onf',
    seasonYear: 2026,
    createdBy: 'owner_admin',
    syncStatus: 'pending_push',
    revision: {
      id: 'zone-rev-2',
      zoneId: 'zone-combe-cerf',
      effectiveFrom: '2026-02-15T00:00:00Z',
      effectiveTo: null,
      areaHectares: 78.5,
      centroid: [2.551, 46.119],
      geometry: {
        type: 'Polygon',
        coordinates: [
          [
            [2.545, 46.116],
            [2.558, 46.116],
            [2.559, 46.122],
            [2.547, 46.124],
            [2.545, 46.116],
          ],
        ],
      },
    },
  },
];

export const populationSnapshots: PopulationSnapshot[] = [
  {
    id: 'pop-cerf-2026',
    zoneId: 'zone-foret-haute',
    seasonYear: 2026,
    speciesId: 'cerf',
    estimatedCount: 24,
    confidence: 'moyenne',
  },
  {
    id: 'pop-sanglier-2026',
    zoneId: 'zone-foret-haute',
    seasonYear: 2026,
    speciesId: 'sanglier',
    estimatedCount: 31,
    confidence: 'forte',
  },
];

export const demoIndividuals: Individual[] = [
  {
    id: 'indiv-cerf-9c',
    speciesId: 'cerf',
    displayName: 'Cerf 9C',
    sex: 'male',
    currentImportance: 'important',
    createdBy: 'owner_admin',
    syncStatus: 'synced',
    yearProfile: {
      id: 'profile-cerf-9c-2026',
      individualId: 'indiv-cerf-9c',
      seasonYear: 2026,
      importanceLevel: 'important',
      presenceWeight: 3,
      notes: 'Sujet dominant suivi depuis 2024.',
    },
  },
  {
    id: 'indiv-chev-2b',
    speciesId: 'chevreuil',
    displayName: 'Brocard 2B',
    sex: 'male',
    currentImportance: 'interessant',
    createdBy: 'member',
    syncStatus: 'pending_push',
    yearProfile: {
      id: 'profile-chev-2b-2026',
      individualId: 'indiv-chev-2b',
      seasonYear: 2026,
      importanceLevel: 'interessant',
      presenceWeight: 2,
      notes: 'Trophee en developpement.',
    },
  },
];

export const demoObservations: Observation[] = [
  {
    id: 'obs-001',
    zoneId: 'zone-combe-cerf',
    individualId: 'indiv-cerf-9c',
    speciesId: 'cerf',
    seasonYear: 2026,
    observedAt: '2026-03-12T06:14:00Z',
    createdBy: 'member',
    syncStatus: 'synced',
    kind: 'observation',
    weatherLabel: 'froid sec, vent faible',
    gpsAccuracyMeters: 4.2,
    comment: 'Entendu au brame, deplacement vers le point d eau.',
    geometry: {
      type: 'Point',
      coordinates: [2.5538, 46.1203],
    },
  },
  {
    id: 'obs-002',
    zoneId: 'zone-combe-cerf',
    individualId: null,
    speciesId: 'sanglier',
    seasonYear: 2026,
    observedAt: '2026-03-13T21:32:00Z',
    createdBy: 'member',
    syncStatus: 'pending_push',
    kind: 'trace',
    weatherLabel: 'humide, leger crachin',
    gpsAccuracyMeters: 6.8,
    comment: 'Traces multiples en lisiere, passage repete.',
    geometry: {
      type: 'Point',
      coordinates: [2.5486, 46.1182],
    },
  },
];

export const demoTracks: GpsTrack[] = [
  {
    id: 'track-001',
    seasonYear: 2026,
    observedAt: '2026-03-14T05:30:00Z',
    createdBy: 'member',
    syncStatus: 'synced',
    totalDistanceKm: 4.7,
    dominantSpeciesId: 'cerf',
    geometry: {
      type: 'LineString',
      coordinates: [
        [2.5452, 46.116],
        [2.5495, 46.1188],
        [2.5521, 46.1212],
        [2.556, 46.1238],
      ],
    },
  },
];

export const pendingSyncChanges: SyncChange[] = [
  {
    id: 'sync-change-1',
    entityName: 'zone_revision',
    entityId: 'zone-rev-2',
    operation: 'upsert',
    deviceId: 'phone-forest-alpha',
    changedAt: '2026-03-21T16:10:00Z',
    syncStatus: 'pending_push',
  },
  {
    id: 'sync-change-2',
    entityName: 'observation',
    entityId: 'obs-002',
    operation: 'upsert',
    deviceId: 'phone-forest-alpha',
    changedAt: '2026-03-21T16:12:00Z',
    syncStatus: 'pending_push',
  },
];

export const dashboardSnapshot: DashboardSnapshot = {
  activeUsers: 4,
  pendingUploads: 18,
  aiReviewQueue: 7,
  conflictQueue: 2,
  trackedZones: 2,
  activeCameraTraps: 6,
};

export const sampleSyncPullResponse: SyncPullResponse = {
  serverCursor: '2026-03-22T08:15:00Z',
  acceptedChanges: pendingSyncChanges.length,
  conflicts: [
    {
      entityName: 'zone',
      entityId: 'zone-combe-cerf',
      reason: 'Revision concurrente detectee sur la geometrie 2026.',
      resolutionState: 'queued',
    },
  ],
  layers: mapLayers,
};
