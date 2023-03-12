package app.sodeg.sodeg.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import app.sodeg.sodeg.feature_newProjects.domain.usecases.UpdateNewProject
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class UpdateNewProjectWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val updateNewProject: UpdateNewProject
): CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        TODO("Not yet implemented")
    }
}