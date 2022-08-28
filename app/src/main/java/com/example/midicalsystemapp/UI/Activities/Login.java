package com.example.midicalsystemapp.UI.Activities;

import static com.example.midicalsystemapp.App.AppClass.getpationtappointments;
import static com.example.midicalsystemapp.App.AppClass.patients;
import static com.example.midicalsystemapp.App.AppClass.LogedInPatient;
import static com.example.midicalsystemapp.App.AppClass.patients_on_doctors;
import static com.example.midicalsystemapp.App.AppClass.requested_patient;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.system.Os;
import android.util.Log;
import android.widget.Toast;

import com.example.midicalsystemapp.DBConnection.FirebaseConnection;
import com.example.midicalsystemapp.MainActivity;
import com.example.midicalsystemapp.Model.Patient;
import com.example.midicalsystemapp.R;
import com.example.midicalsystemapp.databinding.ActivityLoginBinding;
import com.google.firebase.Timestamp;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;
    FirebaseConnection firebaseConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        firebaseConnection = new FirebaseConnection();
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        binding.login.setOnClickListener(v -> {
                if (cheakdata()) {
                    clearFields();
                    SharedPreferences preferences = getSharedPreferences("patient",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("id",LogedInPatient.getId());
                    editor.putString("name",LogedInPatient.getUsername());
                    for (Patient patient: requested_patient) {
                        if (patient.getEmail().equals(LogedInPatient.getEmail())){
                            patients_on_doctors.put(LogedInPatient.getId(),patient);
                        }
                    }
                    if (editor.commit()){
                        Log.e("user", "user created");
                    }
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }

        });
        binding.regester.setOnClickListener(v -> {
                clearFields();
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);

            finish();
        });
    }

    Boolean cheakdata() {
        boolean check = false;
        String Email = binding.Email.getText().toString();
        String password = binding.Password.getText().toString();
        if (Email.equals("") || password.equals("")) {
            Toast.makeText(getApplicationContext(), "fill your data", Toast.LENGTH_SHORT).show();
        } else if (!Email.equals("") && !password.equals("")) {

                if (patients.get(Email)!= null) {
                    if (patients.get(Email).getPassword().equals(password)) {
                        LogedInPatient = patients.get(Email);
                        getpationtappointments();
                        check = true;
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            }

        return check;
    }

    void clearFields() {
        binding.Password.setText("");
        binding.Email.setText("");
    }
}

