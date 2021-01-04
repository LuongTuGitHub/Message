package com.example.message.module;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;

public interface CallBackQuery {
    void function(@NonNull Task<Void> task);
}
