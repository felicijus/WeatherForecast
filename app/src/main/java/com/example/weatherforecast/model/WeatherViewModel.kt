package com.example.weatherforecast.model

import androidx.lifecycle.*
import com.example.weatherforecast.repository.WeatherRepository
import com.example.weatherforecast.database.Weather
import kotlinx.coroutines.launch

// ViewModel -> designed to store and manage UI-related data in a lifecycle conscious way
// allows data to survive configuration changes such as screen rotations.
// wraps all methods for implementation in the UI
class WeatherViewModel(private val repository: WeatherRepository): ViewModel() {

    val weathers: LiveData<List<Weather>> = repository.allWeather.asLiveData()

    fun insert(weather: Weather) = viewModelScope.launch {
        repository.insert(weather)
    }

    fun update(weather: Weather) = viewModelScope.launch {
        repository.update(weather)
    }

    fun delete(weather: Weather) = viewModelScope.launch {
        repository.delete(weather)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}