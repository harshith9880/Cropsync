package com.example.greenpulse_04

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository()
    val weatherLiveData = MutableLiveData<WeatherResponse>()

    fun fetchWeather(city: String) {
        viewModelScope.launch {
            val response = repository.getWeather(city)
            weatherLiveData.postValue(response)
        }
    }
}
