package com.example.myweather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weather (
    @Json(name = "last_updated") val lastUpdated: String,
    @Json(name = "temp_c") val temp_c: Double,
    @Json(name = "humidity") val humidity: Int,
    @Json(name = "condition:text") val conditionText : String
)