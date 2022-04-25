package com.example.weatherforecast.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherforecast.database.Weather
import com.example.weatherforecast.model.DummyData


class WeatherViewModel: ViewModel() {

    private var _weathers = MutableLiveData<List<Weather>>()

    val weathers: LiveData<List<Weather>>
        get() = _weathers

    init {
        _weathers.value = DummyData.createData()
    }
}
