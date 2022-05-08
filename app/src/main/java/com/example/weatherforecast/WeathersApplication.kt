package com.example.weatherforecast

import android.app.Application
import com.example.weatherforecast.database.WeatherRoomDatabase
import com.example.weatherforecast.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WeathersApplication : Application() {


    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { WeatherRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { WeatherRepository(database.weatherDAO) }
}