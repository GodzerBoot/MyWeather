package com.example.myweather

import okhttp3.OkHttpClient
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class   NetworkClient {
    private val client = OkHttpClient.Builder()
    val retrofit = Retrofit.Builder()
        .baseUrl("http://api.weatherapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client.build())
        .build()
    fun fetchWeatherData(q: String, callback: Callback<Weather?>) {
        val key = "ac94d90b2d854a1eb4a160612241101"


        val service = retrofit.create(WeatherService::class.java)
        val callAsync = service.getWeather(key, "Paris")


        callAsync?.enqueue(callback)


    }
}
