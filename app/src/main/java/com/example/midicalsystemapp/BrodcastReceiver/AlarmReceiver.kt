package com.example.midicalsystemapp.broadcastrecivers

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.midicalsystemapp.App.AppClass
import com.example.midicalsystemapp.DBConnection.FirebaseConnection
import com.example.midicalsystemapp.Model.MedicineSchedule
import com.example.midicalsystemapp.R
import com.example.midicalsystemapp.UI.Fragments.Medications
import java.time.LocalDate

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p0 != null) {
            val date = LocalDate.now()
            //////////////////////////////////////
            val manager =
                p0.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val i = Intent(p0, Medications::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            val pendingIntent = PendingIntent.getActivity(p0, 0, i, 0)
            val builder =
                NotificationCompat.Builder(p0, AppClass.CHANNEL_ID).setSmallIcon(R.drawable.ic_pills_solid)
                    .setContentTitle("Take your Medicine")
                    .setContentText(" hello!! it is time to take your medicine")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent).setAutoCancel(true)
            p0.getSystemService(Context.NOTIFICATION_SERVICE)
            manager.notify(1, builder.build())

//////////////////////////////////////////////////
            val medicationShcedule = MedicineSchedule()
            medicationShcedule.date = "${date.month.value}/${date.dayOfMonth}"
            medicationShcedule.isChecked = false
            if (p1 != null) {
                medicationShcedule.medId = p1.extras!!.getString("id")
            }
            println("${date.month.value}/${date.dayOfMonth}")
            println(medicationShcedule.medId)
            FirebaseConnection().storeMedicineSchedule(medicationShcedule)
        }
    }
}