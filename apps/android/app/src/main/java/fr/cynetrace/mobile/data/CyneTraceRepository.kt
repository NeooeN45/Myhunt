package fr.cynetrace.mobile.data

import fr.cynetrace.mobile.data.local.IndividualDao
import fr.cynetrace.mobile.data.local.ObservationDao
import fr.cynetrace.mobile.data.local.SyncChangeDao
import fr.cynetrace.mobile.data.local.ZoneDao

class CyneTraceRepository(
    private val zoneDao: ZoneDao,
    private val observationDao: ObservationDao,
    private val individualDao: IndividualDao,
    private val syncChangeDao: SyncChangeDao,
) {
    fun observeZones() = zoneDao.observeAll()
    fun observeObservations() = observationDao.observeAll()
    fun observeIndividuals() = individualDao.observeAll()
    fun observeSyncChanges() = syncChangeDao.observeAll()

    suspend fun seedIfEmpty() {
        if (zoneDao.count() > 0) {
            return
        }

        zoneDao.upsertAll(SeedData.zones)
        observationDao.upsertAll(SeedData.observations)
        individualDao.upsertAll(SeedData.individuals)
        syncChangeDao.upsertAll(SeedData.syncChanges)
    }

    suspend fun pendingSyncCount(): Int = syncChangeDao.pendingCount()
}
