package com.example.greenpulse_04


import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val FARMING_BASE_URL = "https://your-farming-api.com/"
    private const val WEATHER_BASE_URL = "https://api.weatherapi.com/v1/"

    private val farmingRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(FARMING_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val weatherRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val farmingApi: FarmingApiService by lazy {
        farmingRetrofit.create(FarmingApiService::class.java)
    }

    val weatherApi: WeatherApiService by lazy {
        weatherRetrofit.create(WeatherApiService::class.java)
    }
}

