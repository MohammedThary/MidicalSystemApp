package com.example.midicalsystemapp.UI.Activities

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.midicalsystemapp.App.AppClass
import com.example.midicalsystemapp.DBConnection.FirebaseConnection
import com.example.midicalsystemapp.Model.Allergy
import com.example.midicalsystemapp.databinding.UpdateAllergyBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.Timestamp
import java.util.*

class UpdateAllergy : AppCompatActivity() {
    private lateinit var binding: UpdateAllergyBinding
    private lateinit var db: FirebaseConnection


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = FirebaseConnection()
        binding = UpdateAllergyBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val name = binding.name
        val time = binding.time
        val updateBtn = binding.update
        val cancel = binding.cancel

        var alrg = AppClass.allergy
        name.text.append(alrg.name)
        time.text = alrg.started.toString()

        time.setOnClickListener {
            openDatePicker()
        }


        updateBtn.setOnClickListener {

            if (checkField(name) &&
                checkField(time)
            ) {
                alrg.name = name.text.toString()
                alrg.created_at = Timestamp.now()
                alrg.started = time.text.toString()
                db.updateAllergy(alrg)
                db.getAllergy(alrg.id)
                AppClass.getAllergies()
                finish()
            }
        }
        cancel.setOnClickListener { finish() }
    }

    private fun checkField(editText: EditText): Boolean {
        if (editText.text.toString().isEmpty()) {
            editText.error = "should not be empty"
            return false
        }
        return true
    }

    private fun checkField(text: TextView): Boolean {
        if (text.text.toString().isEmpty()) {
            text.error = "should not be empty"
            return false
        }
        return true
    }

    private fun openDatePicker() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()
        datePicker.show(supportFragmentManager, "tag")
        datePicker.apply {
            addOnPositiveButtonClickListener {
                binding.time.text = selection?.let { it1 -> Date(it1).toLocaleString() }
            }
        }

    }

}