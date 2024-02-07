package com.example.myweather.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.myweather.R
import com.example.myweather.viewModel.UiStateCurrent
import com.example.myweather.viewModel.UiStateForecast
import com.example.myweather.viewModel.ViewModelForecast

class ForecastFragment : Fragment(R.layout.fragment_forecast) {
    val viewModel : ViewModelForecast by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val temp1 = getView()?.findViewById<TextView>(R.id.max_temp1)
        val date1 = getView()?.findViewById<TextView>(R.id.day1)
        val observer = Observer<UiStateForecast> { forecastData ->
            // Update the UI, in this case, a TextView.
            if (temp1 != null) {
                "${forecastData.maxTemp} C".also { temp1.text = it }
            }
            if (date1 != null){
                date1.text = forecastData.date
            }

        }

        viewModel.liveData.observe(viewLifecycleOwner, observer)

    }
}