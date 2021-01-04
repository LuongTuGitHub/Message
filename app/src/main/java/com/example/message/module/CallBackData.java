package com.example.message.module;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public interface CallBackData {
    void change();

    void cancel();

    void change(@NonNull DataSnapshot snapshot);

    void cancel(@NonNull DatabaseError error);
}
