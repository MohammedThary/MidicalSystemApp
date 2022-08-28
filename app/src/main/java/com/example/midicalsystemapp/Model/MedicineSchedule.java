package com.example.midicalsystemapp.Model;

import com.google.firebase.firestore.DocumentId;

public class MedicineSchedule {
    @DocumentId
    String id;
    String medId;
    String date;
    boolean checked;

    public MedicineSchedule() {

    }

    public MedicineSchedule(String id, String medId, String date, boolean checked) {
        this.id = id;
        this.medId = medId;
        this.date = date;
        this.checked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedId() {
        return medId;
    }

    public void setMedId(String medId) {
        this.medId = medId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
