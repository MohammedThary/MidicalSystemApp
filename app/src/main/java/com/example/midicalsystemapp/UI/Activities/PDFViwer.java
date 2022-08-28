package com.example.midicalsystemapp.UI.Activities;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.midicalsystemapp.data.FakeDatabase;
import com.example.midicalsystemapp.databinding.ActivityPdfviwerBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StreamDownloadTask;

public class PDFViwer extends AppCompatActivity {
    ActivityPdfviwerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityPdfviwerBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        Log.e("jhjhjh", FakeDatabase.pdfuri.toString());
        binding.pdfView.fromUri(FakeDatabase.pdfuri).load();


        StorageReference storageRef;
        FirebaseApp app;
        FirebaseStorage storage;
        storageRef = FirebaseStorage.getInstance().getReference();
        app = FirebaseApp.getInstance();
        storage = FirebaseStorage.getInstance(app);
        storageRef = storage.getReference().child("medical_tests/" + FakeDatabase.pdfuri);
        storageRef.getStream().addOnSuccessListener(new OnSuccessListener<StreamDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(StreamDownloadTask.TaskSnapshot taskSnapshot) {
                binding.pdfView.fromStream(taskSnapshot.getStream()).load();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //   Toast.makeText(PDFViwer.this, "Fail :" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

    }

}