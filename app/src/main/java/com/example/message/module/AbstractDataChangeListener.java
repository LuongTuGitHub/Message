package com.example.message.module;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public abstract class AbstractDataChangeListener implements DataChangeListener {

    @Override
    public void onChanged(@NonNull DataSnapshot snapshot, int Flag) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}
