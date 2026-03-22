package fr.cynetrace.mobile

import android.app.Application
import androidx.room.Room
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import fr.cynetrace.mobile.data.CyneTraceRepository
import fr.cynetrace.mobile.data.local.CyneTraceDatabase
import fr.cynetrace.mobile.sync.SyncWorker
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CyneTraceApplication : Application() {
    private val appScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    val database: CyneTraceDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            CyneTraceDatabase::class.java,
            "cynetrace.db",
        ).fallbackToDestructiveMigration().build()
    }

    val repository: CyneTraceRepository by lazy {
        CyneTraceRepository(
            zoneDao = database.zoneDao(),
            observationDao = database.observationDao(),
            individualDao = database.individualDao(),
            syncChangeDao = database.syncChangeDao(),
        )
    }

    override fun onCreate() {
        super.onCreate()

        appScope.launch {
            repository.seedIfEmpty()
        }

        val syncWork = PeriodicWorkRequestBuilder<SyncWorker>(6, TimeUnit.HOURS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build(),
            )
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "terrain-sync",
            ExistingPeriodicWorkPolicy.KEEP,
            syncWork,
        )
    }
}
