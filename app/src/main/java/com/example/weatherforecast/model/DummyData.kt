package com.example.weatherforecast.model

import com.example.weatherforecast.database.Weather

class DummyData {

    companion object{

        fun createData():ArrayList<Weather>{
            val list = ArrayList<Weather>()


            list.add(Weather(1,"25","Sunny"))
            list.add(Weather(2,"15","Cloudy"))
            list.add(Weather(3,"10","Rainy"))
            list.add(Weather(4,"-1","Snowy"))
            list.add(Weather(4,"-1","Snowy"))
            list.add(Weather(4,"-1","Snowy"))
            list.add(Weather(4,"-1","Snowy"))
            list.add(Weather(4,"-1","Snowy"))
            list.add(Weather(4,"-1","Snowy"))
            list.add(Weather(4,"-1","Snowy"))
            list.add(Weather(4,"-1","Snowy"))
            list.add(Weather(4,"-1","Snowy"))
            list.add(Weather(4,"-1","Snowy"))


            return list
        }
    }
}
