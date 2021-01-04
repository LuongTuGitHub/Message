package com.example.message.module;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface CallBackAuth {
    void isSuccess();

    void isSuccess(Task<AuthResult> task);

    void isFail();

    void isFail(Task<AuthResult> task);
}
