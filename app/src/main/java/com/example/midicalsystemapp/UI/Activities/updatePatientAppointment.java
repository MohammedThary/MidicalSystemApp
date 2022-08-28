package com.example.midicalsystemapp.UI.Activities;

import static com.example.midicalsystemapp.App.AppClass.choosedDoctor;
import static com.example.midicalsystemapp.App.AppClass.doctor_appointments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.midicalsystemapp.Model.Appointment;
import com.example.midicalsystemapp.R;
import com.example.midicalsystemapp.UI.Adapters.TimeAdapter;
import com.example.midicalsystemapp.UI.Adapters.updatePatientAppoinmentAdapter;
import com.example.midicalsystemapp.databinding.ActivityUpdatePatientAppointmentBinding;

import java.util.ArrayList;
import java.util.Locale;

public class updatePatientAppointment extends AppCompatActivity {
     ActivityUpdatePatientAppointmentBinding binding;
    updatePatientAppoinmentAdapter updatepatientappoinmentadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityUpdatePatientAppointmentBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.Saturday.setOnClickListener(v -> {
            Appointment appointment = getDayappointment(binding.Saturday.getText().toString().toLowerCase(Locale.ROOT),doctor_appointments.get(choosedDoctor.getId()));
            if (appointment.getDay()!=null){
                binding.pookRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                updatepatientappoinmentadapter = new updatePatientAppoinmentAdapter(updatePatientAppointment.this,appointment);
                binding.pookRv.setAdapter(updatepatientappoinmentadapter);
            }else{
                binding.pookRv.setAdapter(null);

            }
        });



        binding.Sunday.setOnClickListener(v -> {
            Appointment appointment = getDayappointment(binding.Sunday.getText().toString().toLowerCase(Locale.ROOT),doctor_appointments.get(choosedDoctor.getId()));
            if (appointment.getDay()!=null){
                binding.pookRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                updatepatientappoinmentadapter = new updatePatientAppoinmentAdapter(updatePatientAppointment.this,appointment);
                binding.pookRv.setAdapter(updatepatientappoinmentadapter);
            }else{
                binding.pookRv.setAdapter(null);

            }
        });

        binding.Monday.setOnClickListener(v -> {
            Appointment appointment = getDayappointment(binding.Monday.getText().toString().toLowerCase(Locale.ROOT),doctor_appointments.get(choosedDoctor.getId()));
            if (appointment.getDay()!=null){
                binding.pookRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                updatepatientappoinmentadapter = new updatePatientAppoinmentAdapter(updatePatientAppointment.this,appointment);
                binding.pookRv.setAdapter(updatepatientappoinmentadapter);
            }else{
                binding.pookRv.setAdapter(null);

            }
        });

        binding.Thursday.setOnClickListener(v -> {
            Appointment appointment = getDayappointment(binding.Thursday.getText().toString().toLowerCase(Locale.ROOT),doctor_appointments.get(choosedDoctor.getId()));
            if (appointment.getDay()!=null){
                binding.pookRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                updatepatientappoinmentadapter = new updatePatientAppoinmentAdapter(updatePatientAppointment.this,appointment);
                binding.pookRv.setAdapter(updatepatientappoinmentadapter);
            }else{
                binding.pookRv.setAdapter(null);

            }
        });

        binding.Wednesday.setOnClickListener(v -> {
            Appointment appointment = getDayappointment(binding.Wednesday.getText().toString().toLowerCase(Locale.ROOT),doctor_appointments.get(choosedDoctor.getId()));
            if (appointment.getDay()!=null){
                binding.pookRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                updatepatientappoinmentadapter = new updatePatientAppoinmentAdapter(updatePatientAppointment.this,appointment);
                binding.pookRv.setAdapter(updatepatientappoinmentadapter);
            }else{
                binding.pookRv.setAdapter(null);

            }
        });

        binding.Tuesday.setOnClickListener(v -> {
            Appointment appointment = getDayappointment(binding.Tuesday.getText().toString().toLowerCase(Locale.ROOT),doctor_appointments.get(choosedDoctor.getId()));
            if (appointment.getDay()!=null){
                binding.pookRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                updatepatientappoinmentadapter = new updatePatientAppoinmentAdapter(updatePatientAppointment.this,appointment);
                binding.pookRv.setAdapter(updatepatientappoinmentadapter);
            }else{
                binding.pookRv.setAdapter(null);

            }
        });

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "choose Appointment please", Toast.LENGTH_LONG).show();
    }

    public static Appointment getDayappointment(String day , ArrayList<Appointment> arr){
        Appointment appointment1 = new Appointment();
        for (Appointment appointment: arr) {
            if (appointment.getDay().equals(day)){
             return  appointment;
            }
        }

        return appointment1;
    }
}