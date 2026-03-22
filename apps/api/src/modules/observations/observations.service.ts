import { Injectable } from '@nestjs/common';
import { demoObservations, demoTracks } from '@cynetrace/contracts';
import { ObservationFilterDto } from './observation-filter.dto';

@Injectable()
export class ObservationsService {
  listObservations(filters: ObservationFilterDto) {
    return demoObservations.filter((item) => {
      if (filters.zoneId && item.zoneId !== filters.zoneId) {
        return false;
      }

      if (filters.seasonYear && item.seasonYear !== filters.seasonYear) {
        return false;
      }

      return true;
    });
  }

  listTracks() {
    return demoTracks;
  }
}
