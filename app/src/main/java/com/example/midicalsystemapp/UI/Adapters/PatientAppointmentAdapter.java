package com.example.midicalsystemapp.UI.Adapters;

import static com.example.midicalsystemapp.App.AppClass.*;
import static com.example.midicalsystemapp.App.AppClass.patentAppointments;

import android.app.Activity;
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

import com.example.midicalsystemapp.Model.Clinic;
import com.example.midicalsystemapp.R;
import com.example.midicalsystemapp.DBConnection.FirebaseConnection;
import com.example.midicalsystemapp.MainActivity;
import com.example.midicalsystemapp.Model.Appointment;
import com.example.midicalsystemapp.Model.Doctor;
import com.example.midicalsystemapp.Model.PatentAppointment;
import com.example.midicalsystemapp.UI.Activities.DoctorDetails;
import com.example.midicalsystemapp.UI.Activities.Splash;
import com.example.midicalsystemapp.UI.Activities.updatePatientAppointment;
import com.example.midicalsystemapp.UI.Fragments.HomeFragment;
import com.example.midicalsystemapp.databinding.ActivityDoctorDetailsBinding;
import com.example.midicalsystemapp.databinding.AppointmentItemBinding;
import com.example.midicalsystemapp.databinding.PookingItemBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientAppointmentAdapter  extends RecyclerView.Adapter<PatientAppointmentAdapter.MyViewHolder> {


    Context context;
    Doctor doctor;
    ArrayList<PatentAppointment> patentAppointments;
    Appointment appointment;
FirebaseConnection firebaseConnection = new FirebaseConnection();

    public PatientAppointmentAdapter(Context context, ArrayList<PatentAppointment> patentAppointments) {
        this.context = context;
       this.patentAppointments = patentAppointments;

    }

    @NonNull

    @Override
    public PatientAppointmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AppointmentItemBinding binding = AppointmentItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new PatientAppointmentAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientAppointmentAdapter.MyViewHolder holder, int position) {

       String time =patentAppointments.get(position).getTime();

       this.appointment = doctor_appointments_for_patient.get(patentAppointments.get(position).getAppointment_id());
        this.doctor = doctors_array.get(appointment.getDoctor_id());
        Clinic clinic = clinics_array.get(doctor.getClinic_id());

        holder.binding.doctorName.setText(doctor.getUsername());
        holder.binding.doctorSpeciality.setText(doctor.getSpecialize());
        holder.binding.clinicName.setText(clinic.getName());
        holder.binding.clinicLocation.setText(clinic.getLocation());
        holder.binding.day.setText(appointment.getDay());
        holder.binding.time.setText(time);
        String theday = appointment.getDay();

        holder.binding.delete.setOnClickListener(v->{
            if (!isNetworkConnected()){
                Toast.makeText(context, "check internet connection", Toast.LENGTH_SHORT).show();
            }else{
            ArrayList<String> Available_time =  new ArrayList<String>(appointment.getAvailable_time());
            ArrayList<String> Booking_time =  new ArrayList<String>(appointment.getBooking_time());
            Booking_time.remove(time);
            Available_time.add(time);
            List<String> new_available_time = Available_time;
            List<String> new_Booking_time= Booking_time;
            Appointment updated_appointment = new Appointment(appointment.getId(), appointment.getDay()
                    , appointment.getDoctor_id(), new_available_time, new_Booking_time);
            firebaseConnection.updateAppointment(updated_appointment);
            firebaseConnection.deletePatientAppointment(patentAppointments.get(position));
            getpationtappointments();
firebaseConnection.deletePatientfromdoctor(patients_on_doctors.get(LogedInPatient.getId()),updated_appointment);

            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Intent i = new Intent(context, Splash.class);
            i.setFlags(i.FLAG_ACTIVITY_NEW_TASK | i.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,i,0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.delete_icon)
                    .setContentTitle("appointment deleted")
                    .setContentText(theday+ " في يوم : " +  time + "تم حذف موعدك الموجود في الساعة :")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent).setAutoCancel(true);
              manager.notify(1,builder.build());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            ((Activity)context).finish();
        }
        });

        holder.binding.update.setOnClickListener(v->{
            choosedDoctor = doctor;
            ArrayList<String> Available_time =  new ArrayList<String>(appointment.getAvailable_time());
            ArrayList<String> Booking_time =  new ArrayList<String>(appointment.getBooking_time());
            Booking_time.remove(time);
            Available_time.add(time);
            List<String> new_available_time = Available_time;
            List<String> new_Booking_time= Booking_time;
            Appointment updated_appointment = new Appointment(appointment.getId(), appointment.getDay()
                    , appointment.getDoctor_id(), new_available_time, new_Booking_time);
            firebaseConnection.updateAppointment(updated_appointment);
            firebaseConnection.deletePatientAppointment(patentAppointments.get(position));
            boolean ll = patients_on_doctors.get(LogedInPatient.getId())!=null;
            Log.e("TAG123", "check: "+patients_on_doctors.get(LogedInPatient.getId()).toString()+"doctor :"+ doctors_array.get(updated_appointment.getDoctor_id()).getUsername());
            firebaseConnection.deletePatientfromdoctor(patients_on_doctors.get(LogedInPatient.getId()),updated_appointment);
            getpationtappointments();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(context, updatePatientAppointment.class);
            context.startActivity(intent);
            ((Activity)context).finish();
        });
    }

    @Override
    public int getItemCount() {
        return patentAppointments.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppointmentItemBinding binding;

        public MyViewHolder(@NonNull AppointmentItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}
