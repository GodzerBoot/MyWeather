package com.example.myweather.network


import com.example.myweather.model.ForecastResponse
import com.example.myweather.model.WeatherResponse
import com.example.myweather.service.ForecastService
import com.example.myweather.service.WeatherService
import okhttp3.OkHttpClient
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class   NetworkClient {
    private val key = "ac94d90b2d854a1eb4a160612241101"
    private val client = OkHttpClient.Builder()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://api.weatherapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client.build())
        .build()
    fun fetchWeatherData(q: String, callback: Callback<WeatherResponse>) {



        val service = retrofit.create(WeatherService::class.java)
        val callAsync = service.getWeather(key, q)


        callAsync.enqueue(callback)


    }
    fun fetchForecastData(q: String, days: String, callback: Callback<ForecastResponse>){
        val service = retrofit.create(ForecastService::class.java)
        val callAsync = service.getForecast(key, q, days)

        callAsync.enqueue(callback)
    }
}
