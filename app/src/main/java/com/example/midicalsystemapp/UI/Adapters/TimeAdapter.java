package com.example.midicalsystemapp.UI.Adapters;

import static com.example.midicalsystemapp.App.AppClass.*;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.midicalsystemapp.BrodcastReceiver.MyAppointmentReceiver;
import com.example.midicalsystemapp.DBConnection.FirebaseConnection;
import com.example.midicalsystemapp.MainActivity;
import com.example.midicalsystemapp.Model.Appointment;
import com.example.midicalsystemapp.Model.Department;
import com.example.midicalsystemapp.Model.Doctor;
import com.example.midicalsystemapp.Model.PatentAppointment;
import com.example.midicalsystemapp.R;
import com.example.midicalsystemapp.UI.Activities.DoctorDetails;
import com.example.midicalsystemapp.UI.Activities.Login;
import com.example.midicalsystemapp.UI.Activities.Splash;
import com.example.midicalsystemapp.databinding.DoctorItemBinding;
import com.example.midicalsystemapp.databinding.PookingItemBinding;
import com.google.firebase.Timestamp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.MyViewHolder> {


    Context context;
    Appointment appointment;
    ArrayList<String> Available_time;
    ArrayList<String> Booking_time;
    private PendingIntent pendingIntent;
    FirebaseConnection firebaseConnection = new FirebaseConnection();
    private AlarmManager alarmManager;
    private Calendar calendar;
    public TimeAdapter(Context context, Appointment appointment) {
        this.appointment = appointment;
        this.context = context;
        this.Available_time =  new ArrayList<String>(appointment.getAvailable_time());
        this.Booking_time =  new ArrayList<String>(appointment.getBooking_time());
    }

    @NonNull

    @Override
    public TimeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PookingItemBinding binding = PookingItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new TimeAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeAdapter.MyViewHolder holder, int position) {
        String appointment_selected = Available_time.get(position);
        holder.binding.time.setText(appointment_selected);
        holder.binding.book.setOnClickListener(v->{
            if (!isNetworkConnected()){
                Toast.makeText(context, "check internet connection", Toast.LENGTH_SHORT).show();
            }else {
                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(appointment_selected.substring(0,appointment_selected.indexOf(":"))));
                calendar.set(Calendar.MINUTE,Integer.parseInt(appointment_selected.substring(appointment_selected.indexOf(":")+1,appointment_selected.length())));
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);
                Log.e("TAGTAG", "onBindViewHolder: "+calendar.getTime().toString());
doctorname = doctors_array.get(appointment.getDoctor_id()).getUsername();

               Available_time.remove(appointment_selected);
                Booking_time.add(appointment_selected);
                setAlarm();
                List<String> new_available_time = Available_time;
                List<String> new_Booking_time = Booking_time;

                Appointment updated_appointment = new Appointment(
                        appointment.getId(), appointment.getDay(), appointment.getDoctor_id(),
                        new_available_time, new_Booking_time);
                      PatentAppointment patentAppointment =new PatentAppointment(  "String", updated_appointment.getId(), updated_appointment.getDoctor_id(),
                        appointment_selected, Timestamp.now(), null,
                        Timestamp.now());
                Log.e("TAG", doctors_array.get(appointment.getDoctor_id()).toString() );
                Log.e("TAG_Tag", "onBindViewHolder: "+holder.binding.shareNote.isChecked() );
                holder.binding.shareNote.setOnClickListener(view ->{
                            Log.e("TAGTAG", "onBindViewHolder: "+calendar.getTime().toString());

                        }
                );
                if (holder.binding.shareNote.isChecked()){
                   firebaseConnection.AddPatienttodoctor(LogedInPatient,updated_appointment);
               }

              firebaseConnection.updateAppointment(updated_appointment);
              firebaseConnection.AddPatientAppointment(patentAppointment);

                getpationtappointments();
                getdoctorappointmentsforpationt(patentAppointment);
//
                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                Intent i = new Intent(context, Splash.class);
                i.setFlags(i.FLAG_ACTIVITY_NEW_TASK | i.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, 0);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.drawable.add_icon)
                        .setContentTitle("تم إضافة الموعد")
                        .setContentText( appointment.getDay()+ " في يوم : " +  appointment_selected + "تم إضافة موعد على الساعة :")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent).setAutoCancel(true);
                manager.notify(1, builder.build());


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                ((Activity) context).finish();
            }});
    }

    @Override
    public int getItemCount() {
        return Available_time.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
      PookingItemBinding binding;

        public MyViewHolder(@NonNull PookingItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    private void setAlarm(){
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MyAppointmentReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
    }

}
