package com.example.greenpulse_04

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/*
import androidx.lifecycle.LiveData
import com.example.greenpulse_01.models.CalendarEvent
import com.example.greenpulse_01.models.FarmingTask
import com.example.greenpulse_01.database.TaskDao
import retrofit2.Retrofit

class TaskRepository(private val dao: TaskDao) {
    private val api = RetrofitInstance.api

    // Suspend function to fetch tasks from the API
    suspend fun getTasks(): List<FarmingTask> = api.getTasks()

    // Suspend function to sync tasks with the calendar
    suspend fun syncCalendar(tasks: List<FarmingTask>) {
        // Assuming that each FarmingTask has a start and end date
        api.syncCalendar(
            tasks.map {
                CalendarEvent(
                    it.title,
                    it.startDate, // Make sure startDate is LocalDateTime
                    it.endDate // Make sure endDate is also LocalDateTime
                )
            }
        )
    }

    // LiveData for observing tasks in the database
    val tasks: LiveData<List<FarmingTask>> = dao.getAllTasks()

    // Suspend function to fetch tasks from the API and store them in the database
    suspend fun fetchTasksFromAPI() {
        val fetchedTasks = api.getTasks()
        dao.clearTasks()
        fetchedTasks.forEach { dao.insertTask(it) }
    }
}
 */

class TaskRepository(
    private val dao: FarmingTaskDao,
    private val api: FarmingApiService // Inject the API
) {
    val tasks: LiveData<List<FarmingTask>> = dao.getAllTasks()

    suspend fun fetchTasksFromAPI() = withContext(Dispatchers.IO) {
        try {
            val fetchedTasks = api.getTasks() // Use the injected instance

            if (!fetchedTasks.isNullOrEmpty()) {
                dao.clearTasks()
                fetchedTasks.forEach { task ->
                    dao.insertTask(task)
                }
            } else {
                Log.d("TaskRepository", "No tasks fetched from API.")
            }
        } catch (e: Exception) {
            Log.e("TaskRepository", "Error fetching tasks", e)
        }
    }
}



