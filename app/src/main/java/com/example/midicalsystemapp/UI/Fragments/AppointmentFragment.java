package com.example.midicalsystemapp.UI.Fragments;

import static com.example.midicalsystemapp.App.AppClass.doctors_array1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import static com.example.midicalsystemapp.App.AppClass.*;

import com.example.midicalsystemapp.App.AppClass;
import com.example.midicalsystemapp.Model.PatentAppointment;
import com.example.midicalsystemapp.R;
import com.example.midicalsystemapp.UI.Adapters.DoctorAdapter;
import com.example.midicalsystemapp.UI.Adapters.PatientAppointmentAdapter;
import com.example.midicalsystemapp.databinding.FragmentAppointmentBinding;
import com.example.midicalsystemapp.databinding.FragmentHomeBinding;

public class AppointmentFragment extends Fragment {



    private FragmentAppointmentBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAppointmentBinding.inflate(inflater, container, false);
        binding.myAppointmentRv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        PatientAppointmentAdapter patientAppointmentAdapter = new PatientAppointmentAdapter(this.getContext(), patentAppointments.get(LogedInPatient.getId()));
        if (patentAppointments == null){
            binding.myAppointmentRv.setAdapter(null);
        }else{
            binding.myAppointmentRv.setAdapter(patientAppointmentAdapter);
        }

        return binding.getRoot();
    }
}