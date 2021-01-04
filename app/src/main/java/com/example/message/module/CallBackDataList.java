package com.example.message.module;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public interface CallBackDataList {
    void add();

    void change();

    void remove();

    void move();

    void cancel();

    void add(@NonNull DataSnapshot snapshot);

    void change(@NonNull DataSnapshot snapshot);

    void remove(@NonNull DataSnapshot snapshot);

    void move(@NonNull DataSnapshot snapshot);

    void cancel(@NonNull DatabaseError error);
}
