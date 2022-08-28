package com.example.midicalsystemapp.utils

import android.app.Activity
import com.example.midicalsystemapp.UI.Activities.AddMedication
import com.example.midicalsystemapp.adapters.*

class AccessPoint {
    companion object {
        lateinit var allergyAdapter: AllergiesAdapter
        lateinit var operationsAdapter: OperationsAdapter
        lateinit var diseasesAdapter: DiseasesAdapter
        lateinit var medicationAdapter: MedicationsAdapter
        lateinit var medicationShceduleAdapter: MedicationShceduleAdapter
        lateinit var activity: AddMedication
    }
}