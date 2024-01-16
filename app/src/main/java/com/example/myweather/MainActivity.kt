package com.example.myweather

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okio.IOException
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    val textView = findViewById<TextView>(R.id.textWeatherId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        fetchWeatherData("Rostov-on-Don")

    }

    private fun fetchWeatherData(q: String) {
        val key = "ac94d90b2d854a1eb4a160612241101"
        val url = "http://api.weatherapi.com/v1/current.json?key=$key&q=Paris"

        val request = Request.Builder()
            .url(url)
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Network error occurred", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()
                if (response.isSuccessful && json != null) {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "Success!", Toast.LENGTH_SHORT).show()
                    }



                } else {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "${response.code}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

}