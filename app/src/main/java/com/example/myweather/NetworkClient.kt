package com.example.myweather

import okhttp3.OkHttpClient
import retrofit.GsonConverterFactory
import retrofit.GsonConverterFactory.*
import retrofit2.Callback
import retrofit2.Retrofit


class   NetworkClient {
    private val client = OkHttpClient()
    val retrofit = Retrofit.Builder()
        .baseUrl("http://api.weatherapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
    fun fetchWeatherData(q: String, callback: Callback) {
        val key = "ac94d90b2d854a1eb4a160612241101"
        val url = "http://api.weatherapi.com/v1/current.json?key=$key&q=Paris"
        val service = retrofit.create(WeatherService::class.java)
        val callSync = service.getWeather()


        client.newCall().enqueue(callback)
    }
}