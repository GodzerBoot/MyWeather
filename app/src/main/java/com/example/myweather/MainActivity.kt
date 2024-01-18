package com.example.myweather


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var textView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textWeatherId)

        fetchWeatherData()
    }

    private fun fetchWeatherData() {
        NetworkClient().fetchWeatherData("Paris", object : Callback<Weather?> {
            override fun onFailure(call: Call<Weather?>, t: Throwable) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Network error occurred", Toast.LENGTH_SHORT).show()
                    textView.text = "Network error occurred"
                }
            }

            override fun onResponse(call: Call<Weather?>, response: Response<Weather?>) {
                val weather = response.body()
                if (response.isSuccessful && weather != null) {

                    runOnUiThread {
                        textView.text = response.raw().toString()
                        Toast.makeText(this@MainActivity, "Success!", Toast.LENGTH_SHORT).show()
                    }




                } else {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "${response.code()}", Toast.LENGTH_SHORT).show()
                        textView.text = response.code().toString()
                    }
                }
            }
        })
    }

    private fun updateWeatherUI(weather: Weather): String {
        val temperatureText =  weather.temp_c
        val humidityText = weather.humidity
        val descriptionText =  weather.conditionText


        return "$temperatureText\n$humidityText\n$descriptionText"

    }
}