package com.example.greenpulse_04

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class WeatherActivity : AppCompatActivity() {
    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        val weatherApiService = RetrofitInstance.weatherApi


        viewModel.weatherLiveData.observe(this, Observer { weather ->
            findViewById<TextView>(R.id.txtTemperature).text = "${weather.main.temp} °C"
            findViewById<TextView>(R.id.txtWeatherDesc).text = weather.weather.first().description
        })

        findViewById<Button>(R.id.btnFetchWeather).setOnClickListener {
            viewModel.fetchWeather("New York") // Replace with dynamic input
        }
        findViewById<Button>(R.id.btnBackToMainWeather).setOnClickListener {
            finish()
        }
    }
}
