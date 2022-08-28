package com.example.midicalsystemapp.UI.Activities;

import static com.example.midicalsystemapp.App.AppClass.chosen_clinic;
import static com.example.midicalsystemapp.App.AppClass.clinics_array;
import static com.example.midicalsystemapp.App.AppClass.doctor_appointments;
import static com.example.midicalsystemapp.App.AppClass.doctors_array1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.midicalsystemapp.DBConnection.FirebaseConnection;
import com.example.midicalsystemapp.Model.Appointment;
import com.example.midicalsystemapp.Model.Clinic;
import com.example.midicalsystemapp.Model.Department;
import com.example.midicalsystemapp.Model.Doctor;
import com.example.midicalsystemapp.Model.Governorate;
import com.example.midicalsystemapp.UI.Adapters.ClinicAdapter;
import com.example.midicalsystemapp.UI.Adapters.DoctorAdapter;
import com.example.midicalsystemapp.databinding.ActivityClinicBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClinicActivity extends AppCompatActivity {
ActivityClinicBinding binding;
DoctorAdapter doctorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClinicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        chosen_clinic.getdoctors();
        binding.doctorsRv.setLayoutManager(new LinearLayoutManager(this));
        doctorAdapter = new DoctorAdapter(this,chosen_clinic.doctors_array);
        binding.doctorsRv.setAdapter(doctorAdapter);

}
}