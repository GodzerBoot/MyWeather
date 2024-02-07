package com.example.myweather.service


import com.example.myweather.model.ForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {
    @GET("forecast.json")
    fun getForecast(
        @Query("key") key : String,
        @Query("q") q : String,
        @Query("days") days : String
    ): Call<ForecastResponse>
}