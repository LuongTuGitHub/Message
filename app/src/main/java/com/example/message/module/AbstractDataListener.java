package com.example.message.module;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public abstract class AbstractDataListener implements DataListener{

    @Override
    public void onChanged(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}
