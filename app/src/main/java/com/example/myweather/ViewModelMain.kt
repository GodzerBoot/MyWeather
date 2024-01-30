package com.example.myweather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweather.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class UiState(
    val text : String,
    val iconUrl : String
)

class ViewModelMain : ViewModel() {



    val liveData = MutableLiveData(UiState("Null", "Null"))

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

                    liveData.value = UiState(updateWeatherUI(weather), weather.current.condition.icon)


                } else {
                    liveData.value = UiState("Net code is  ${response.code().toString()}", "Null" )
                }
            }
        })
    }

    fun updateWeatherUI(weather: WeatherResponse): String {
        val temperatureText = weather.current.tempC
        val humidityText = weather.current.humidity
        val descriptionText = weather.current.condition.text


        return "Temperature, C: $temperatureText\nHumidity, %: $humidityText\n$descriptionText"

    }
}