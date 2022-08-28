package com.example.midicalsystemapp.DBConnection;

import static com.example.midicalsystemapp.App.AppClass.*;


import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.midicalsystemapp.App.AppClass;
import com.example.midicalsystemapp.Model.Allergy;
import com.example.midicalsystemapp.Model.Appointment;
import com.example.midicalsystemapp.Model.ChronicDisease;
import com.example.midicalsystemapp.Model.Clinic;
import com.example.midicalsystemapp.Model.Department;
import com.example.midicalsystemapp.Model.Doctor;
import com.example.midicalsystemapp.Model.Governorate;
import com.example.midicalsystemapp.Model.MedicalTest;
import com.example.midicalsystemapp.Model.Medicine;
import com.example.midicalsystemapp.Model.MedicineSchedule;
import com.example.midicalsystemapp.Model.PatentAppointment;
import com.example.midicalsystemapp.Model.Patient;
import com.example.midicalsystemapp.Model.PreviousOperation;
import com.example.midicalsystemapp.Model.XRay;
import com.example.midicalsystemapp.utils.AccessPoint;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StreamDownloadTask;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirebaseConnection {
    FirebaseFirestore db;
    StorageReference storageRef;
    FirebaseApp app;
    FirebaseStorage storage;

    final String TAG = "err";

    public FirebaseConnection() {
        this.db = FirebaseFirestore.getInstance();
        storageRef = FirebaseStorage.getInstance().getReference();
        app = FirebaseApp.getInstance();
        storage = FirebaseStorage.getInstance(app);
        storageRef = storage.getReference().child("/files/");
    }

    public void AddPatient(Patient patient) {
        db.collection(Patient.COLLECTION_NAME).
                add(patient).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.e(TAG, "onSuccess: ");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Added not complete" + e.getMessage().toString());
                    }
                });
    }

    public void GetAllPatients() {
        db.collection(Patient.COLLECTION_NAME).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                    Patient patient = snapshot.toObject(Patient.class);
                    patient.setId(snapshot.getId());
                    patients.put(patient.getEmail(), patient);
                    patients_emails.add(patient.getEmail());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("GetAllPatients", e.getMessage());
            }
        });
    }

    ///////////////
    //////get saved patient
    public void GetlogedinPatient(String Patient_id) {
        db.collection(Patient.COLLECTION_NAME).document(Patient_id).get().
                addOnSuccessListener(
                        new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                Patient patient = documentSnapshot.toObject(Patient.class);
                                patient.setId(documentSnapshot.getId());
                                LogedInPatient = patient;
                            }
                        }
                ).addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        }
                );
    }

    ///////////////
    ///// get patient appointment
    public void GetPatientAppointment() {
        ArrayList<PatentAppointment> patentAppointmentsarray = new ArrayList<>();
        db.collection(Patient.COLLECTION_NAME).document(LogedInPatient.getId()).
                collection(PatentAppointment.COLLECTION_NAME).get().addOnSuccessListener(
                        new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                                    PatentAppointment patentAppointment = snapshot.toObject(PatentAppointment.class);
                                    patentAppointment.setId(snapshot.getId());
                                    patentAppointmentsarray.add(patentAppointment);
                                    getDoctorAppointment_for_display_patient_appointment(patentAppointment);
                                    Log.e("hello", "array size" + patentAppointment.toString());
                                }
                                patentAppointments.put(LogedInPatient.getId(), patentAppointmentsarray);

                            }
                        }
                ).addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        }
                ).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    }
                });


    }


    public void AddPatientAppointment(PatentAppointment patentAppointment) {
        db.collection(Patient.COLLECTION_NAME).
                document(LogedInPatient.getId()).collection(PatentAppointment.COLLECTION_NAME).
                add(patentAppointment).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.e(TAG, "onSuccess: ");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "added not complete" + e.getMessage().toString());
                    }
                });
    }


    public void updatePatientAppointment(PatentAppointment patentAppointment) {
        db.collection(Patient.COLLECTION_NAME).document(LogedInPatient.getId()).
                collection(PatentAppointment.COLLECTION_NAME).document(patentAppointment.getId()).
                set(patentAppointment).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.e(TAG, "updated successfully.");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "update not complete" + e.getMessage().toString());
                    }
                });
    }

    public void deletePatientAppointment(PatentAppointment patentAppointment) {
        db.collection(Patient.COLLECTION_NAME).document(LogedInPatient.getId()).
                collection(PatentAppointment.COLLECTION_NAME).document(patentAppointment.getId()).
                delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.e(TAG, "deleted successfully.");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "deleted not complete" + e.getMessage().toString());
                    }
                });
    }


    ////////////// patient done


    //////governorate
    public void GetAllGovernorates() {
        db.collection(Governorate.COLLECTION_NAME).get().
                addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                            Governorate governorate = snapshot.toObject(Governorate.class);
                            governorate.setId(snapshot.getId());
                            governorates.put(snapshot.getId(), governorate);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("BAG", e.getMessage());
                    }
                }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (Map.Entry<String, Governorate> set :
                                governorates.entrySet())
                            GetAllGovernorateClinics(set.getValue());
                    }
                });
    }

    ///////
    //////////clinics
    public void GetAllGovernorateClinics(Governorate governorate) {
        db.collection(Governorate.COLLECTION_NAME).document(governorate.getId()).
                collection(Clinic.COLLECTION_NAME).get().
                addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                            Clinic clinic = snapshot.toObject(Clinic.class);
                            clinic.setId(snapshot.getId());
                            if (governorate.getId().equals(clinic.getGovernorate_id())) {
                                governorates.get(governorate.getId()).clinics.put(clinic.getId(), clinic);
                                GetAllClinicDoctors(clinic);
                            }
                            clinics_array.put(clinic.getId(), clinic);
                            clinics_array1.add(clinic);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("BAG", e.getMessage());
                    }
                });
    }

    ////////////
    /////// clinic doctors
    public void GetAllClinicDoctors(Clinic clinic) {
        db.collection(Governorate.COLLECTION_NAME).document(clinic.getGovernorate_id()).
                collection(Clinic.COLLECTION_NAME).document(clinic.getId()).
                collection(Doctor.COLLECTION_NAME).get().
                addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                            Doctor doctor = snapshot.toObject(Doctor.class);
                            doctor.setId(snapshot.getId());
                            if (governorates.get(clinic.getGovernorate_id()).clinics.get(clinic.getId()).getId().equals(doctor.getClinic_id())) {
                                governorates.get(clinic.getGovernorate_id()).clinics.get(clinic.getId()).clinic_doctors.put(doctor.getId(), doctor);
                                getDoctorPatient_for_display_doctor_appointment(doctor);
                                getDoctorAppointment_for_display_doctor_appointment(doctor);
                            }
                            doctors_array.put(doctor.getId(), doctor);
                            doctors_array1.add(doctor);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("BAG", e.getMessage());
                    }
                });
    }

    //////////
    //department
    public void GetAllDepartments() {
        db.collection(Department.COLLECTION_NAME).get().
                addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                            Department department = snapshot.toObject(Department.class);
                            department.setId(snapshot.getId());
                            departments.put(department.getId(), department);
                            GetAllDepartmentDoctors(department);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("BAG", e.getMessage());
                    }
                });
    }

    ////////////
    // department doctors
    public void GetAllDepartmentDoctors(Department department) {
        db.collection(Department.COLLECTION_NAME).document(department.getId()).
                collection(Doctor.COLLECTION_NAME).get().
                addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                            Doctor doctor = snapshot.toObject(Doctor.class);
                            doctor.setId(snapshot.getId());
                            if (departments.get(department.getId()).getId().equals(doctor.getDepartment_id())) {
                                departments.get(department.getId()).doctors.put(doctor.getClone_id(), doctor);
                            }
                            department_doctors_array.put(doctor.getClone_id(), doctor);
                            department_doctors_array1.add(doctor);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("BAG", e.getMessage());
                    }
                });
    }
    /////////////////
    // getDoctorAppointment_for_display_patient_appointment

    public void getDoctorAppointment_for_display_patient_appointment(PatentAppointment patentAppointment) {
        Doctor doctor = doctors_array.get(patentAppointment.getDoctor_id());
        ArrayList<Appointment> appointments = new ArrayList<>();
        db.collection(Governorate.COLLECTION_NAME).document(doctor.getGovernorate_id())
                .collection(Clinic.COLLECTION_NAME).document(doctor.getClinic_id())
                .collection(Doctor.COLLECTION_NAME).document(doctor.getId())
                .collection(Appointment.COLLECTION_NAME).document(patentAppointment.getAppointment_id())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Appointment appointment = documentSnapshot.toObject(Appointment.class);
                        appointment.setId(documentSnapshot.getId());
                        Log.e("htht", appointment.toString());
                        doctor_appointments_for_patient.put(appointment.getId(), appointment);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
    ////////////////
    ///update Appointment

    public void updateAppointment(Appointment appointment) {

        Doctor doctor = doctors_array.get(appointment.getDoctor_id());

        db.collection(Governorate.COLLECTION_NAME).document(doctor.getGovernorate_id())
                .collection(Clinic.COLLECTION_NAME).document(doctor.getClinic_id())
                .collection(Doctor.COLLECTION_NAME).document(doctor.getId())
                .collection(Appointment.COLLECTION_NAME).document(appointment.getId()).
                set(appointment).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.e("TAG123", "updated successfully.");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("TAG123", "update not complete" + e.getMessage().toString());
                    }
                });

    }

