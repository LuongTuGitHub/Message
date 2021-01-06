package com.example.message.module;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public interface DataChangeListener {
    void onChanged(@NonNull DataSnapshot snapshot, int Flag);

    void onCancelled(@NonNull DatabaseError error);
}
