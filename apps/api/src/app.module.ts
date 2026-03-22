import { Module } from '@nestjs/common';
import { CatalogController } from './modules/catalog/catalog.controller';
import { CatalogService } from './modules/catalog/catalog.service';
import { DashboardController } from './modules/dashboard/dashboard.controller';
import { DashboardService } from './modules/dashboard/dashboard.service';
import { HealthController } from './modules/health/health.controller';
import { ObservationsController } from './modules/observations/observations.controller';
import { ObservationsService } from './modules/observations/observations.service';
import { SyncController } from './modules/sync/sync.controller';
import { SyncService } from './modules/sync/sync.service';
import { TerritoryController } from './modules/territory/territory.controller';
import { TerritoryService } from './modules/territory/territory.service';

@Module({
  imports: [],
  controllers: [
    HealthController,
    CatalogController,
    TerritoryController,
    ObservationsController,
    SyncController,
    DashboardController,
  ],
  providers: [
    CatalogService,
    TerritoryService,
    ObservationsService,
    SyncService,
    DashboardService,
  ],
})
export class AppModule {}
