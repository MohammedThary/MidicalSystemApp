package com.example.midicalsystemapp.utils

interface FilterObservable {
    fun registerListener(listener: FilterListener,data:String)
}