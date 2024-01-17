package com.example.myweather

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.os.Looper
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.delay
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okio.IOException

class MainActivity : AppCompatActivity() {
    lateinit var textView : TextView
    lateinit var button : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textWeatherId)

        


        button = findViewById(R.id.button)
        val mHandler = Handler(Looper.getMainLooper())
        val mRunnable = Runnable{
            fetchWeatherData()
        }
        mHandler.post(mRunnable)

    }

    private fun fetchWeatherData() {
        NetworkClient().fetchWeatherData("Rostov-on-Don", object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Network error occurred", Toast.LENGTH_SHORT).show()
                    textView.text = "Network error occurred"
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()
                if (response.isSuccessful && json != null) {

                    runOnUiThread {
                        textView.text = updateWeatherUI(ParserMoshi().parseWeatherJson(json))
                        Toast.makeText(this@MainActivity, "Success!", Toast.LENGTH_SHORT).show()
                    }




                } else {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "${response.code}", Toast.LENGTH_SHORT).show()
                        textView.text = response.code.toString()
                    }
                }
            }
        })
    }

    private fun updateWeatherUI(weather: Weather): String {
        val temperatureText =  weather.tempC
        val humidityText = weather.humidity
        val descriptionText =  weather.conditionText


        return "$temperatureText\n$humidityText\n$descriptionText"

    }
}