package com.example.midicalsystemapp.UI.Activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.midicalsystemapp.App.AppClass
import com.example.midicalsystemapp.DBConnection.FirebaseConnection
import com.example.midicalsystemapp.Model.Medicine
import com.example.midicalsystemapp.R
import com.example.midicalsystemapp.broadcastrecivers.AlarmReceiver
import com.example.midicalsystemapp.databinding.AddMedicationBinding
import com.example.midicalsystemapp.utils.AccessPoint
import com.google.firebase.Timestamp
import java.util.*

class AddMedication : AppCompatActivity() {
    private var alarmMgr: AlarmManager? = null
    private var alarmIntent: PendingIntent? = null
    lateinit var time: TextView
    var hour = 0
    var min = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AccessPoint.activity=this
        val binding = AddMedicationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dropDownSpinner(binding.durationSpinner)
        time = binding.time
        time.setOnClickListener {
            openTimePicker(binding.root.context, binding)
        }

        val amount = binding.amount
        val duration = binding.duration
        val name = binding.name
        val addBtn = binding.add
        addBtn.setOnClickListener {
            if (checkField(name) &&
                checkField(amount) &&
                checkField(duration)
            ) {
                var medicine = Medicine()
                medicine.amount = amount.text.toString().toInt()
                medicine.name = name.text.toString()
                medicine.duration = duration.text.toString().toInt()
                medicine.firstTime = Timestamp.now()
                FirebaseConnection().storeMedication(medicine)
                finish()
            }
        }
    }

    fun openTimePicker(context: Context, binding: AddMedicationBinding) {

        val timePickerDialogListener: TimePickerDialog.OnTimeSetListener =
            object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    hour = hourOfDay
                    min = minute
                    binding.time.text = "$hourOfDay:$minute"
                }
            }

        val timePicker = TimePickerDialog(context, timePickerDialogListener, 1, 1, false)
        timePicker.show()


    }

    private fun dropDownSpinner(spinner: Spinner) {
        ArrayAdapter.createFromResource(
            this,
            R.array.duration_type,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter

        }
    }

    private fun checkField(editText: EditText): Boolean {
        if (editText.text.toString().isEmpty()) {
            editText.setError("should not be empty")
            return false
        }
        return true
    }

    fun setAlarm(id:String,reqCode: Int, hour: Int, minute: Int) {

        println("Alarm set")
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        alarmMgr = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager;
        intent = Intent(
            this, AlarmReceiver::class.java
        ).apply {
            println(id)
            putExtra("id", id)
        }
        alarmIntent = PendingIntent.getBroadcast(
            this, reqCode, intent, 0
        );
        alarmMgr!!.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            alarmIntent
        );
    }

    private fun cancelAlarm(reqCode: Int, hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        alarmMgr = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager;
        intent = Intent(
            this, AlarmReceiver::class.java
        )
        alarmIntent = PendingIntent.getBroadcast(
            this, reqCode, intent, 0
        );
        alarmMgr!!.cancel(alarmIntent);
    }


}


