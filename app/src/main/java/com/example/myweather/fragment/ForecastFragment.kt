package com.example.myweather.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myweather.R

class ForecastFragment : Fragment(R.layout.fragment_forecast) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val temp1 = getView()?.findViewById<TextView>(R.id.max_temp1)

    }


}