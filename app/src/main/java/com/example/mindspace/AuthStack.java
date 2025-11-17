package com.example.mindspace;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AuthStack extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("userCred", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            startMainStack();
        } else {
            Utils.Navigate(this, Login.class, false);

        }
    }

    private void startMainStack() {

        Utils.Navigate(this, Home.class, true);
        finish();
    }

}
