package com.example.midicalsystemapp.Model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentId;

public class Allergy {
    public static final String COLLECTION_NAME = "Allergy";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_CREATED_AT = "created_at";
    public static final String COL_DELETED_AT = "deleted_at";
    public static final String COL_UPDATED_AT = "updated_at";


    @DocumentId
    String id;
    String name;
    String started;
    Timestamp created_at;
    Timestamp deleted_at;
    Timestamp updated_at;

    public Allergy(String id, String name, String started, Timestamp created_at, Timestamp deleted_at, Timestamp updated_at) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
        this.updated_at = updated_at;
        this.started = started;
    }


    public Allergy() {
    }

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
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
}
