package com.example.midicalsystemapp.UI.Activities

import android.app.TimePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import com.example.midicalsystemapp.App.AppClass
import com.example.midicalsystemapp.DBConnection.FirebaseConnection
import com.example.midicalsystemapp.Model.Allergy
import com.example.midicalsystemapp.databinding.AddAllergyBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.Timestamp
import java.util.*

class AddAllergy : AppCompatActivity() {
    private lateinit var dbConnection: FirebaseConnection
    private lateinit var binding: AddAllergyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbConnection = FirebaseConnection()
        binding = AddAllergyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val time = binding.time

        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()
        datePicker.apply {
            addOnPositiveButtonClickListener {
                binding.time.text = selection?.let { it1 -> Date(it1).toLocaleString() }
            }
        }

        time.setOnClickListener {
            datePicker.show(supportFragmentManager, "tag")
        }


        val name = binding.name
        val date = binding.time
        val addBtn = binding.add
        val cancel = binding.cancel

        addBtn.setOnClickListener {
            if (checkField(name) &&
                checkField(date)
            ) {
                val allergy = Allergy()
                allergy.name = name.text.toString()
                allergy.started = time.text.toString()
                allergy.created_at = Timestamp.now()
                    dbConnection.storeAllergy(allergy)
                AppClass.getAllergies()
                finish()
            }
        }

        cancel.setOnClickListener {
            finish()
        }
    }


    fun checkField(editText: EditText): Boolean {
        if (editText.text.toString().isEmpty()) {
            editText.setError("should not be empty")
            return false
        }
        return true
    }

    fun checkField(text: TextView): Boolean {
        if (text.text.toString().isEmpty()) {
            text.setError("should not be empty")
            return false
        }
        return true
    }

    fun openTimePicker(context: Context, binding: AddAllergyBinding) {

        val timePickerDialogListener: TimePickerDialog.OnTimeSetListener =
            object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    binding.time.text = hourOfDay.toString() + ":" + minute.toString()
                }
            }

        val timePicker = TimePickerDialog(context, timePickerDialogListener, 1, 1, false)
        timePicker.show()


    }
}