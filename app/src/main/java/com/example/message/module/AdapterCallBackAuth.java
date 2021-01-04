package com.example.message.module;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public abstract class AdapterCallBackAuth {
    public void isSuccess() {

    }

    public void isSuccess(Task<AuthResult> task) {

    }

    public void isFail() {

    }

    public void isFail(Task<AuthResult> task) {

    }

}
