package com.example.weatherforecast

import com.example.weatherforecast.model.WeatherViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.weatherforecast.database.Weather
import com.example.weatherforecast.databinding.FragmentWeatherupdatedialogBinding
import com.example.weatherforecast.model.WeatherViewModel


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

        binding.dialogTemp.placeholderText = _weather.temperature.toString()
        binding.dialogSummary.placeholderText = _weather.condition

        binding.dialogBtnSave.setOnClickListener {

            if (binding.dialogTemp.editText?.text.toString() == "" ){
                weatherViewModel.update(Weather(_weather.id, null, binding.dialogSummary.editText?.text.toString()))
            }
            else {
                weatherViewModel.update(Weather(_weather.id, binding.dialogTemp.editText?.text.toString().toDouble(), binding.dialogSummary.editText?.text.toString()))
            }
            dismiss()
        }
        binding.dialogBtnAbort.setOnClickListener {
            dismiss()
        }
    }
}