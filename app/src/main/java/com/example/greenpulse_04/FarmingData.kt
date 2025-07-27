package com.example.greenpulse_04


import com.google.gson.annotations.SerializedName

data class ScheduleRequest(
    @SerializedName("start_date") val startDate: String,
    @SerializedName("end_date") val endDate: String,
    @SerializedName("crop_type") val cropType: String
)

data class CalendarEvent(
    @SerializedName("title") val title: String,
    @SerializedName("date") val date: String,
    @SerializedName("description") val description: String
)