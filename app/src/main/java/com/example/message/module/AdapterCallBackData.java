package com.example.message.module;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public abstract class AdapterCallBackData {
    public void change() {

    }

    public void cancel() {

    }

    public void change(@NonNull DataSnapshot snapshot) {

    }

    public void cancel(@NonNull DatabaseError error) {

    }
}
