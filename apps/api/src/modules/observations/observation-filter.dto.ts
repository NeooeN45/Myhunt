import { IsInt, IsOptional, IsString, Max, Min } from 'class-validator';

export class ObservationFilterDto {
  @IsOptional()
  @IsString()
  zoneId?: string;

  @IsOptional()
  @IsInt()
  @Min(2020)
  @Max(2035)
  seasonYear?: number;
}
