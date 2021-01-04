package com.example.message.module;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class User {

    private final FirebaseAuth fAuth;
    private final FirebaseUser fUser;


    public User() {
        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();
    }

    public boolean isVerity() {
        if (fUser == null) {
            return false;
        }
        fUser.reload();
        return fUser.isEmailVerified();
    }

    public void login(@NonNull String user, @NonNull String password) {
        fAuth.signInWithEmailAndPassword(user, password);
    }

    public void login(@NonNull String user, @NonNull String password, @NonNull CallBackAuth callBackAuth) {

        if (callBackAuth != null) {
            fAuth.signInWithEmailAndPassword(user, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        callBackAuth.isSuccess();
                        callBackAuth.isSuccess(task);
                    } else {
                        callBackAuth.isFail(task);
                        callBackAuth.isFail();
                    }
                }
            });
        } else {
            login(user, password);
        }
    }

    public void login(@NonNull String user, @NonNull String password, @NonNull AdapterCallBackAuth adapterCallBackAuth) {
        if (adapterCallBackAuth != null) {
            fAuth.signInWithEmailAndPassword(user, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        adapterCallBackAuth.isSuccess();
                        adapterCallBackAuth.isSuccess(task);
                    } else {
                        adapterCallBackAuth.isFail();
                        adapterCallBackAuth.isFail(task);
                    }
                }
            });
        } else {
            login(user, password);
        }
    }

    public void signUp(String user, String password) {
        fAuth.createUserWithEmailAndPassword(user, password);
    }

    public void signUp(String user, String password, @NonNull CallBackAuth callBackAuth) {
        if (callBackAuth != null) {
            fAuth.createUserWithEmailAndPassword(user, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        callBackAuth.isSuccess();
                        callBackAuth.isSuccess(task);
                    } else {
                        callBackAuth.isFail();
                        callBackAuth.isFail(task);
                    }
                }
            });
        } else {
            signUp(user, password);
        }
    }

    public void signUp(String user, String password, @NonNull AdapterCallBackAuth adapterCallBackAuth) {
        if (adapterCallBackAuth != null) {
            fAuth.createUserWithEmailAndPassword(user, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        adapterCallBackAuth.isSuccess();
                        adapterCallBackAuth.isSuccess(task);
                    } else {
                        adapterCallBackAuth.isFail();
                        adapterCallBackAuth.isFail(task);
                    }
                }
            });
        } else {
            signUp(user, password);
        }
    }
}
