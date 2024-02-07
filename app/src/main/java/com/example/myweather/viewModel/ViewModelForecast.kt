package com.example.myweather.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweather.model.ForecastResponse
import com.example.myweather.network.NetworkClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class UiStateForecast(
    val date : String = "Null",
    val maxTemp : String = "Null",
    val minTemp : String = "Null",
    val averageTemp : String = "Null",
    val windSpeed : String = "Null",
    val precipitationMM : String = "Null",
    val sunriseTime : String = "Null",
    val sunsetTime : String = "Null",
    val humidity : String = "Null",
    val condition: String = "Null",
    val iconURL: String = "Null"
)
class ViewModelForecast : ViewModel() {
    val liveData = MutableLiveData(UiStateForecast())

    init{
        fetchForecastData()
    }

    fun fetchForecastData(){
        NetworkClient().fetchForecastData("Paris", "1", object : Callback<ForecastResponse>{
            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                liveData.value = UiStateForecast(maxTemp = "Error: $t")
            }

            override fun onResponse(
                call: Call<ForecastResponse>,
                response: Response<ForecastResponse>
            ) {
                val forecast = response.body()
                if(response.isSuccessful && forecast != null ){
                    liveData.value = UiStateForecast(
                        forecast.date,
                        forecast.day.maxTempC.toString(),
                        forecast.day.minTempC.toString(),
                        forecast.day.avgTempC.toString(),
                        forecast.day.maxWind.toString(),
                        forecast.day.totalPrecipMM.toString(),
                        forecast.astro.sunRise,
                        forecast.astro.sunSet,
                        forecast.day.avgHumidity.toString(),
                        forecast.day.condition.text,
                        forecast.day.condition.icon
                        )
                } else{
                    liveData.value = UiStateForecast(maxTemp = "${response.code()}")
                }
            }
        })
    }
}