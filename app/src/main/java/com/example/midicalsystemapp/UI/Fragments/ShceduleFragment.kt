package com.example.midicalsystemapp.UI.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midicalsystemapp.App.AppClass
import com.example.midicalsystemapp.DBConnection.FirebaseConnection
import com.example.midicalsystemapp.Model.MedicineSchedule
import com.example.midicalsystemapp.adapters.DaysAdapter
import com.example.midicalsystemapp.adapters.MedicationShceduleAdapter
import com.example.midicalsystemapp.adapters.MonthsAdapter
import com.example.midicalsystemapp.data.FakeDatabase
import com.example.midicalsystemapp.data.MedicationShcedule
import com.example.midicalsystemapp.databinding.ShceduleBinding
import java.time.LocalDateTime
import java.util.*
import java.util.Calendar.DAY_OF_MONTH
import kotlin.collections.ArrayList
import kotlin.random.Random

class ShceduleFragment : Fragment() {
    lateinit var binding: ShceduleBinding
    lateinit var days: RecyclerView
    lateinit var months: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = ShceduleBinding.inflate(layoutInflater)
        initCalenderRecycler(binding)
        initMedicationRecycler(this, binding)
        val daysObservable = binding.calendarDaysOfWeek.adapter as DaysAdapter
        val filterListener = binding.medications.adapter as MedicationShceduleAdapter
        val monthObservable = binding.calendarMonths.adapter as MonthsAdapter
        val medicationFilterListener = binding.medications.adapter as MedicationShceduleAdapter
        daysObservable.setFilterListener(medicationFilterListener)
        val unSelectObservable = binding.calendarMonths.adapter as MonthsAdapter
        val unSelectListener = binding.calendarDaysOfWeek.adapter as DaysAdapter
        val medicationsUnSelectListener = binding.medications.adapter as MedicationShceduleAdapter
        unSelectObservable.setUnselectListener(unSelectListener)
        monthObservable.setFilterListener(daysObservable)
        daysObservable.setUnselectListener(medicationsUnSelectListener)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.medications.adapter?.notifyDataSetChanged()
    }

    private fun initCalenderRecycler(binding: ShceduleBinding) {
        days = binding.calendarDaysOfWeek
        months = binding.calendarMonths

        val horizontalLayoutManager1 =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        val verticalLayoutManager1 =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)

        val horizontalLayoutManager2 =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)

        val daysAdapter = DaysAdapter()
        val monthsAdapter = MonthsAdapter()

        days.adapter = daysAdapter
        days.layoutManager = horizontalLayoutManager1

        months.adapter = monthsAdapter
        months.layoutManager = horizontalLayoutManager2

    }

    private fun initMedicationRecycler(activity: ShceduleFragment, binding: ShceduleBinding) {
        println("${Calendar.MONTH}/${Calendar.DAY_OF_MONTH}")
        val medications = binding.medications
        var data = ArrayList<MedicationShcedule>()
        var date = LocalDateTime.now()
        data = FakeDatabase.filterByDay("${date.month}/${date.dayOfMonth}")
        val medicationsAdapter = MedicationShceduleAdapter(/*data*/)
        val verticalLayoutManager2 =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        medications.adapter = medicationsAdapter
        medications.layoutManager = verticalLayoutManager2
    }


}