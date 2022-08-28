package com.example.midicalsystemapp.Model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentId;

import java.util.HashMap;

public class Patient {
    @DocumentId
    String id;
    String username;
    String email;
    String Password;
    String image;
    HashMap<String,String> doctors_note;
    Timestamp created_at;
    Timestamp deleted_at;
    Timestamp updated_at;



    public static final String COLLECTION_NAME = "patients";
    public static final String COLLECTION_NAME_WAITING = "waiting_patients";
    public static final String COLLECTION_NAME_REQUEST = "requested_patients";
    public Patient() {
    }

    public Patient(String id, String username, String email, String password, String image, Timestamp created_at, Timestamp deleted_at, Timestamp updated_at,HashMap<String, String> doctors_note
   ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.Password = password;
        this.image = image;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
        this.updated_at = updated_at;
       this.doctors_note = doctors_note;
    }


    public HashMap<String, String> getDoctors_note() {
        return doctors_note;
    }

    public void setDoctors_note(HashMap<String, String> doctors_note) {
        this.doctors_note = doctors_note;
    }

    public static String getCollectionName() {
        return COLLECTION_NAME;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", Password='" + Password + '\'' +
                ", image='" + image + '\'' +
                ", doctors_note=" + doctors_note +
                ", created_at=" + created_at +
                ", deleted_at=" + deleted_at +
                ", updated_at=" + updated_at +
                '}';
    }
}

