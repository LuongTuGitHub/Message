package com.example.message.module;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public interface DataListener {
    void onChanged(@NonNull DataSnapshot snapshot);

    void onCancelled(@NonNull DatabaseError error);
}
