package com.example.weatherforecast

import WeatherViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.weatherforecast.database.Weather
import com.example.weatherforecast.databinding.FragmentWeatherupdatedialogBinding
import com.example.weatherforecast.view.WeatherViewModel


class WeatherUpdateDialogFragment(weather:Weather) : DialogFragment(){

    private var _binding: FragmentWeatherupdatedialogBinding? = null

    private val _weather = weather

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val weatherViewModel: WeatherViewModel by activityViewModels{
        WeatherViewModelFactory((activity?.application as WeathersApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NO_FRAME,
            R.style.FullScreenDialog
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentWeatherupdatedialogBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dialogTemp.placeholderText = _weather.temp
        binding.dialogSummary.placeholderText = _weather.summary

        binding.dialogBtnSave.setOnClickListener {
            weatherViewModel.update(Weather(_weather.id, binding.dialogTemp.editText?.text.toString(), binding.dialogSummary.editText?.text.toString()))
            dismiss()
        }
        binding.dialogBtnAbort.setOnClickListener {
            dismiss()
        }
    }
}