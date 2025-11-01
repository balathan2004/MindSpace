package com.example.mindspace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button btn = findViewById(R.id.submit);

        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);


        btn.setOnClickListener(e -> {
            String emailValue = new InputValidator(email).validate();
            Log.i("Login", "email:" + emailValue);
            Log.i("Login", password.getText().toString());

        });


    }




}
