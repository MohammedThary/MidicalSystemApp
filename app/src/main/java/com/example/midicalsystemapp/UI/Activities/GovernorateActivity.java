package com.example.midicalsystemapp.UI.Activities;

import static com.example.midicalsystemapp.App.AppClass.chosen_Governorate;
import static com.example.midicalsystemapp.App.AppClass.chosen_clinic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.midicalsystemapp.UI.Adapters.ClinicAdapter;
import com.example.midicalsystemapp.UI.Adapters.DoctorAdapter;
import com.example.midicalsystemapp.databinding.ActivityGovernorateBinding;

public class GovernorateActivity extends AppCompatActivity {
ActivityGovernorateBinding binding;
    ClinicAdapter clinicAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding = ActivityGovernorateBinding.inflate(getLayoutInflater());
      setContentView(binding.getRoot());

        chosen_Governorate.getclinics();
        binding.governoraterv.setLayoutManager(new LinearLayoutManager(this));
        clinicAdapter = new ClinicAdapter(this,chosen_Governorate.clinics_array);
        binding.governoraterv.setAdapter(clinicAdapter);

    }
}