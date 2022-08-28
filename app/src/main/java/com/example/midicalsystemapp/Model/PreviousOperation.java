package com.example.midicalsystemapp.Model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentId;

public class PreviousOperation {
    public static final String COLLECTION_NAME = "Patient";
    public static final String COL_ID = "id";
    public static final String COL_DOCTOR_NAME = "doctor_name";
    public static final String COL_OPERATION_NAME = "operation_name";
    public static final String COL_CLINIC_NAME = "clinic_name";
    public static final String COL_CREATED_AT = "created_at";
    public static final String COL_UPDATED_AT = "updated_at";
    public static final String COL_DATE = "date";

    @DocumentId
    String id;
    String clinicName;
    Timestamp date;
    String doctorName;
    String operationName;
    Timestamp created_at;
    Timestamp updated_at;

    public PreviousOperation() {
    }

    public PreviousOperation(String id, String clinicName, Timestamp date, String doctorName, String operationName, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.clinicName = clinicName;
        this.date = date;
        this.doctorName = doctorName;
        this.operationName = operationName;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
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
