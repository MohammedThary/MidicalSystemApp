package com.example.midicalsystemapp.Model;

import android.net.Uri;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentId;

public class MedicalTest {
    @DocumentId
    String id;
    String title;
    Uri uri;
    Timestamp created_at;
    Timestamp updated_at;

    public MedicalTest() {
    }

    public MedicalTest(String id, String title, Uri uri, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.title = title;
        this.uri = uri;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
