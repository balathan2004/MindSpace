package com.example.mindspace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button btn = findViewById(R.id.submit);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        TextView navigateText = findViewById(R.id.navigateText);


        navigateText.setOnClickListener(e -> {
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
        });

        btn.setOnClickListener(e -> {
            String emailValue = new InputValidator(email).setMinLength(6).validate();
            String passwordValue = new InputValidator(password).setMinLength(6).validate();

            if (emailValue != null && passwordValue != null) {
                Log.i("Login", "Email: " + emailValue);
                Log.i("Login", "Password: " + passwordValue);
                Intent Home = new Intent(Login.this, Home.class);
                SharedPreferences prefs = getSharedPreferences("userCred", MODE_PRIVATE);
                prefs.edit().putBoolean("isLoggedIn", true).apply();

                Intent intent = new Intent(Login.this, Home.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
                finish();

            } else {
                Log.i("Login", "Validation failed");
            }
        });
    }
}
