import { Controller, Get, Query } from '@nestjs/common';
import { ObservationFilterDto } from './observation-filter.dto';
import { ObservationsService } from './observations.service';

@Controller('observations')
export class ObservationsController {
  constructor(private readonly observationsService: ObservationsService) {}

  @Get()
  listObservations(@Query() filters: ObservationFilterDto) {
    return this.observationsService.listObservations(filters);
  }

  @Get('tracks')
  listTracks() {
    return this.observationsService.listTracks();
  }
}
