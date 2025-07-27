package com.example.greenpulse_04

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FarmingApiService {
    @GET("tasks")
    suspend fun getTasks(): List<FarmingTask>

    @POST("generate_schedule")
    suspend fun generateSchedule(@Body request: ScheduleRequest)

    @POST("sync_calendar")
    suspend fun syncCalendar(@Body events: List<CalendarEvent>)


}


