package fr.cynetrace.mobile.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        ZoneEntity::class,
        ObservationEntity::class,
        IndividualEntity::class,
        SyncChangeEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class CyneTraceDatabase : RoomDatabase() {
    abstract fun zoneDao(): ZoneDao
    abstract fun observationDao(): ObservationDao
    abstract fun individualDao(): IndividualDao
    abstract fun syncChangeDao(): SyncChangeDao
}