/////////////
///getDoctorAppointment_for_display_doctor_appointment

    public void getDoctorAppointment_for_display_doctor_appointment(Doctor doctor) {
        ArrayList<Appointment> appointments = new ArrayList<>();
        db.collection(Governorate.COLLECTION_NAME).document(doctor.getGovernorate_id())
                .collection(Clinic.COLLECTION_NAME).document(doctor.getClinic_id())
                .collection(Doctor.COLLECTION_NAME).document(doctor.getId())
                .collection(Appointment.COLLECTION_NAME).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                            Appointment appointment = snapshot.toObject(Appointment.class);
                            appointment.setId(snapshot.getId());
                            appointments.add(appointment);
                        }
                        doctor_appointments.put(doctor.getId(), appointments);
                        Log.e("hithere", doctor_appointments.get(doctor.getId()).toString());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    ///////////////
    // add patient to doctors
    public void AddPatienttodoctor(Patient patient, Appointment appointment) {
        Doctor doctor = doctors_array.get(appointment.getDoctor_id());

        db.collection(Governorate.COLLECTION_NAME).document(doctor.getGovernorate_id())
                .collection(Clinic.COLLECTION_NAME).document(doctor.getClinic_id())
                .collection(Doctor.COLLECTION_NAME).document(doctor.getId())
                .collection(Patient.COLLECTION_NAME_REQUEST).add(patient)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.e("thetag", "onSuccess: patient");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("thetag", "onfails: " + e.toString());

                    }
                });
    }

    ///////////////
    // delete patient to doctors
    public void deletePatientfromdoctor(Patient patient, Appointment appointment) {
        Doctor doctor = doctors_array.get(appointment.getDoctor_id());

        db.collection(Governorate.COLLECTION_NAME).document(doctor.getGovernorate_id())
                .collection(Clinic.COLLECTION_NAME).document(doctor.getClinic_id())
                .collection(Doctor.COLLECTION_NAME).document(doctor.getId())
                .collection(Patient.COLLECTION_NAME_REQUEST).document(patient.getId()).delete()
                .addOnSuccessListener(
                        new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        }
                ).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    /////////////
