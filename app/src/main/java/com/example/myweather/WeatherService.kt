package com.example.myweather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {


        @GET("current.json")
        fun getWeather(
                @Query("key") key : String,
                @Query("q") q: String
        ): Call<Weather?>?


}