package com.example.myweather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweather.model.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


data class WeatherUiState(
    val uiText : String? = null
)
class ViewModelMain : ViewModel() {

    lateinit var text : String

    val liveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        text = "default"
        fetchWeatherData()
        liveData.value = text
    }

    private fun fetchWeatherData() {
        NetworkClient().fetchWeatherData("Paris", object : Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse?>, t: Throwable) {
                this@ViewModelMain.text = "trouble"
            }

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                val weather = response.body()
                if (response.isSuccessful && weather != null) {

                    this@ViewModelMain.text = updateWeatherUI(weather)

                } else {
                    this@ViewModelMain.text = "code is "+ response.code().toString()
                }
            }
        })
    }

    fun updateWeatherUI(weather: WeatherResponse): String {
        val temperatureText = weather.current.tempC
        val humidityText = weather.current.humidity
        val descriptionText = weather.current.condition


        return "$temperatureText\n$humidityText\n$descriptionText"

    }
}