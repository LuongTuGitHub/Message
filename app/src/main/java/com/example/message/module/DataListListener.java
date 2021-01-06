package com.example.message.module;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public interface DataListListener {

    void onAdded(@NonNull DataSnapshot snapshot);

    void onChanged(@NonNull DataSnapshot snapshot);

    void onRemoved(@NonNull DataSnapshot snapshot);

    void onMoved(@NonNull DataSnapshot snapshot);

    void onCancelled(@NonNull DatabaseError error);
}
