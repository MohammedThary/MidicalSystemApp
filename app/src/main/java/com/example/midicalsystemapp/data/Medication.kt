package com.example.midicalsystemapp.data

class Medication(id: Int, name: String, pills: Int, duration: Int, date: String, time: String) {
    val id: Int
    val name: String
    val pills: Int
    val duration: Int
    val date: String
    val time: String
    init {
        this.id=id
        this.name=name
        this.pills=pills
        this.duration =duration
        this.date = date
        this.time = time
    }
}
