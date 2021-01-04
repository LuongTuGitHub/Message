package com.example.message.module;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;

public abstract class AdapterCallBackQuery {
    public void isSuccess() {

    }

    public void isFail() {

    }

    public void isSuccess(@NonNull Task<Void> task) {

    }

    public void isFail(@NonNull Task<Void> task) {

    }

    public void function(@NonNull Task<Void> task) {

    }
}
