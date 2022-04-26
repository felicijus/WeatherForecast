package com.example.weatherforecast.view

import androidx.lifecycle.ViewModel

class CountViewModel: ViewModel() {

    var count:Int = 0

    fun update(){
        ++count
    }


}