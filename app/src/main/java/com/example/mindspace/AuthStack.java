package com.example.mindspace;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mindspace.api_response.UserProfile;
import com.google.gson.Gson;

public class AuthStack extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("userCred", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);


        String userCred = prefs.getString("user", null);

        Log.e("console", "Raw userCred from prefs: " + userCred);


        UserProfile userProfile = null;

        if (userCred == null) {
            Log.e("console", "cred is null");
        } else {
            Log.e("console", userCred);
            Gson gson = new Gson();
            userProfile = gson.fromJson(userCred, UserProfile.class);
        }


        AuthState state = (AuthState) getApplication();


        if (isLoggedIn && userProfile!=null) {
            state.setLoggedIn(isLoggedIn, userProfile);
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
