package com.example.midicalsystemapp.App;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.midicalsystemapp.DBConnection.FirebaseConnection;
import com.example.midicalsystemapp.Model.Allergy;
import com.example.midicalsystemapp.Model.Appointment;
import com.example.midicalsystemapp.Model.ChronicDisease;
import com.example.midicalsystemapp.Model.Clinic;
import com.example.midicalsystemapp.Model.Department;
import com.example.midicalsystemapp.Model.Doctor;
import com.example.midicalsystemapp.Model.*;
import com.example.midicalsystemapp.Model.PatentAppointment;
import com.example.midicalsystemapp.Model.Patient;
import com.example.midicalsystemapp.R;
import com.example.midicalsystemapp.UI.Activities.Splash;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppClass extends Application {
    static FirebaseConnection firebaseConnection;
    public static Map<String, Governorate> governorates = new HashMap<>();
    public static Map<String, Department> departments = new HashMap<>();
    public static Map<String, Clinic> clinics_array = new HashMap<>();
    public static ArrayList<Clinic> clinics_array1 = new ArrayList<>();
    public static Map<String, Doctor> doctors_array = new HashMap<>();
    public static Map<String, Doctor> department_doctors_array = new HashMap<>();
    public static ArrayList<Doctor> doctors_array1 = new ArrayList<>();
    public static ArrayList<Doctor> department_doctors_array1 = new ArrayList<>();
    public static Map<String, Patient> patients = new HashMap<>();
    public static Map<String, Patient> patients_on_doctors = new HashMap<>();
    public static ArrayList<Patient> requested_patient = new ArrayList<>();

    public static ArrayList<String> patients_emails = new ArrayList<>();
    public static Map<String, Appointment> doctor_appointments_for_patient = new HashMap<>();
    public static Map<String, ArrayList<Appointment>> doctor_appointments = new HashMap<>();
    public static Map<String, ArrayList<PatentAppointment>> patentAppointments = new HashMap<>();


    public static Patient LogedInPatient;
    public static Doctor choosedDoctor;
    public static Clinic chosen_clinic;
    public static Governorate chosen_Governorate;
    public static Department chosen_Department;
    public static String sharedpatient;
    public static String search_type = "name";
    public static boolean isLogedIn = false;
    public static final String CHANNEL_ID = "MyChannel";
    public static String Lng;
    public static String Lat;
    public static String doctorname = "";


    public static ArrayList<Allergy> allergies;
    public static ArrayList<ChronicDisease> diseases;
    public static ArrayList<Medicine> medications;
    public static ArrayList<PreviousOperation> operations;
    public static ArrayList<MedicineSchedule> medicineSchedules;
    public static Allergy allergy;
    public static ChronicDisease disease;
    public static PreviousOperation operation;
    public static Medicine medicine;
    public static MedicalTest medicalTest;
    public static XRay xRay;

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences preferences = getSharedPreferences("patient", MODE_PRIVATE);
        sharedpatient = preferences.getString("id", " ");
        firebaseConnection = new FirebaseConnection();
        Log.e("TAGis", sharedpatient);
        if (!sharedpatient.equals(" ")) {
            isLogedIn = true;
            firebaseConnection.GetlogedinPatient(sharedpatient.toString());
        } else {
            firebaseConnection.GetAllPatients();
        }

        firebaseConnection.GetAllGovernorates();
        firebaseConnection.GetAllDepartments();
        getAllergies();
        getDiseases();
        getOperations();
        getMedications();
        medicineSchedules = new ArrayList<>();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= 26) {
            CharSequence name = "AAA";
            String description = "DD";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public static void getpationtappointments() {
        firebaseConnection.GetPatientAppointment();
    }

    public static void getdoctorappointmentsforpationt(PatentAppointment patentAppointment) {

        firebaseConnection.getDoctorAppointment_for_display_patient_appointment(patentAppointment);
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


    public static void getAllergies() {
        allergies = firebaseConnection.getAllergies();
    }

    public static void getDiseases() {
        diseases = firebaseConnection.getChronicDiseases();
    }

    public static void getMedications() {
        medications = firebaseConnection.getMedications();
    }

    public static void getOperations() {
        operations = firebaseConnection.getPreviousOperation();
    }

  /*  public static void getMedicalTests(){
        medicalTests = firebaseConnection.getMedicalTests();
    }*/


    public static void storeSampleSchedule() {
        for (Medicine m : medications) {
            MedicineSchedule ms = new MedicineSchedule();
            ms.setChecked(true);
            ms.setDate("7/26");
            ms.setMedId(m.getId());
            firebaseConnection.storeMedicineSchedule(ms);
            System.out.println("*******" + m.getId());
        }


    }


}
