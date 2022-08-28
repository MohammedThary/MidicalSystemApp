package com.example.midicalsystemapp.UI.Activities;

import static com.example.midicalsystemapp.App.AppClass.chosen_Department;
import static com.example.midicalsystemapp.App.AppClass.chosen_Governorate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.midicalsystemapp.UI.Adapters.ClinicAdapter;
import com.example.midicalsystemapp.UI.Adapters.DepartmentAdapter;
import com.example.midicalsystemapp.UI.Adapters.DoctorAdapter;
import com.example.midicalsystemapp.databinding.ActivityDepartmentBinding;

public class DepartmentActivity extends AppCompatActivity {
  ActivityDepartmentBinding binding;
  DoctorAdapter doctorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDepartmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    chosen_Department.getdoctors();

        binding.departmentRv.setLayoutManager(new LinearLayoutManager(this));
        doctorAdapter = new DoctorAdapter(this,chosen_Department.doctors_array);
        binding.departmentRv.setAdapter(doctorAdapter);
    }
}