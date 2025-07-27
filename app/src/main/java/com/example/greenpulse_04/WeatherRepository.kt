package com.example.greenpulse_04

class WeatherRepository {
    private val api = RetrofitInstance.weatherApi

    suspend fun getWeather(city: String): WeatherResponse {
        return api.getWeather(city, "f79a4d940e3f49c0b59174338250502")
    }
}
