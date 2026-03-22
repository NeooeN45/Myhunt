package fr.cynetrace.mobile.sync

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import fr.cynetrace.mobile.CyneTraceApplication

class SyncWorker(
    appContext: Context,
    params: WorkerParameters,
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        val app = applicationContext as CyneTraceApplication
        val pendingCount = app.repository.pendingSyncCount()

        return Result.success(
            workDataOf("pendingSyncCount" to pendingCount),
        )
    }
}
