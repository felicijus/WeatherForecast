package com.example.weatherforecast.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather(
    @PrimaryKey(autoGenerate = true) val id: Long?,//nullable?
    @ColumnInfo(name = "temp") val temp: String?,
    @ColumnInfo(name = "summary") val summary: String?
)