package com.example.message.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.message.R;
import com.example.message.module.Data;
import com.example.message.object.Account;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        new Data<Account>().getDataList(Account.class).remove(null);
    }
}