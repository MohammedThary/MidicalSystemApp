package com.example.midicalsystemapp.BrodcastReceiver;

import static com.example.midicalsystemapp.App.AppClass.CHANNEL_ID;
import static com.example.midicalsystemapp.App.AppClass.doctorname;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.midicalsystemapp.R;
import com.example.midicalsystemapp.UI.Activities.Splash;

public class MyAppointmentReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent i = new Intent(context, Splash.class);
        i.setFlags(i.FLAG_ACTIVITY_NEW_TASK | i.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,i,0);
     String messege =  "موعدك مع الطبيب : "  + doctorname+ "قد حان ";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.arrive_time)
                .setContentTitle("حان الآن موعد الحجز")
                .setContentText(messege)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent).setAutoCancel(true);
        manager.notify(1,builder.build());
    }


}