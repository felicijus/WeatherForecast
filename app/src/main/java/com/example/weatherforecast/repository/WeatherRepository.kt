package com.example.androidweatherforecast.Database

import android.app.Application
import androidx.annotation.WorkerThread
import com.example.weatherforecast.database.Weather
import com.example.weatherforecast.database.WeatherDAO
import kotlinx.coroutines.flow.Flow

class WeatherRepository(private val weatherDao: WeatherDAO)
{
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWeather: Flow<List<Weather>> = weatherDao.getWeather()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(weather: Weather) {
        weatherDao.insert(weather)
    }
}