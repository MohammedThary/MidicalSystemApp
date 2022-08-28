package com.example.midicalsystemapp.UI.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import static com.example.midicalsystemapp.App.AppClass.*;
import com.example.midicalsystemapp.DBConnection.FirebaseConnection;
import com.example.midicalsystemapp.Model.Patient;
import com.example.midicalsystemapp.R;
import com.example.midicalsystemapp.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Timestamp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    ActivitySignupBinding binding;
    Uri imageuri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    String image_name = "";
    FirebaseConnection firebaseConnection;
    boolean nameCheck =false;
    boolean emailCheck =false;
    boolean passwordCheck =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        firebaseConnection = new FirebaseConnection();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
binding.LoginPage.setOnClickListener(v->{
    Intent intent = new Intent(getApplicationContext(), Login.class);

    startActivity(intent);
    finish();
});
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setTitle("log in info");
        alBuilder.setMessage("name must have 4 name \n" +
                "email must be like test@test.com\n"
                +"password must be like Password12$$");
        alBuilder.setPositiveButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alBuilder.show();
        String regexname = "^[a-zA-Z\\\\s]+";
        Pattern patternname = Pattern.compile(regexname);

        binding.usernameField.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                Matcher matcher = patternname.matcher(s.toString());
                if (matcher.matches())
                {
                    nameCheck = true;
                    Toast.makeText(getApplicationContext(),"valid name",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    emailCheck = false;
                    Toast.makeText(getApplicationContext(),"Invalid name",Toast.LENGTH_SHORT).show();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // other stuffs
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // other stuffs
            }
        });

        String regexemail = "^(.+)@(.+)$";
        Pattern patternemail = Pattern.compile(regexemail);

        binding.emailField.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                    Matcher matcher = patternemail.matcher(s.toString());
                if (matcher.matches())
                {
                    emailCheck = true;
                    Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    emailCheck = false;
                    Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // other stuffs
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // other stuffs
            }
        });

        String regexPAss = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        Pattern patternPass = Pattern.compile(regexPAss);

        binding.passwordField.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                Matcher matcher = patternPass.matcher(s.toString());
                if (matcher.matches())
                {
                    passwordCheck = true;
                    Toast.makeText(getApplicationContext(),"valid password",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    passwordCheck = false;
                    Toast.makeText(getApplicationContext(),"Invalid password",Toast.LENGTH_SHORT).show();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // other stuffs
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // other stuffs
            }
        });

        binding.AddPatient.setOnClickListener(v->
                {
                if (cheakfiled()) {
                    //uploadimage();
                    HashMap<String,String> doctors_not = new HashMap<>();
                    doctors_not.put("mohammed","patient");
                    doctors_not.put("fsdf","padsfsdftient");
                    doctors_not.put("mohasdfsmmed","patiesdfsdfnt");

                    Patient patient = new Patient(
                            "1",
                            binding.usernameField.getText().toString(),
                            binding.emailField.getText().toString(),
                            binding.passwordField.getText().toString(),
                            image_name, Timestamp.now(),Timestamp.now(),null,doctors_not);
                    firebaseConnection.AddPatient(patient);
                    firebaseConnection.GetAllPatients();
                    clearFields();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "fill your filed", Toast.LENGTH_SHORT).show();
                }

        });
        binding.uploadeImage.setOnClickListener(v->{
                if (imageuri != null){
                    uploadimage();
                }else{
                    Toast.makeText(getApplicationContext(), "choose image first", Toast.LENGTH_SHORT).show();
                }
        });
        binding.myimage.setOnClickListener(v->{
                chooseImage();
            }
        );
    }


    void clearFields() {
        binding.usernameField.setText("");
        binding.passwordField.setText("");
        binding.emailField.setText("");
    }

    Boolean cheakfiled() {
        boolean check = false;
        if (!nameCheck || !emailCheck||!passwordCheck) {
            return false;
        } else if (nameCheck&& emailCheck&&passwordCheck) {
           if (patients_emails.contains(binding.emailField.getText().toString())){
               Toast.makeText(getApplicationContext(), "email is already exist", Toast.LENGTH_SHORT).show();
           return false;
           }else{
             return true;
           }
        }
        return check;
    }



    private void chooseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageuri = data.getData();
            binding.myimage.setImageURI(imageuri);
        }
    }

    public  void  uploadimage(){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading image");pd.show();
        final  String randomkey = UUID.randomUUID().toString();
        StorageReference reference_storage = storageReference.child("image/"+randomkey);
        image_name = randomkey;
        reference_storage.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pd.dismiss();
                reference_storage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Uri downloadUrl = uri;
                        image_name = downloadUrl.toString();
                        Log.e("getimageuri", image_name );
                    }});
         Snackbar.make(findViewById(android.R.id.content),"image uploaded.",Snackbar.LENGTH_LONG).show();            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "failed to upload", Toast.LENGTH_SHORT).show();            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount() );
                pd.setMessage("Percentage: "+ (int)progressPercent +"%");}});}
}