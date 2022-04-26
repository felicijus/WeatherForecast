package com.example.weatherforecast.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Weather::class], version = 1, exportSchema = false)
abstract class WeatherRoomDatabase: RoomDatabase() {

    abstract val weatherDAO:WeatherDAO

    companion object{

        @Volatile
        private var INSTANCE:WeatherRoomDatabase? = null

        fun getDatabase(context: Context,
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
            // Delete all content here.
            weatherDao.deleteAll()

            // Add sample words.
            var weather = Weather(1,"23","Sunny")
            weatherDao.insert(weather)
        }
    }
}