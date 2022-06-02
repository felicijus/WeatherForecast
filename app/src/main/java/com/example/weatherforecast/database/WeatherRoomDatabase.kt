package com.example.weatherforecast.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


// WeatherRoomDatabase class holds the reference to the Database
// defines configuration
// annotation @Database includes:
// 1. Entities array -> associates the specified Entity with the Database (just one "Weather")
// 2. Version -> important for migrations from different Entity definitions


@Database(entities = [Weather::class], version = 3, exportSchema = false)
abstract class WeatherRoomDatabase : RoomDatabase() {

    abstract val weatherDAO: WeatherDAO     // associates a DAO with the Database

    companion object {      // TODO:

        @Volatile
        private var INSTANCE: WeatherRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WeatherRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherRoomDatabase::class.java,
                    "weather_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(WeatherDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class WeatherDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.weatherDAO)
                }
            }
        }

        suspend fun populateDatabase(weatherDao: WeatherDAO) {
            // Delete all content here
            weatherDao.deleteAll()

            // add sample weather data
            val weather = Weather(1, 23.0, "Sunny")
            weatherDao.insert(weather)

            // add DummyData
            val weatherList = DummyData.createData()
            weatherDao.insertList(weatherList)
        }
    }
}