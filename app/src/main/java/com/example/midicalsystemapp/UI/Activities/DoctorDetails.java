package com.example.midicalsystemapp.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.example.midicalsystemapp.App.AppClass.*;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.midicalsystemapp.DBConnection.FirebaseConnection;
import com.example.midicalsystemapp.Model.Appointment;
import com.example.midicalsystemapp.Model.Doctor;
import com.example.midicalsystemapp.R;
import com.example.midicalsystemapp.UI.Adapters.DoctorAdapter;
import com.example.midicalsystemapp.UI.Adapters.TimeAdapter;
import com.example.midicalsystemapp.databinding.ActivityDoctorDetailsBinding;
import com.google.firebase.Timestamp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DoctorDetails extends AppCompatActivity {
ActivityDoctorDetailsBinding binding;

FirebaseConnection firebaseConnection;
    TimeAdapter timeAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityDoctorDetailsBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        firebaseConnection = new FirebaseConnection();
        firebaseConnection.getDoctorAppointment_for_display_doctor_appointment(choosedDoctor);
        binding.doctorname.setText(choosedDoctor.getUsername());
        binding.specialty.setText(choosedDoctor.getSpecialize());
        binding.rating.setText(choosedDoctor.getEvaluation());
        Picasso.get().load(choosedDoctor.getImage()).into(binding.profileImage);


      binding.Saturday.setOnClickListener(v -> {
          Appointment appointment = getDayappointment(binding.Saturday.getText().toString().toLowerCase(Locale.ROOT),doctor_appointments.get(choosedDoctor.getId()));
          if (appointment.getDay()!=null){
              binding.pookRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
              timeAdapter = new TimeAdapter(DoctorDetails.this,appointment);
              binding.pookRv.setAdapter(timeAdapter);
              Log.e("getapp",appointment.toString());
          }else{
              binding.pookRv.setAdapter(null);

          }
      });



        binding.Sunday.setOnClickListener(v -> {
            Appointment appointment = getDayappointment(binding.Sunday.getText().toString().toLowerCase(Locale.ROOT),doctor_appointments.get(choosedDoctor.getId()));
            if (appointment.getDay()!=null){
                binding.pookRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                timeAdapter = new TimeAdapter(DoctorDetails.this,appointment);
                binding.pookRv.setAdapter(timeAdapter);
                Log.e("getapp",appointment.toString());
            }else{
                binding.pookRv.setAdapter(null);

            }
        });

        binding.Monday.setOnClickListener(v -> {
            Appointment appointment = getDayappointment(binding.Monday.getText().toString().toLowerCase(Locale.ROOT),doctor_appointments.get(choosedDoctor.getId()));
            if (appointment.getDay()!=null){
                binding.pookRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                timeAdapter = new TimeAdapter(DoctorDetails.this,appointment);
                binding.pookRv.setAdapter(timeAdapter);
                Log.e("getapp",appointment.toString());
            }else{
                binding.pookRv.setAdapter(null);

            }
        });

        binding.Thursday.setOnClickListener(v -> {
            Appointment appointment = getDayappointment(binding.Thursday.getText().toString().toLowerCase(Locale.ROOT),doctor_appointments.get(choosedDoctor.getId()));
            if (appointment.getDay()!=null){
                binding.pookRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                timeAdapter = new TimeAdapter(DoctorDetails.this,appointment);
                binding.pookRv.setAdapter(timeAdapter);
                Log.e("getapp",appointment.toString());
            }else{
                binding.pookRv.setAdapter(null);

            }
        });

        binding.Wednesday.setOnClickListener(v -> {
            Appointment appointment = getDayappointment(binding.Wednesday.getText().toString().toLowerCase(Locale.ROOT),doctor_appointments.get(choosedDoctor.getId()));
            if (appointment.getDay()!=null){
                binding.pookRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                timeAdapter = new TimeAdapter(DoctorDetails.this,appointment);
                binding.pookRv.setAdapter(timeAdapter);
                Log.e("getapp",appointment.toString());
            }else{
                binding.pookRv.setAdapter(null);

            }
        });

        binding.Tuesday.setOnClickListener(v -> {
            Appointment appointment = getDayappointment(binding.Tuesday.getText().toString().toLowerCase(Locale.ROOT),doctor_appointments.get(choosedDoctor.getId()));
            if (appointment.getDay()!=null){
                binding.pookRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                timeAdapter = new TimeAdapter(DoctorDetails.this,appointment);
                binding.pookRv.setAdapter(timeAdapter);
                Log.e("getapp",appointment.toString());
            }else{
                binding.pookRv.setAdapter(null);

            }
        });



    }


    public static Appointment getDayappointment(String day , ArrayList<Appointment> arr){
        Appointment appointment1 = new Appointment();
        if (arr!=null){
            for (Appointment appointment: arr) {
                if (appointment.getDay().equals(day)){
                    appointment1 = appointment;
                }
            }
        }

        return appointment1;
    }
}