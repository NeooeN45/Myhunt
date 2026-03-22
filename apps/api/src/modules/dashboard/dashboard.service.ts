import { Injectable } from '@nestjs/common';
import {
  dashboardSnapshot,
  demoIndividuals,
  demoObservations,
  demoZones,
  mapLayers,
  pendingSyncChanges,
} from '@cynetrace/contracts';

@Injectable()
export class DashboardService {
  getSnapshot() {
    return {
      ...dashboardSnapshot,
      lastRefreshAt: new Date().toISOString(),
      observationCount: demoObservations.length,
      importantIndividuals: demoIndividuals.filter(
        (item) => item.currentImportance === 'important',
      ).length,
      activeLayers: mapLayers.length,
      zones: demoZones.length,
      syncQueue: pendingSyncChanges,
    };
  }
}
