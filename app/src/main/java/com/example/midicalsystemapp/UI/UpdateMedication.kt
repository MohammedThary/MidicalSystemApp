package com.example.midicalsystemapp.UI

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.example.midicalsystemapp.App.AppClass
import com.example.midicalsystemapp.DBConnection.FirebaseConnection
import com.example.midicalsystemapp.Model.MedicineSchedule
import com.example.midicalsystemapp.R
import com.example.midicalsystemapp.broadcastrecivers.AlarmReceiver
import com.example.midicalsystemapp.databinding.UpdateMedicationBinding
import java.util.*

class UpdateMedication : AppCompatActivity() {
    private var alarmMgr: AlarmManager? = null
    private var alarmIntent: PendingIntent? = null
    lateinit var binding: UpdateMedicationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UpdateMedicationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var med = AppClass.medicine
        dropDownSpinner(binding.durationSpinner)
        val time = binding.time
        val prevTime = time.text.toString()
        time.setOnClickListener {
            openTimePicker(binding.root.context, binding)
        }

        binding.name.text.append(med.name)
        binding.amount.text.append(med.amount.toString())
        binding.duration.text.append(med.duration.toString())
        binding.time.text = med.amount.toString()


        binding.update.setOnClickListener {
            val amount = binding.amount
            val duration = binding.duration
            val name = binding.name
            val update = binding.update
            if (checkField(name) &&
                checkField(amount) &&
                checkField(duration)
            ) {

                med.amount = amount.text.toString().toInt()
                med.name = name.text.toString()
                med.duration = duration.text.toString().toInt()

                FirebaseConnection().updateMedicine(med)
                FirebaseConnection().getMedicine(med.id)
                val t = time.text.toString()
                val arr = t.split(":")
                val prevArr = prevTime.split(":")
                cancelAlarm(prevArr[0].toInt(), prevArr[1].toInt())

                println(AppClass.medicine.name)
                finish()
            }


        }
    }

    fun dropDownSpinner(spinner: Spinner) {
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

    fun checkField(editText: EditText): Boolean {
        if (editText.text.toString().isEmpty()) {
            editText.setError("should not be empty")
            return false
        }
        return true
    }

    fun openTimePicker(context: Context, binding: UpdateMedicationBinding) {

        val timePickerDialogListener: TimePickerDialog.OnTimeSetListener =
            object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

                    binding.time.text = hourOfDay.toString() + ":" + minute.toString()
                }
            }

        val timePicker = TimePickerDialog(context, timePickerDialogListener, 1, 1, false)
        timePicker.show()


    }

    private fun setAlarm(hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        alarmMgr = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager;
        intent = Intent(
            this, AlarmReceiver::class.java
        )
        alarmIntent = PendingIntent.getBroadcast(
            this, 1, intent, 0
        );
        alarmMgr!!.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            alarmIntent
        );
    }

    private fun cancelAlarm(hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        alarmMgr = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager;
        intent = Intent(
            this, AlarmReceiver::class.java
        )
        alarmIntent = PendingIntent.getBroadcast(
            this, 1, intent, 0
        );
        alarmMgr!!.cancel(alarmIntent);
    }


}

