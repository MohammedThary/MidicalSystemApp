package com.example.midicalsystemapp.Model;

import android.util.Log;

import com.google.firebase.firestore.DocumentId;

import java.util.Arrays;
import java.util.List;


public class Appointment {
    @DocumentId
    String id;
    String day;
    String Doctor_id;
    List<String> available_time;
    List<String> booking_time;
    public static final String COLLECTION_NAME = "appointments";


    public Appointment() {
    }

    public Appointment(String id, String day, String doctor_id, List<String> available_time, List<String> booking_time) {
        this.id = id;
        this.day = day;
        Doctor_id = doctor_id;
        this.available_time = available_time;
        this.booking_time = booking_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDoctor_id() {
        return Doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        Doctor_id = doctor_id;
    }

    public List<String> getAvailable_time() {
        return available_time;
    }

    public void setAvailable_time(List<String> available_time) {
        this.available_time = available_time;
    }

    public List<String> getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(List<String> booking_time) {
        this.booking_time = booking_time;
    }

    public static String getCollectionName() {
        return COLLECTION_NAME;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id='" + id + '\'' +
                ", day='" + day + '\'' +
                ", Doctor_id='" + Doctor_id + '\'' +
                ", available_time=" + available_time +
                ", booking_time=" + booking_time +
                '}';
    }
}
