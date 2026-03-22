import { Injectable } from '@nestjs/common';
import {
  pendingSyncChanges,
  sampleSyncPullResponse,
  type SyncConflict,
} from '@cynetrace/contracts';
import { PullSyncDto, PushSyncDto } from './sync.dto';

@Injectable()
export class SyncService {
  pull(request: PullSyncDto) {
    return {
      ...sampleSyncPullResponse,
      serverCursor: new Date().toISOString(),
      deviceId: request.deviceId,
    };
  }

  push(request: PushSyncDto) {
    const conflicts: SyncConflict[] = request.changes
      .filter((change) => change.entityName === 'zone_revision')
      .map((change) => ({
        entityName: change.entityName,
        entityId: change.entityId,
        reason: 'Geometrie serveur plus recente, revue manuelle requise.',
        resolutionState: 'queued',
      }));

    return {
      deviceId: request.deviceId,
      receivedChanges: request.changes.length,
      acceptedChanges: request.changes.length - conflicts.length,
      pendingServerChanges: pendingSyncChanges.length,
      conflicts,
    };
  }
}
