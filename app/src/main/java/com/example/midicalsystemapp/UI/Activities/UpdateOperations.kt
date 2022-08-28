package com.example.midicalsystemapp.UI.Activities

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.midicalsystemapp.App.AppClass
import com.example.midicalsystemapp.DBConnection.FirebaseConnection
import com.example.midicalsystemapp.databinding.UpdateOperationBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.Timestamp

class UpdateOperations : AppCompatActivity() {
    private lateinit var binding: UpdateOperationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UpdateOperationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val name = binding.name
        val hospital = binding.hospital
        val time = binding.time
        val updateBtn = binding.update
        val cancel = binding.cancel

        var operation = AppClass.operation
        binding.name.text.append( operation.operationName)
        binding.hospital.text.append(operation.clinicName)
        binding.time.text = operation.created_at.toDate().toString()

        time.setOnClickListener {
            openDatePicker()
        }
        updateBtn.setOnClickListener {

            if (checkField(name) &&
                checkField(hospital)   /*&&
              checkField(time as EditText)*/
            ) {
                operation.clinicName = hospital.text.toString()
                operation.operationName = name.text.toString()
                operation.created_at = Timestamp.now()
                FirebaseConnection().updatePreviousOperation(operation)
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


    fun openDatePicker() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()
        datePicker.show(supportFragmentManager, "tag")
    }
}