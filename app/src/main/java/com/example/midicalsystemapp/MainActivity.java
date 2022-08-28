package com.example.midicalsystemapp;

import static com.example.midicalsystemapp.App.AppClass.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.midicalsystemapp.BrodcastReceiver.MyAppointmentReceiver;
import com.example.midicalsystemapp.DBConnection.FirebaseConnection;
import com.example.midicalsystemapp.UI.Activities.Login;
import com.example.midicalsystemapp.UI.Activities.MedicalRecords;
import com.example.midicalsystemapp.UI.Fragments.AppointmentFragment;
import com.example.midicalsystemapp.UI.Fragments.HomeFragment;
import com.example.midicalsystemapp.UI.Fragments.Medications;
import com.example.midicalsystemapp.UI.Fragments.MedicineFragment;
import com.example.midicalsystemapp.UI.Fragments.ShceduleFragment;
import com.example.midicalsystemapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
     ActivityMainBinding binding;
    MyAppointmentReceiver myAppointmentReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);

        setContentView(binding.getRoot());
        replaceFregment(new HomeFragment());
        FirebaseConnection firebaseConnection = new FirebaseConnection();
       // firebaseConnection.GetPatientAppointment();
        binding.bootomNavigationView.setOnItemSelectedListener(item ->{
           switch (item.getItemId()){
               case R.id.home_menu_item:
                   replaceFregment(new HomeFragment());
                   break;
               case R.id.appointment_menu_item:
                   replaceFregment(new AppointmentFragment());
                   break;
               case R.id.scheduling_menu_item:
                   replaceFregment(new ShceduleFragment());
                   break;
               case R.id.medicine_menu_item:
                   replaceFregment(new Medications());
                   break;
               case   R.id.profile_menu_item:
               replaceFregment(new MedicalRecords());
                   break;

           }


            return  true;
        });
    }


    private  void replaceFregment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.logout:{
                SharedPreferences preferences = getSharedPreferences("patient",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                if (editor.remove("id").commit()){
                    Log.e("user", "user deleted");
                }
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}