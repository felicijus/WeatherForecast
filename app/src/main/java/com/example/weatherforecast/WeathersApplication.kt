package com.example.weatherforecast

import android.app.Application
import com.example.weatherforecast.database.WeatherRoomDatabase
import com.example.weatherforecast.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WeathersApplication : Application() {


    private val applicationScope = CoroutineScope(SupervisorJob())

    // lazy -> database and repository are only created when they're needed
    val database by lazy { WeatherRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { WeatherRepository(database.weatherDAO) }
}