package com.example.weatherforecast.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather(
    @PrimaryKey(autoGenerate = true) val id: Int?,//nullable?
    @ColumnInfo(name = "temp") val temp: String?,
    @ColumnInfo(name = "summmary") val summary: String?
)