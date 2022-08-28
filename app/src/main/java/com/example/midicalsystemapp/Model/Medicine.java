package com.example.midicalsystemapp.Model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentId;

public class Medicine {
    public static final String COLLECTION_NAME = "Medicine";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_FIRST_TIME = "first_time";
    public static final String COL_DURATION = "duration";

    @DocumentId
    String id;
    String name;
    String type;
    int amount;
    int duration;
    Timestamp firstTime;

    public Medicine() {
    }

    public Medicine(String id, String name, String type, int amount, int duration, Timestamp firstTime) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.firstTime = firstTime;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Timestamp getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Timestamp firstTime) {
        this.firstTime = firstTime;
    }
}

