import { Body, Controller, Post } from '@nestjs/common';
import { PullSyncDto, PushSyncDto } from './sync.dto';
import { SyncService } from './sync.service';

@Controller('sync')
export class SyncController {
  constructor(private readonly syncService: SyncService) {}

  @Post('pull')
  pull(@Body() request: PullSyncDto) {
    return this.syncService.pull(request);
  }

  @Post('push')
  push(@Body() request: PushSyncDto) {
    return this.syncService.push(request);
  }
}
