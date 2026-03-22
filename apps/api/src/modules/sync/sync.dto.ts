import {
  ArrayMinSize,
  IsArray,
  IsIn,
  IsISO8601,
  IsObject,
  IsOptional,
  IsString,
  ValidateNested,
} from 'class-validator';
import { Type } from 'class-transformer';

export class PullSyncDto {
  @IsString()
  deviceId!: string;

  @IsOptional()
  @IsISO8601()
  lastPulledAt?: string;
}

export class SyncChangeDto {
  @IsString()
  entityName!: string;

  @IsString()
  entityId!: string;

  @IsIn(['upsert', 'delete'])
  operation!: 'upsert' | 'delete';

  @IsISO8601()
  changedAt!: string;

  @IsOptional()
  @IsObject()
  payload?: Record<string, unknown>;
}

export class PushSyncDto {
  @IsString()
  deviceId!: string;

  @IsArray()
  @ArrayMinSize(1)
  @ValidateNested({ each: true })
  @Type(() => SyncChangeDto)
  changes!: SyncChangeDto[];
}
