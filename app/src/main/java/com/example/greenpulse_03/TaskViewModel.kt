package com.example.greenpulse_04

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.greenpulse_03.CalendarHelper
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.launch

/*
class TaskViewModel : ViewModel() {
    private val repository = TaskRepository()
    val tasks = MutableLiveData<List<FarmingTask>>()

    fun fetchTasks() {
        viewModelScope.launch {
            tasks.postValue(repository.getTasks())
        }
    }

    fun syncWithCalendar() {
        viewModelScope.launch {
            repository.syncCalendar(tasks.value ?: emptyList())
        }
    }
}
*/

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = FarmingDatabase.getDatabase(application).farmingTaskDao()
    private val api = RetrofitInstance.farmingApi // Corrected reference
    private val repository = TaskRepository(dao, api) // Pass both dao and api
    private val _calendarTasks = MutableLiveData<List<FarmingTask>>()
    val calendarTasks: LiveData<List<FarmingTask>> get() = _calendarTasks

    val tasks = repository.tasks

    fun fetchTasks() {
        viewModelScope.launch {
            repository.fetchTasksFromAPI()
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            repository.fetchTasksFromAPI()
        }
    }

    fun syncWithCalendar(account: GoogleSignInAccount) {
        viewModelScope.launch {
            tasks.value?.forEach { task ->
                CalendarHelper(getApplication(), account).addTaskToGoogleCalendar(task)
            }
        }
    }

    fun fetchCalendarEvents(account: GoogleSignInAccount) {
        viewModelScope.launch {
            val events = CalendarHelper(getApplication(), account).getTasksFromCalendar()
            _calendarTasks.postValue(events)
        }
    }

    fun deleteTaskFromCalendar(account: GoogleSignInAccount, taskTitle: String) {
        viewModelScope.launch {
            CalendarHelper(getApplication(), account).deleteTaskFromGoogleCalendar(taskTitle)
        }
    }



}
