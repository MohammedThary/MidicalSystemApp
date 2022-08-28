package com.example.midicalsystemapp.UI.Activities

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.midicalsystemapp.App.AppClass
import com.example.midicalsystemapp.DBConnection.FirebaseConnection
import com.example.midicalsystemapp.databinding.UpdateDiseaseBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.Timestamp
import java.util.*

class UpdateDisease : AppCompatActivity() {
    private lateinit var binding: UpdateDiseaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = UpdateDiseaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var dis = AppClass.disease
        val name = binding.name
        val medicine = binding.medicine
        val time = binding.time
        val updateBtn = binding.update
        val cancel = binding.cancel


        name.text.append(dis.name)
        medicine.text.append(dis.medicineName)

        time.setOnClickListener {
            openDatePicker()
        }
        updateBtn.setOnClickListener {
            if (checkField(name) &&
                checkField(medicine) &&
                checkField(time)
            ) {
                dis.name = name.text.toString()
                dis.medicineName = medicine.text.toString()
                dis.created_at = Timestamp.now()
                FirebaseConnection().updateDisease(dis)
                finish()
            }
        }
        cancel.setOnClickListener { finish() }
    }

    fun checkField(editText: EditText): Boolean {
        if (editText.text.toString().isEmpty()) {
            editText.error = "should not be empty"
            return false
        }
        return true
    }

    fun checkField(text: TextView): Boolean {
        if (text.text.toString().isEmpty()) {
            text.error = "should not be empty"
            return false
        }
        return true
    }

    fun openDatePicker() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()
        datePicker.show(supportFragmentManager, "tag")
        datePicker.addOnPositiveButtonClickListener {
            binding.time.text = datePicker.selection?.let { it1 -> Date(it1).toLocaleString() }
        }
    }


}