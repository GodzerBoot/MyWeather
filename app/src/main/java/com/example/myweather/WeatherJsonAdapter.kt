package com.example.myweather

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.*
import java.util.regex.Pattern

class WeatherJsonAdapter() : JsonAdapter<Weather>() {
    @FromJson
    override fun fromJson(reader: JsonReader): Weather? {
        val pattern = Pattern.compile("^(.*) <(.*)>$");
        val matcher = pattern.matcher(reader.toString());
        return if(matcher.find()) Weather(matcher.group(1), (matcher.group(2)).toDouble(), matcher.group(3).toInt(), matcher.group(4)) else null

    }


    @ToJson
    override fun toJson(writer: JsonWriter, value: Weather?) {
        TODO("Not yet implemented")
    }

}