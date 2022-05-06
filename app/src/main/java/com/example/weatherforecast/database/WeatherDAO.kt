package com.example.weatherforecast.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDAO {

    @Query("SELECT * FROM Weather")
    fun getWeather(): Flow<List<Weather>>

    @Query("DELETE FROM Weather")
    suspend fun deleteAll()



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weather: Weather)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertList(weatherList: List<Weather>)

    @Delete
    suspend fun delete(weather: Weather)
}