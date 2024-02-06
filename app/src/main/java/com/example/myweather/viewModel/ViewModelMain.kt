package com.example.myweather.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweather.NetworkClient
import com.example.myweather.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class UiState(
    val currentTemp : String = "Null",
    val conditionText : String = "Null",
    val iconUrl : String = "Null",

)

class ViewModelMain : ViewModel() {



    val liveData = MutableLiveData(UiState())

    init {
        fetchWeatherData()

    }

    fun fetchWeatherData() {
        //Network client does not operate
        NetworkClient().fetchWeatherData("Paris", object : Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse?>, t: Throwable) {
                liveData.value = UiState("Error: $t", "Null" )
            }

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                val weather = response.body()
                if (response.isSuccessful && weather != null) {

                    liveData.value = updateWeatherUI(weather)


                } else {
                    liveData.value = UiState("Net code is  ${response.code().toString()}", "Null" )
                }
            }
        })
    }

    fun updateWeatherUI(weather: WeatherResponse): UiState {
        return UiState(
            weather.current.tempC.toString(),
            "Sky condition: " + weather.current.condition.text,
            weather.current.condition.icon
        )

    }
}