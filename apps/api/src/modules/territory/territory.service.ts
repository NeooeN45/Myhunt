import { Injectable, NotFoundException } from '@nestjs/common';
import { demoZones, populationSnapshots } from '@cynetrace/contracts';

@Injectable()
export class TerritoryService {
  listZones() {
    return demoZones.map((zone) => ({
      ...zone,
      childZoneIds: demoZones
        .filter((candidate) => candidate.parentZoneId === zone.id)
        .map((candidate) => candidate.id),
    }));
  }

  getZone(zoneId: string) {
    const zone = this.listZones().find((item) => item.id === zoneId);

    if (!zone) {
      throw new NotFoundException(`Zone ${zoneId} introuvable`);
    }

    return zone;
  }

  getPopulation(zoneId: string) {
    return populationSnapshots.filter((item) => item.zoneId === zoneId);
  }
}
