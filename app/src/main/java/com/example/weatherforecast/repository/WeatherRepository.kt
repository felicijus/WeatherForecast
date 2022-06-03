package com.example.weatherforecast.repository

import androidx.annotation.WorkerThread
import com.example.weatherforecast.database.Weather
import com.example.weatherforecast.database.WeatherDAO
import kotlinx.coroutines.flow.Flow

class WeatherRepository(private val weatherDao: WeatherDAO)
{
    // Room will execute all queries on a separate thread
    // an observed Flow will notify the observer when the data has changed
    val allWeather: Flow<List<Weather>> = weatherDao.getWeather()


    // by default Room runs suspend queries off the main thread -> prevents Blocking
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(weather: Weather) {      // suspend fun -> function that can be paused and resumed at a later time
        weatherDao.insert(weather)
    }

    suspend fun update(weather: Weather){
        weatherDao.update(weather)
    }

    suspend fun delete(weather: Weather){
        weatherDao.delete(weather)
    }

    suspend fun deleteAll(){
        weatherDao.deleteAll()
    }
}