package com.example.myweather.model

import com.google.gson.annotations.SerializedName

data class ForecastResponse (
    @SerializedName("date") val date: String,
    @SerializedName("day") val day: Day,
    @SerializedName("astro") val astro: Astro
) {

    data class Day(
        @SerializedName("maxtemp_c") val maxTempC: Float,
        @SerializedName("mintemp_c") val minTempC: Float,
        @SerializedName("avgtemp_c") val avgTempC: Float,
        @SerializedName("maxwind_kph") val maxWind: Float,
        @SerializedName("totalprecip_mm") val totalPrecipMM: Float,
        @SerializedName("avghumidity") val avgHumidity: Int,
        @SerializedName("condition") val condition: Condition
    )

    data class Astro(
        @SerializedName("sunrise") val sunRise: String,
        @SerializedName("sunset") val sunSet: String
    )

    data class Condition(
        @SerializedName("text") val text: String,
        @SerializedName("icon") val icon: String
    )

}