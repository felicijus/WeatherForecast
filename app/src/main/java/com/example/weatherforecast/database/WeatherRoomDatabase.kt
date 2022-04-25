package com.example.weatherforecast.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Weather::class], version = 1, exportSchema = false)
abstract class WeatherRoomDatabase: RoomDatabase() {

    abstract val weatherDAO:WeatherDAO

    companion object{

        @Volatile
        private var INSTANCE:WeatherRoomDatabase? = null

        fun getDatabase(application: Application): WeatherRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    application.applicationContext,
                    WeatherRoomDatabase::class.java,
                    "weather_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}