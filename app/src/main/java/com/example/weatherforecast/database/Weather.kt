package com.example.weatherforecast.database


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


// Room Database Entity -> ERM Entity, Attributes equal table header
// each Object of the data class "Weather" corresponds to a row/dataset in the Room Database
// Constructors are overloaded for different use cases ->
// 1. create your own Weather record
// 2. fetch Weather Data from an Online API
@Entity
data class Weather(     // correspond to the table name
    @PrimaryKey(autoGenerate = true) val id: Long?, // if the primary is null Room will autogenerate primary key


    @ColumnInfo val timestamp: String?,     // if not specified column name will equal the variable
    @ColumnInfo val source_id: Int?,
    @ColumnInfo val precipitation: String?,
    @ColumnInfo val pressure_msl: Double?,
    @ColumnInfo val sunshine: Int?,
    @ColumnInfo val temperature: Double?,
    @ColumnInfo val wind_direction: Int?,
    @ColumnInfo val wind_speed: Double?,
    @ColumnInfo val cloud_cover: Int?,
    @ColumnInfo val dew_point: Double?,
    @ColumnInfo val relative_humidity: Int?,
    @ColumnInfo val visibility: Int?,
    @ColumnInfo val wind_gust_direction: Int?,
    @ColumnInfo val wind_gust_speed: Double?,

    @ColumnInfo val condition: String?,
    @ColumnInfo val icon: String?,


    ){
    constructor(temperature: Double?, condition: String?) : this(
        null,

        timestamp = null,
        source_id = null,
        precipitation = null,
        pressure_msl = null,
        sunshine = null,
        temperature = temperature,
        wind_direction = null,
        wind_speed = null,
        cloud_cover = null,
        dew_point = null,
        relative_humidity = null,
        visibility = null,
        wind_gust_direction = null,
        wind_gust_speed = null,

        condition = condition,
        icon = null
        )

    constructor(id: Long?, temperature: Double?, condition: String?) : this(
        id = id,

        timestamp = null,
        source_id = null,
        precipitation = null,
        pressure_msl = null,
        sunshine = null,
        temperature = temperature,
        wind_direction = null,
        wind_speed = null,
        cloud_cover = null,
        dew_point = null,
        relative_humidity = null,
        visibility = null,
        wind_gust_direction = null,
        wind_gust_speed = null,

        condition = condition,
        icon = null
    )
}