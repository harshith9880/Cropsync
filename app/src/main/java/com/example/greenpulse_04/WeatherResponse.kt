package com.example.greenpulse_04

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("main") val main: MainWeather,
    @SerializedName("weather") val weather: List<WeatherDescription>
)

data class MainWeather(@SerializedName("temp") val temp: Double)
data class WeatherDescription(@SerializedName("description") val description: String)