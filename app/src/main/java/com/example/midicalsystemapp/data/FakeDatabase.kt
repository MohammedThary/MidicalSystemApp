package com.example.midicalsystemapp.data

import android.net.Uri
import java.util.*
import kotlin.random.Random

class FakeDatabase {
    companion object {
        lateinit var pdfuri: Uri
        var operationIds = 1
        val medications = ArrayList<Medication>()
        val medicationShceduleList = ArrayList<MedicationShcedule>()
        val medicalTests = ArrayList<MedicalTest>()
        val xray = ArrayList<XRay>()
        fun populateWithFakeData() {
            populateMedicationsWithFakeData()
            populateShceduleWithFakeData()
        }

        private fun populateMedicationsWithFakeData() {
            val medication = Medication(
                1,
                "Acamol",
                3,
                4,
                "2022/2/2",
                "11:02"
            )
            val medication2 = Medication(
                2,
                "Valzan",
                3,
                4,
                "2022/2/2",
                "2:00"
            )
            val medication3 = Medication(
                3,
                "Eucrias",
                3,
                4,
                "2022/2/2",
                "5:00"
            )
            medications.add(medication)
            medications.add(medication2)
            medications.add(medication3)
        }


        private fun populateShceduleWithFakeData() {
            val m1 = MedicationShcedule(Random(13234).nextInt(), 1, "7/17", true)
            val m2 = MedicationShcedule(Random(13234).nextInt(), 2, "7/17", false)
            val m3 = MedicationShcedule(Random(13234).nextInt(), 3, "7/17", true)

            val m4 = MedicationShcedule(Random(13234).nextInt(), 1, "7/18", false)
            val m5 = MedicationShcedule(Random(13234).nextInt(), 2, "7/18", false)
            val m6 = MedicationShcedule(Random(13234).nextInt(), 3, "7/18", true)

            val m7 = MedicationShcedule(Random(13234).nextInt(), 1, "7/19", true)
            val m8 = MedicationShcedule(Random(13234).nextInt(), 2, "7/19", true)
            val m9 = MedicationShcedule(Random(13234).nextInt(), 3, "7/19", true)
            medicationShceduleList.add(m1)
            medicationShceduleList.add(m2)
            medicationShceduleList.add(m3)
            medicationShceduleList.add(m4)
            medicationShceduleList.add(m5)
            medicationShceduleList.add(m6)
            medicationShceduleList.add(m7)
            medicationShceduleList.add(m8)
            medicationShceduleList.add(m9)
        }


        private fun removeDuplicates(list: ArrayList<String>): ArrayList<String> {
            val newList = ArrayList<String>()
            for (v in list) {
                if (!newList.contains(v)) {
                    list.add(v)
                }
            }
            return newList
        }


        fun filterByDay(date: String): ArrayList<MedicationShcedule> {
            val shc = ArrayList<MedicationShcedule>()
            for (v in medicationShceduleList) {
                if (v.date == date) {
                    shc.add(v)
                }
            }
            return shc
        }


        fun getMedById(id: Int): Medication? {
            for (v in medications) {
                if (v.id == id) {
                    return v
                }
            }
            return null
        }

    }

}