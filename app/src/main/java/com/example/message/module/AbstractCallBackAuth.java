package com.example.message.module;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public abstract class AbstractCallBackAuth implements CallBackAuth {

    @Override
    public void function(Task<AuthResult> task) {

    }
}
