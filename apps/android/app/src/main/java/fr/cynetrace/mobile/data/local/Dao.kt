package fr.cynetrace.mobile.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ZoneDao {
    @Query("SELECT * FROM zone ORDER BY parentZoneId IS NOT NULL, name")
    fun observeAll(): Flow<List<ZoneEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<ZoneEntity>)

    @Query("SELECT COUNT(*) FROM zone")
    suspend fun count(): Int
}

@Dao
interface ObservationDao {
    @Query("SELECT * FROM observation ORDER BY observedAt DESC")
    fun observeAll(): Flow<List<ObservationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<ObservationEntity>)
}

@Dao
interface IndividualDao {
    @Query("SELECT * FROM individual ORDER BY currentImportance DESC, displayName")
    fun observeAll(): Flow<List<IndividualEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<IndividualEntity>)
}

@Dao
interface SyncChangeDao {
    @Query("SELECT * FROM sync_change ORDER BY changedAt DESC")
    fun observeAll(): Flow<List<SyncChangeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<SyncChangeEntity>)

    @Query("SELECT COUNT(*) FROM sync_change WHERE syncStatus = 'pending_push'")
    suspend fun pendingCount(): Int
}
