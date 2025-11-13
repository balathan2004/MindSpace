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
        }else{
            Intent intent = new Intent(AuthStack.this, Login.class);
            startActivity(intent);
        }
    }

    private void startMainStack() {
        Intent intent = new Intent(AuthStack.this, Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}
