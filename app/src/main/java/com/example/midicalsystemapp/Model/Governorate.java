package com.example.midicalsystemapp.Model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Governorate {

    @DocumentId
    String id;
    String name;
    Timestamp created_at;
    Timestamp deleted_at;
    Timestamp updated_at;
    Timestamp remebertoken;

    @Exclude
    public  Map<String, Clinic> clinics = new HashMap<>();
    public ArrayList<Clinic> clinics_array = new ArrayList<>();

   public ArrayList<Clinic> getclinics(){
        clinics_array.clear();
        for (Map.Entry<String, Clinic> set :
                clinics.entrySet()) {
            clinics_array.add(set.getValue());
        }
        return clinics_array;
    }
    public static final String COLLECTION_NAME = "Governorates";

    public Governorate() {
    }

    public Governorate(String id, String name, Timestamp created_at,
                       Timestamp deleted_at, Timestamp updated_at, Timestamp remebertoken) {

        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
        this.updated_at = updated_at;
        this.remebertoken = remebertoken;

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

    @Override
    public String toString() {
        return "Governorate{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", created_at=" + created_at.toString() +
                ", deleted_at=" + deleted_at.toString() +
                ", updated_at=" + updated_at.toString() +
                ", remebertoken='" + remebertoken.toString() + '\'' +
                '}';
    }
}
