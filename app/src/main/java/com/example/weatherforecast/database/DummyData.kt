package com.example.weatherforecast.database

class DummyData {

    companion object {

        fun createData(): MutableList<Weather> {

            val weatherDummyList: MutableList<Weather> = mutableListOf(
                Weather(29.0, "Hot"),
                Weather(19.0, "Rainy"),
                Weather(0.0, "Cold"),
                Weather(15.0, "Mild"),
                Weather(45.0, "Extremely Hot"),
                Weather(90.0, "Dead"),
                Weather(-10.0, "Winter"),
                Weather(-3.0, "Snowy"),
                Weather(4.0, "Brown Snow"),
            )

            weatherDummyList.add(Weather(25.0, "Sunny"))
            weatherDummyList.add(Weather(15.0, "Cloudy"))
            weatherDummyList.add(Weather(10.0, "Rainy"))
            weatherDummyList.add(Weather(-1.0, "Snowy"))
            weatherDummyList.add(Weather(-9.0, "Very Cold"))
            weatherDummyList.add(Weather(7.0, "April Weather"))
            weatherDummyList.add(Weather(9.0, "April Weather"))
            weatherDummyList.add(Weather(10.0, "April Weather"))
            weatherDummyList.add(Weather(19.0, "April Weather"))
            weatherDummyList.add(Weather(30.0, "April Weather"))

            return weatherDummyList
        }
    }
}
