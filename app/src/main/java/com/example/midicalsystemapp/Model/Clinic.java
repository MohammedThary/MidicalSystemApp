package com.example.midicalsystemapp.Model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Clinic {

    @DocumentId
    String id;
    String name;
    String image;
    String governorate_id;
    String location;
    String specialize;
    Timestamp created_at;
    Timestamp deleted_at;
    Timestamp updated_at;
    Timestamp remebertoken;
    String Lat;
    String Lng;
    @Exclude
    public  Map<String, Doctor> clinic_doctors = new HashMap<>();
    public ArrayList<Doctor> doctors_array = new ArrayList<>();

    public ArrayList<Doctor> getdoctors(){
        doctors_array.clear();
        for (Map.Entry<String, Doctor> set :
                clinic_doctors.entrySet()) {
            doctors_array.add(set.getValue());
        }
        return doctors_array;
    }

    public static final String COLLECTION_NAME = "Clinics";

    public Clinic() {
    }

    public Clinic(String id, String name, String image, String governorate_id, String location, String specialize,
                  Timestamp created_at, Timestamp deleted_at, Timestamp updated_at, Timestamp remebertoken,String Lat,String Lng) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.governorate_id = governorate_id;
        this.location = location;
        this.specialize = specialize;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
        this.updated_at = updated_at;
        this.remebertoken = remebertoken;
        this.Lat = Lat;
        this.Lng = Lng;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGovernorate_id() {
        return governorate_id;
    }

    public void setGovernorate_id(String governorate_id) {
        this.governorate_id = governorate_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSpecialize() {
        return specialize;
    }

    public void setSpecialize(String specialize) {
        this.specialize = specialize;
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

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLng() {
        return Lng;
    }

    public void setLng(String lng) {
        Lng = lng;
    }

    @Override
    public String toString() {
        return "Clinic{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", governorate_id='" + governorate_id + '\'' +
                ", location='" + location + '\'' +
                ", specialize='" + specialize + '\'' +
                ", created_at=" + created_at +
                ", deleted_at=" + deleted_at +
                ", updated_at=" + updated_at +
                ", remebertoken='" + remebertoken + '\'' +
                ", doctors='"+  clinic_doctors.toString()+
                '}';
    }
}
