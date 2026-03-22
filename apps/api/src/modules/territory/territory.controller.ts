import { Controller, Get, Param } from '@nestjs/common';
import { TerritoryService } from './territory.service';

@Controller('zones')
export class TerritoryController {
  constructor(private readonly territoryService: TerritoryService) {}

  @Get()
  listZones() {
    return this.territoryService.listZones();
  }

  @Get(':zoneId')
  getZone(@Param('zoneId') zoneId: string) {
    return this.territoryService.getZone(zoneId);
  }

  @Get(':zoneId/population')
  getPopulation(@Param('zoneId') zoneId: string) {
    return this.territoryService.getPopulation(zoneId);
  }
}
