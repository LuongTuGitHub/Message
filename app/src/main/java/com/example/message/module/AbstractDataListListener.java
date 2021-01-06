package com.example.message.module;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public abstract class AbstractDataListListener implements DataListListener{


    @Override
    public void onAdded(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onChanged(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onRemoved(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onMoved(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}
