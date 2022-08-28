package com.example.midicalsystemapp.UI.Activities;

import static com.example.midicalsystemapp.App.AppClass.isLogedIn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.midicalsystemapp.MainActivity;
import com.example.midicalsystemapp.databinding.ActivitySplashBinding;

public class Splash extends AppCompatActivity {
ActivitySplashBinding binding;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isNetworkConnected()){
                    if (isLogedIn){
                        Intent i  = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        Intent i  = new Intent(getApplicationContext(),Login.class);
                        startActivity(i);
                        finish();
                    }
                }

                while (!isNetworkConnected()){
                    Toast.makeText(getApplicationContext(), "check internet connection", Toast.LENGTH_SHORT).show();
                    if (isNetworkConnected()){
                            if (isLogedIn){
                                Intent i  = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                finish();
                            }else{
                                Intent i  = new Intent(getApplicationContext(),Login.class);
                                startActivity(i);
                                finish();
                        }
                    }
                }


            }
        },6000);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}