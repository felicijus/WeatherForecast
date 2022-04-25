package com.example.weatherforecast.database


import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDAO {


    @Query("SELECT * FROM Weather")
    fun getWeather(): Flow<List<Weather>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weatherModel: Weather)

    @Query("DELETE FROM Weather")
    suspend fun deleteAll()
}