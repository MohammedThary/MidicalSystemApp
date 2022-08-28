package com.example.midicalsystemapp.UI.Fragments;

import static com.example.midicalsystemapp.App.AppClass.*;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toolbar;

import com.example.midicalsystemapp.Model.Department;
import com.example.midicalsystemapp.Model.Doctor;
import com.example.midicalsystemapp.Model.Governorate;
import com.example.midicalsystemapp.UI.Adapters.ClinicAdapter;
import com.example.midicalsystemapp.UI.Adapters.DepartmentAdapter;
import com.example.midicalsystemapp.UI.Adapters.DoctorAdapter;
import com.example.midicalsystemapp.UI.Adapters.GovernorateAdapter;
import com.example.midicalsystemapp.databinding.FragmentHomeBinding;
import com.example.midicalsystemapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


public class HomeFragment extends Fragment {

    Toolbar toolbar;
    DoctorAdapter doctorAdapter;
    ClinicAdapter clinicAdapter;
    GovernorateAdapter governorateAdapter;
    DepartmentAdapter departmentAdapter;
    Context context;
    private String selectType  = "doctors";
    private ArrayAdapter<CharSequence> typeAdapter;
    ArrayList<Doctor> newarray = new ArrayList<>();
    public static FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = this.getContext();
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.SHOWRV.setLayoutManager(new LinearLayoutManager(this.getContext()));
        doctorAdapter = new DoctorAdapter(this.getContext(), doctors_array1);
        clinicAdapter = new ClinicAdapter(this.getContext(), clinics_array1);

        Collection<Governorate> governoratesvalues = governorates.values();
        ArrayList<Governorate> governorates = new ArrayList<>(governoratesvalues);
        governorateAdapter = new GovernorateAdapter(this.getContext(), governorates);

        Collection<Department> Departmentvalues = departments.values();
        ArrayList<Department> departments = new ArrayList<>(Departmentvalues);
        departmentAdapter = new DepartmentAdapter(this.getContext(), departments);
        binding.rateUp.setOnClickListener(v -> {
            sortArrayListByratingup();
            doctorAdapter = new DoctorAdapter(this.getContext(), doctors_array1);
            binding.SHOWRV.setAdapter(doctorAdapter);
        });
        binding.rateDown.setOnClickListener(v -> {
            sortArrayListByratingdown();
            doctorAdapter = new DoctorAdapter(this.getContext(), doctors_array1);
            binding.SHOWRV.setAdapter(doctorAdapter);
        });


        typeAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.type_array, R.layout.sppiner_layout);
        binding.typeSpinner.setAdapter(typeAdapter);
        binding.typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String type = binding.typeSpinner.getSelectedItem().toString().toLowerCase();
                selectType = type;
                switch (type) {
                    case "المحافظة": {
                        binding.rateUp.setVisibility(View.INVISIBLE);
                        binding.rateDown.setVisibility(View.INVISIBLE);
                        binding.SHOWRV.setAdapter(null);

                        binding.SHOWRV.setAdapter(governorateAdapter);
                        break;
                    }
                    case "القسم": {
                        binding.rateUp.setVisibility(View.INVISIBLE);
                        binding.rateDown.setVisibility(View.INVISIBLE);
                        binding.SHOWRV.setAdapter(null);

                        binding.SHOWRV.setAdapter(departmentAdapter);
                        break;
                    }
                    case "العيادة": {
                        binding.SHOWRV.setAdapter(null);
                        binding.SHOWRV.setAdapter(clinicAdapter);
                        binding.rateUp.setVisibility(View.INVISIBLE);
                        binding.rateDown.setVisibility(View.INVISIBLE);
                        break;
                    }
                    case "الطبيب": {
                        binding.rateUp.setVisibility(View.VISIBLE);
                        binding.rateDown.setVisibility(View.VISIBLE);
                        binding.SHOWRV.setAdapter(null);

                        binding.SHOWRV.setAdapter(doctorAdapter);
                        break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        binding.bySpecialty.setOnClickListener(v -> {
//            sortArrayListBySpecialty();
//            search_type = "specialty";
//            doctorAdapter = new DoctorAdapter(this.getContext(),doctors);
//            binding.doctorsRv.setAdapter(doctorAdapter);
//        });
//        binding.byLocation.setOnClickListener(v -> {
//      //      sortArrayListByLocation();
//            search_type = "location";
//            doctorAdapter = new DoctorAdapter(this.getContext(),doctors);
//            binding.doctorsRv.setAdapter(doctorAdapter);
//        });
//        binding.byRate.setOnClickListener(v -> {
//            sortArrayListByrating();
//            doctorAdapter = new DoctorAdapter(this.getContext(),doctors);
//            binding.doctorsRv.setAdapter(doctorAdapter);
//        });


        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        if (selectType.equals("governorates")) {
            inflater.inflate(R.menu.frag_menu, menu);
            MenuItem menuItem = menu.findItem(R.id.searchId);
            SearchView searchView = (SearchView) menuItem.getActionView();
            searchView.setMaxWidth(Integer.MAX_VALUE);
            searchView.setQueryHint("search here");
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    governorateAdapter.getFilter().filter(newText);


                    return false;

                }
            });
        } else if (selectType.equals("doctors")) {
            inflater.inflate(R.menu.frag_menu, menu);
            MenuItem menuItem = menu.findItem(R.id.searchId);
            SearchView searchView = (SearchView) menuItem.getActionView();
            searchView.setMaxWidth(Integer.MAX_VALUE);
            searchView.setQueryHint("search here");
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    doctorAdapter.getFilter().filter(newText);


                    return false;

                }
            });
        } else if (selectType.equals("clinics")) {
            inflater.inflate(R.menu.frag_menu, menu);
            MenuItem menuItem = menu.findItem(R.id.searchId);
            SearchView searchView = (SearchView) menuItem.getActionView();
            searchView.setMaxWidth(Integer.MAX_VALUE);
            searchView.setQueryHint("search here");
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    clinicAdapter.getFilter().filter(newText);
                    return false;
                }
            });
        } else if (selectType.equals("departments")) {
            inflater.inflate(R.menu.frag_menu, menu);
            MenuItem menuItem = menu.findItem(R.id.searchId);
            SearchView searchView = (SearchView) menuItem.getActionView();
            searchView.setMaxWidth(Integer.MAX_VALUE);
            searchView.setQueryHint("search here");
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    departmentAdapter.getFilter().filter(newText);


                    return false;

                }
            });
        }

        super.onCreateOptionsMenu(menu, inflater);
    }


    private void sortArrayListByratingup() {
        Collections.sort(doctors_array1, new Comparator<Doctor>() {
            @Override
            public int compare(Doctor o1, Doctor o2) {
                return o2.getEvaluation().compareTo(o1.getEvaluation());
            }
        });
    }

    private void sortArrayListByratingdown() {
        Collections.sort(doctors_array1, new Comparator<Doctor>() {
            @Override
            public int compare(Doctor o1, Doctor o2) {
                return o1.getEvaluation().compareTo(o2.getEvaluation());
            }
        });
    }
}