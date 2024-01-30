package com.example.myweather.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.myweather.R
import com.example.myweather.UiState
import com.example.myweather.ViewModelMain

class CurrentFragment : Fragment(R.layout.fragment_current) {

    val viewModel: ViewModelMain by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val textView = getView()?.findViewById<TextView>(R.id.textView)
        val imageView = getView()?.findViewById<ImageView>(R.id.imageView)

        val observer = Observer<UiState> { freshWeatherData ->
            // Update the UI, in this case, a TextView.
            if (textView != null) {
                textView.text = freshWeatherData.text
            }

            if (imageView != null){
                Glide
                    .with(this)
                    .load("https:" + freshWeatherData.iconUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(imageView);
            }
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.liveData.observe(viewLifecycleOwner, observer)
    }

}