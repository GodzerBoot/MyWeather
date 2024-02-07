package com.example.myweather.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.myweather.R
import com.example.myweather.viewModel.UiStateCurrent
import com.example.myweather.viewModel.ViewModelCurrent

class CurrentFragment : Fragment(R.layout.fragment_current) {

    private val viewModel: ViewModelCurrent by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val tempText = getView()?.findViewById<TextView>(R.id.text_current_temp)
        val iconView = getView()?.findViewById<ImageView>(R.id.image_condition)
        val skyStateText = getView()?.findViewById<TextView>(R.id.text_condition)

        val observer = Observer<UiStateCurrent> { freshWeatherData ->
            // Update the UI, in this case, a TextView.
            if (tempText != null) {
                "${freshWeatherData.currentTemp} C".also { tempText.text = it }
            }

            if (iconView != null){
                Glide
                    .with(this)
                    .load("https:" + freshWeatherData.iconUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(iconView);
            }

            if (skyStateText != null){
                freshWeatherData.conditionText.also { skyStateText.text = it}
            }
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.liveData.observe(viewLifecycleOwner, observer)
    }

}