package com.example.midicalsystemapp.UI.Activities

import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.example.midicalsystemapp.App.AppClass
import com.example.midicalsystemapp.DBConnection.FirebaseConnection
import com.example.midicalsystemapp.Model.PreviousOperation
import com.example.midicalsystemapp.databinding.AddOperationBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.Timestamp
import java.util.*

class AddOperation : AppCompatActivity() {
    private lateinit var binding: AddOperationBinding
    private lateinit var db: FirebaseConnection
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db =FirebaseConnection()
        binding = AddOperationBinding.inflate(layoutInflater)
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
        val hospital = binding.hospital
        val date = binding.time
        val addBtn = binding.add
        val cancel = binding.cancel

        addBtn.setOnClickListener {
            if (checkField(name) &&
                checkField(hospital) &&
                checkField(date)
            ) {
                var operation = PreviousOperation()
                operation.operationName = name.text.toString()
                operation.date = Timestamp.now()
                operation.clinicName = hospital.text.toString()
                operation.created_at = Timestamp.now()
                db.storeOperation(operation)
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

    fun openTimePicker(context: Context, binding: AddOperationBinding) {

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