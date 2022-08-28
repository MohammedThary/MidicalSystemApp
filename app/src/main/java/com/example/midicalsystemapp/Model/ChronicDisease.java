package com.example.midicalsystemapp.Model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentId;

public class ChronicDisease {
    public static final String COLLECTION_NAME = "ChronicDisease";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_MEDICINE_NAME = "medicine_name";
    public static final String COL_CREATED_AT = "created_at";
    public static final String COL_DELETED_AT = "deleted_at";

    @DocumentId
    String id;
    String name;
    String medicineName;
    String started;
    Timestamp created_at;
    Timestamp deleted_at;

    public ChronicDisease() {
    }

    public ChronicDisease(String id, String name, String medicineName, Timestamp created_at, Timestamp deleted_at) {
        this.id = id;
        this.name = name;
        this.medicineName = medicineName;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
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

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
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
}
