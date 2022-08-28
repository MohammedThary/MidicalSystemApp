package com.example.midicalsystemapp.Model;

import static com.example.midicalsystemapp.App.AppClass.patentAppointments;

import android.util.Log;

import com.example.midicalsystemapp.App.AppClass;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PatentAppointment {
    @DocumentId
    String id;
    String appointment_id;
    String doctor_id;
    String time;
    Timestamp created_at;
    Timestamp deleted_at;
    Timestamp updated_at;

    public static final String COLLECTION_NAME = "appointments";



    public PatentAppointment() {
    }

    public PatentAppointment(String id, String appointment_id, String doctor_id, String time, Timestamp created_at, Timestamp deleted_at, Timestamp updated_at) {
        this.id = id;
        this.appointment_id = appointment_id;
        this.doctor_id = doctor_id;
        this.time = time;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(String appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public static String getCollectionName() {
        return COLLECTION_NAME;
    }

    @Override
    public String toString() {
        return "PatentAppointment{" +
                "id='" + id + '\'' +
                ", appointment_id='" + appointment_id + '\'' +
                ", doctor_id='" + doctor_id + '\'' +
                ", time='" + time + '\'' +
                ", created_at=" + created_at +
                ", deleted_at=" + deleted_at +
                ", updated_at=" + updated_at +
                '}';
    }
}


