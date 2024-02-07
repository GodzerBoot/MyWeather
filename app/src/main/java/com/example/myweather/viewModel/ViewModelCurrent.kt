package com.example.myweather.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweather.network.NetworkClient
import com.example.myweather.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class UiStateCurrent(
    val currentTemp : String = "Null",
    val conditionText : String = "Null",
    val iconUrl : String = "Null",

)

class ViewModelCurrent : ViewModel() {



    val liveData = MutableLiveData(UiStateCurrent())

    init {
        fetchWeatherData()

    }

    fun fetchWeatherData() {
        //Network client does not operate
        NetworkClient().fetchWeatherData("Paris", object : Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse?>, t: Throwable) {
                liveData.value = UiStateCurrent("Error: $t", "Null" )
            }

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                val weather = response.body()
                if (response.isSuccessful && weather != null) {

                    liveData.value = updateWeatherUI(weather)


                } else {
                    liveData.value = UiStateCurrent("Net code is  ${response.code().toString()}", "Null" )
                }
            }
        })
    }

    fun updateWeatherUI(weather: WeatherResponse): UiStateCurrent {
        return UiStateCurrent(
            weather.current.tempC.toString(),
            "Sky condition: " + weather.current.condition.text,
            weather.current.condition.icon
        )

    }
}