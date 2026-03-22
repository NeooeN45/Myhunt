package fr.cynetrace.mobile.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "zone")
data class ZoneEntity(
    @PrimaryKey val id: String,
    val parentZoneId: String?,
    val name: String,
    val kind: String,
    val seasonYear: Int,
    val effectiveFrom: String,
    val effectiveTo: String?,
    val areaHectares: Double,
    val centroidLng: Double,
    val centroidLat: Double,
    val syncStatus: String,
)

@Entity(tableName = "observation")
data class ObservationEntity(
    @PrimaryKey val id: String,
    val zoneId: String,
    val individualId: String?,
    val speciesId: String,
    val seasonYear: Int,
    val observedAt: String,
    val createdBy: String,
    val syncStatus: String,
    val kind: String,
    val weatherLabel: String,
    val gpsAccuracyMeters: Double,
    val comment: String,
    val longitude: Double,
    val latitude: Double,
)

@Entity(tableName = "individual")
data class IndividualEntity(
    @PrimaryKey val id: String,
    val speciesId: String,
    val displayName: String,
    val sex: String,
    val currentImportance: String,
    val seasonYear: Int,
    val presenceWeight: Int,
    val notes: String,
    val syncStatus: String,
)

@Entity(tableName = "sync_change")
data class SyncChangeEntity(
    @PrimaryKey val id: String,
    val entityName: String,
    val entityId: String,
    val operation: String,
    val changedAt: String,
    val deviceId: String,
    val syncStatus: String,
)
