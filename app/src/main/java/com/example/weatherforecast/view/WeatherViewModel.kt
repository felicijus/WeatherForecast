package com.example.weatherforecast.view

import androidx.lifecycle.*
import com.example.androidweatherforecast.Database.WeatherRepository
import com.example.weatherforecast.database.Weather
import com.example.weatherforecast.model.DummyData


/*class WeatherViewModel(private val repository: WeatherRepository): ViewModel() {

    val weathers: LiveData<List<Weather>> = repository.allWeather.asLiveData()

    suspend fun insert(weather: Weather) = viewModelScope.let {
        repository.insert(weather)
    }
}*/

class WeatherViewModel: ViewModel() {

    private var _weathers = MutableLiveData<List<Weather>>()

    val weathers: LiveData<List<Weather>>
        get() = _weathers

    init {
        _weathers.value = DummyData.createData()
    }
}
