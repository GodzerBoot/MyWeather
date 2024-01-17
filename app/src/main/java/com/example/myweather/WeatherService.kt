package com.example.myweather

import com.squareup.moshi.Json
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface WeatherService {


        @GET("/current")
        fun getWeather(
        ): Call<Weather?>?


}