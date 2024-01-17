package com.example.myweather

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi

class ParserMoshi {

    fun parseWeatherJson(json: String): Weather {
        val moshi = Moshi.Builder()
            .add(WeatherJsonAdapter())
            .build()
        val adapter = moshi.adapter(Weather::class.java)
        return adapter.fromJson(json) ?: throw JsonDataException("Invalid JSON format")
    }
}