package com.example.midicalsystemapp.Model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Department {
    @DocumentId
    String id;
    String name;
    Timestamp created_at;
    Timestamp deleted_at;
    Timestamp updated_at;
    Timestamp remebertoken;
    public static final String COLLECTION_NAME = "Departments";
    @Exclude
    public  Map<String, Doctor> doctors = new HashMap<>();
    public ArrayList<Doctor> doctors_array = new ArrayList<>();


    public ArrayList<Doctor> getdoctors(){
        doctors_array.clear();
        for (Map.Entry<String, Doctor> set :
                doctors.entrySet()) {
            doctors_array.add(set.getValue());
        }
        return doctors_array;
    }

    @Exclude
    public static Map<String, Doctor> Department_doctors = new HashMap<>();

    public Department() {
    }

    public Department(String id, String name, Timestamp created_at, Timestamp deleted_at, Timestamp updated_at, Timestamp remebertoken) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
        this.updated_at = updated_at;
        this.remebertoken = remebertoken;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", created_at=" + created_at +
                ", deleted_at=" + deleted_at +
                ", updated_at=" + updated_at +
                ", remebertoken=" + remebertoken +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Timestamp getRemebertoken() {
        return remebertoken;
    }

    public void setRemebertoken(Timestamp remebertoken) {
        this.remebertoken = remebertoken;
    }
}
