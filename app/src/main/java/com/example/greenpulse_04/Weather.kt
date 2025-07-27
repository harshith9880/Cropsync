package com.example.greenpulse_04

data class Weather(
    val main: Main,
    val weather: List<WeatherDescription>
)

data class Main(
    val temp: Double
)

