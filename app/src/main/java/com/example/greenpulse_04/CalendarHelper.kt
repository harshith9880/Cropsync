package com.example.greenpulse_04

import android.content.Context
import com.example.greenpulse_04.FarmingTask
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.CalendarScopes
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.EventDateTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.google.api.client.util.DateTime

class CalendarHelper(private val context: Context, private val account: GoogleSignInAccount) {
    private val transport = GoogleNetHttpTransport.newTrustedTransport()
    private val jsonFactory = GsonFactory.getDefaultInstance()

    private val credential = GoogleAccountCredential.usingOAuth2(
        context, listOf(CalendarScopes.CALENDAR)
    ).apply { selectedAccount = account.account }

    private val calendarService = Calendar.Builder(transport, jsonFactory, credential)
        .setApplicationName("GreenPulse")
        .build()

    suspend fun addTaskToGoogleCalendar(task: FarmingTask) {
        withContext(Dispatchers.IO) {
            val event = Event()
                .setSummary(task.title)
                .setDescription("Farming Task: ${task.title}")
                .setStart(EventDateTime().setDateTime(DateTime(task.date)))
                .setEnd(EventDateTime().setDateTime(DateTime(task.date)))

            calendarService.events().insert("primary", event).execute()
        }
    }

    suspend fun getTasksFromCalendar(): List<FarmingTask> {
        return withContext(Dispatchers.IO) {
            val events = calendarService.events().list("primary").execute().items
            events.map { event ->
                FarmingTask(
                    title = event.summary,
                    date = event.start.dateTime.toString()
                )
            }
        }
    }

    suspend fun deleteTaskFromGoogleCalendar(taskTitle: String) {
        withContext(Dispatchers.IO) {
            val events = calendarService.events().list("primary")
                .setQ(taskTitle)  // Search for events with the task title
                .execute()
                .items

            if (events.isNotEmpty()) {
                for (event in events) {
                    calendarService.events().delete("primary", event.id).execute()
                }
            }
        }
    }

}
