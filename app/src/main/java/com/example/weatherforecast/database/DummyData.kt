package com.example.weatherforecast.database

class DummyData {

    companion object {

        fun createData(): MutableList<Weather> {

            val weatherDummyList: MutableList<Weather> = mutableListOf(
                Weather(29, "Hot"),
                Weather(19, "Rainy"),
                Weather(0, "Cold"),
                Weather(15, "Mild"),
                Weather(45, "Extremely Hot"),
                Weather(90, "Dead"),
                Weather(-10, "Winter"),
                Weather(-3, "Snowy"),
                Weather(4, "Brown Snow"),
            )

            weatherDummyList.add(Weather(25, "Sunny"))
            weatherDummyList.add(Weather(15, "Cloudy"))
            weatherDummyList.add(Weather(10, "Rainy"))
            weatherDummyList.add(Weather(-1, "Snowy"))
            weatherDummyList.add(Weather(-9, "Very Cold"))
            weatherDummyList.add(Weather(7, "April Weather"))
            weatherDummyList.add(Weather(9, "April Weather"))
            weatherDummyList.add(Weather(10, "April Weather"))
            weatherDummyList.add(Weather(19, "April Weather"))
            weatherDummyList.add(Weather(30, "April Weather"))

            return weatherDummyList
        }
    }
}