///getDoctorAppointment_for_display_appointment

    public void getDoctorPatient_for_display_doctor_appointment(Doctor doctor) {
        db.collection(Governorate.COLLECTION_NAME).document(doctor.getGovernorate_id())
                .collection(Clinic.COLLECTION_NAME).document(doctor.getClinic_id())
                .collection(Doctor.COLLECTION_NAME).document(doctor.getId())
                .collection(Patient.COLLECTION_NAME_REQUEST).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                            Patient patient = snapshot.toObject(Patient.class);
                            patient.setId(snapshot.getId());
                            if (LogedInPatient != null) {
                                patients_on_doctors.put(LogedInPatient.getId(), patient);
                            } else {
                                requested_patient.add(patient);
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }


/////////////////////////////


    public void storeMedication(Medicine med) {
        db.collection("patients").document("9urn7aTxmT48FftscO3u").collection("medicines").
                add(med).
                addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                         @Override
                                         public void onSuccess(DocumentReference documentReference) {
                                             AccessPoint.medicationAdapter.getData().add(med);
                                             AccessPoint.medicationAdapter.notifyDataSetChanged();
                                             med.setId(documentReference.getId());
                                             medicine = med;
                                             AccessPoint.activity.setAlarm(med.getId(), med.getName().hashCode(),
                                                     AccessPoint.activity.getHour(),
                                                     AccessPoint.activity.getMin());
                                         }
                                     }
                )

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }


    public void storeAllergy(Allergy allergy) {
        db.collection("patients").document("9urn7aTxmT48FftscO3u").collection("allergy").
                add(allergy).
                addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        AccessPoint.allergyAdapter.getData().add(allergy);
                        AccessPoint.allergyAdapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    public void storeOperation(PreviousOperation operation) {
        db.collection("patients").document("9urn7aTxmT48FftscO3u").collection("previous operations").
                add(operation).
                addOnSuccessListener(
                        new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                operation.setId(documentReference.getId());
                                AccessPoint.operationsAdapter.getData().add(operation);
                                AccessPoint.operationsAdapter.notifyDataSetChanged();
                            }
                        }
                )
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    public void storeChronicDisease(ChronicDisease chronicDisease) {
        db.collection("patients").document("9urn7aTxmT48FftscO3u").collection("chronic diseases").
                add(chronicDisease).
                addOnSuccessListener(
                        new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                chronicDisease.setId(documentReference.getId());
                                AccessPoint.diseasesAdapter.getData().add(chronicDisease);
                                AccessPoint.diseasesAdapter.notifyDataSetChanged();
                            }
                        }
                )
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

    }

    public void storeXray(String path) {
        storageRef.getStream().addOnSuccessListener(new OnSuccessListener<StreamDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(StreamDownloadTask.TaskSnapshot taskSnapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void uploadMedicalTest(Uri uri) {
        UploadTask uploadTask = null;
        storageRef = storageRef.child("medical_tests/" + uri.getLastPathSegment());
        uploadTask = storageRef.putFile(uri);

        uploadTask.addOnFailureListener(exception -> {

        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

            }
        });
    }

    public void storeMedicalTest(MedicalTest test) {
        db.collection("patients").document("9urn7aTxmT48FftscO3u").collection("medical tests").
                add(test).
                addOnSuccessListener(aVoid ->
                        System.out.println("**********************************"))
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    public void downloadMedicalTest(Uri uri) {
        StorageReference islandRef = storageRef.child("medical_tests/" + uri.getLastPathSegment());

        final long ONE_MEGABYTE = 1024 * 1024;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }

    public void storeMedicineSchedule(MedicineSchedule medicineSchedule) {
        db.collection("patients").document("9urn7aTxmT48FftscO3u")
                .collection("medicine schedule").
                add(medicineSchedule).
                addOnSuccessListener(aVoid ->
                        System.out.println("********************************** Schedule Added"))
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("********************************** failed to store schedule");
                    }
                });
    }

    public void getMedicineSchedule(String date) {
        System.out.println(date);
        medicineSchedules.clear();
        Query scheduleQuery = db.collection("patients")
                .document("9urn7aTxmT48FftscO3u").
                collection("medicine schedule")
                .whereEqualTo("date", date);
        scheduleQuery
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                MedicineSchedule ms = document.toObject(MedicineSchedule.class);
                                ms.setId(document.getId());
                                medicineSchedules.add(ms);
                                // list.add(ms);
                            }
                            AccessPoint.medicationShceduleAdapter.notifyDataSetChanged();
                        } else {

                        }

                    }


                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("********************************** failed to retrieve schedule");
                    }
                });


    }

    /*public void deleteSchedule(String id){
        db.collection("patients").document("9urn7aTxmT48FftscO3u").
                collection("medicine schedule").document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });
    }*/

    public void getAllergy(String id) {
        db.collection("patients").document("9urn7aTxmT48FftscO3u").
                collection("allergy").document(id)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Allergy a = documentSnapshot.toObject(Allergy.class);
                        a.setId(documentSnapshot.getId());
                        AppClass.allergy = a;
                    }
                });
    }

    public void getOperation(String id) {

        db.collection("patients").document("9urn7aTxmT48FftscO3u").
                collection("previous operations").document(id)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        PreviousOperation po = documentSnapshot.toObject(PreviousOperation.class);
                        po.setId(documentSnapshot.getId());
                        operation = po;
                    }
                });
    }

    public void getMedicine(String id) {

        db.collection("patients").document("9urn7aTxmT48FftscO3u").
                collection("medicines").document(id)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Medicine m = documentSnapshot.toObject(Medicine.class);
                        m.setId(documentSnapshot.getId());
                        medicine = m;

                    }
                });

    }

    public void getDisease(String id) {
        db.collection("patients").document("9urn7aTxmT48FftscO3u").
                collection("chronic disease").document(id)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        ChronicDisease d = documentSnapshot.toObject(ChronicDisease.class);
                        d.setId(documentSnapshot.getId());
                        disease = d;
                    }
                });
    }


    public ArrayList<Allergy> getAllergies() {
        ArrayList<Allergy> list = new ArrayList<>();
        db.collection("patients").document("9urn7aTxmT48FftscO3u").
                collection("allergy")
                .get().addOnSuccessListener(
                        queryDocumentSnapshots -> {
                            for (QueryDocumentSnapshot qs : queryDocumentSnapshots) {
                                Allergy allergy = qs.toObject(Allergy.class);
                                allergy.setId(qs.getId());
                                list.add(allergy);
                            }

                        }
                ).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

        return list;
    }

    public ArrayList<MedicalTest> getMedicalTests() {
        ArrayList<MedicalTest> list = new ArrayList<>();
        db.collection("patients").document("9urn7aTxmT48FftscO3u").
                collection("medical tests")
                .get().addOnSuccessListener(
                        queryDocumentSnapshots -> {
                            for (QueryDocumentSnapshot qs : queryDocumentSnapshots) {
                                MedicalTest medicalTest = qs.toObject(MedicalTest.class);
                                medicalTest.setId(qs.getId());
                                list.add(medicalTest);
                            }

                        }
                ).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

        return list;
    }

  /*  public ArrayList<MedicalTest> getMedicalTests() {
        db.collection("patients").document("9urn7aTxmT48FftscO3u").collection("medical test")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return null;
    }*/

    public ArrayList<Medicine> getMedications() {
        ArrayList<Medicine> list = new ArrayList<>();
        db.collection("patients").document("9urn7aTxmT48FftscO3u").collection("medicines")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Medicine medicine = document.toObject(Medicine.class);
                                medicine.setId(document.getId());
                                list.add(medicine);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return list;

    }

    public ArrayList<ChronicDisease> getChronicDiseases() {
        ArrayList<ChronicDisease> list = new ArrayList();
        db.collection("patients").document("9urn7aTxmT48FftscO3u").collection("chronic diseases")
                .get()
                .addOnSuccessListener(
                        queryDocumentSnapshots -> {
                            for (QueryDocumentSnapshot qs : queryDocumentSnapshots) {
                                ChronicDisease disease = qs.toObject(ChronicDisease.class);
                                disease.setId(qs.getId());
                                System.out.println("**************" + disease.getId());
                                list.add(disease);
                                System.out.println("********************************" + list.size());
                            }

                        }
                ).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
        return list;
    }

    public ArrayList<PreviousOperation> getPreviousOperation() {
        ArrayList<PreviousOperation> list = new ArrayList<>();
        db.collection("patients").document("9urn7aTxmT48FftscO3u")
                .collection("previous operations")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PreviousOperation previousOperation = document.toObject(PreviousOperation.class);
                                previousOperation.setId(document.getId());
                                list.add(previousOperation);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return list;
    }

    public ArrayList<XRay> getXrayDocs() {
        db.collection("")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return null;
    }


    public void updateAllergy(Allergy allergy) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", allergy.getName());
        map.put("started", allergy.getStarted());
        db.collection("allergy").document(allergy.getId())
                .update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
    }

    public void updateDisease(ChronicDisease disease) {
        Map<String, Object> map = new HashMap<>();

        map.put("name", disease.getName());
        map.put("medicineName", disease.getMedicineName());


        db.collection("patients").document("9urn7aTxmT48FftscO3u")
                .collection("chronic disease").document(disease.getId())
                .update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        AccessPoint.diseasesAdapter.getData().set(AccessPoint.diseasesAdapter.getUpdatedIndex(), disease);
                        AccessPoint.diseasesAdapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
    }

    public void updateMedicine(Medicine medicine) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", medicine.getName());
        map.put("type", medicine.getType());
        map.put("duration", medicine.getDuration());
        map.put("amount", medicine.getAmount());

        db.collection("patients").document("9urn7aTxmT48FftscO3u")
                .collection("medicines").document(medicine.getId())
                .update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        AccessPoint.medicationAdapter.getData().
                                set(AccessPoint.medicationAdapter.getUpdatedIndex(), medicine);
                        AccessPoint.medicationAdapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
    }

    public void updatePreviousOperation(PreviousOperation previousOperation) {
        Map<String, Object> map = new HashMap<>();
        map.put("clinicName", previousOperation.getClinicName());
        map.put("doctorName", previousOperation.getDoctorName());

        db.collection("patients")
                .document("9urn7aTxmT48FftscO3u")
                .collection("previous operation")
                .document(previousOperation.getId()).update(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
    }

    public void deleteMedicine(String id) {
        db.collection("patients").document("9urn7aTxmT48FftscO3u").
                collection("medicines").document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });
    }

}

