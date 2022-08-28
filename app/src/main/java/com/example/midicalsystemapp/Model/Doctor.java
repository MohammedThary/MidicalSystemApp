package com.example.midicalsystemapp.Model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Doctor {

    @DocumentId
    String id;
    String username;
    String email;
    String Password;
    String image;
    String evaluation;
    String department_id;
    String clinic_id;
    String cv_link;
    String specialize;
    String clone_id;
    String governorate_id;
    Timestamp created_at;
    Timestamp deleted_at;
    Timestamp updated_at;
    Timestamp remebertoken;
    @Exclude
    public static Map<String, ArrayList<Patient>> request_patiants = new HashMap<>();
    @Exclude
    public static Map<String, ArrayList<Patient>> waitning_patiants = new HashMap<>();
    @Exclude
    public static Map<String, ArrayList<Patient>> request_to_folow_patiants = new HashMap<>();
    @Exclude
    public static Map<String, ArrayList<Patient>> folowed_patiants = new HashMap<>();

    public static final String COLLECTION_NAME = "Doctors";

    public Doctor() {
    }

    public Doctor(String id,String clone_id,   String governorate_id,String username, String email, String password, String image, String evaluation, String department_id, String clinic_id, String cv_link,String specialize, Timestamp created_at, Timestamp deleted_at, Timestamp updated_at,Timestamp remebertoken) {
        this.id = id;
        this.clinic_id = clinic_id;
        this.governorate_id = governorate_id;
        this.username = username;
        this.email = email;
        this.Password = password;
        this.image = image;
        this.evaluation = evaluation;
        this.department_id = department_id;
        this.clinic_id = clinic_id;
        this.cv_link = cv_link;
        this.specialize = specialize;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
        this.updated_at = updated_at;
        this.remebertoken = remebertoken;
    }

    public String getClone_id() {
        return clone_id;
    }

    public String getGovernorate_id() {
        return governorate_id;
    }

    public void setGovernorate_id(String governorate_id) {
        this.governorate_id = governorate_id;
    }

    public void setClone_id(String clone_id) {
        this.clone_id = clone_id;
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

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getClinic_id() {
        return clinic_id;
    }

    public void setClinic_id(String clinic_id) {
        this.clinic_id = clinic_id;
    }

    public String getCv_link() {
        return cv_link;
    }

    public void setCv_link(String cv_link) {
        this.cv_link = cv_link;
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

    public String getSpecialize() {
        return specialize;
    }

    public void setSpecialize(String specialize) {
        this.specialize = specialize;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", Password='" + Password + '\'' +
                ", image='" + image + '\'' +
                ", evaluation='" + evaluation + '\'' +
                ", department_id='" + department_id + '\'' +
                ", clinic_location='" + clinic_id + '\'' +
                ", cv_link='" + cv_link + '\'' +
                ", specialize='" + specialize + '\'' +
                ", created_at=" + created_at +
                ", deleted_at=" + deleted_at +
                ", updated_at=" + updated_at +
                '}';
    }
}

