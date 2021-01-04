package com.example.message.module;

import com.google.android.gms.tasks.Task;

public interface CallBackQuery {
    void isSuccess();

    void isFail();

    void isSuccess(Task<Void> task);

    void isFail(Task<Void> task);
}
