package com.example.weatherforecast.database

class DummyData {

    companion object {

        fun createData(): MutableList<Weather> {

            var weatherDummyList: MutableList<Weather> = mutableListOf(
                Weather(null, "29", "Hot"),
                Weather(null, "19", "Rainy"),
                Weather(null, "0", "Cold"),
                Weather(null, "15", "Mild"),
                Weather(null, "45", "Extremely Hot"),
                Weather(null, "90", "Dead"),
                Weather(null, "-10", "Winter"),
                Weather(null, "-3", "Snowy"),
                Weather(null, "4", "Brown Snow"),
            )

            weatherDummyList.add(Weather(null, "25", "Sunny"))
            weatherDummyList.add(Weather(null, "15", "Cloudy"))
            weatherDummyList.add(Weather(null, "10", "Rainy"))
            weatherDummyList.add(Weather(null, "-1", "Snowy"))
            weatherDummyList.add(Weather(null, "-9", "Very Cold"))
            weatherDummyList.add(Weather(null, "7", "April Weather"))
            weatherDummyList.add(Weather(null, "9", "April Weather"))
            weatherDummyList.add(Weather(null, "10", "April Weather"))
            weatherDummyList.add(Weather(null, "19", "April Weather"))
            weatherDummyList.add(Weather(null, "30", "April Weather"))

            return weatherDummyList
        }
    }
}
