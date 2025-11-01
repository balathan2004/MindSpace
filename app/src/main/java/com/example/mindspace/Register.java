



package com.example.mindspace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Register extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Button btn = findViewById(R.id.submit);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);

        TextView navigateText=findViewById(R.id.navigateText);

        navigateText.setOnClickListener(e->{
            Intent intent=new Intent(Register.this,Login.class);
            startActivity(intent);
        });

        btn.setOnClickListener(e -> {
            String emailValue = new InputValidator(email).validate();
            String passwordValue = new InputValidator(password).validate();

            if (emailValue != null && passwordValue != null) {
                Log.i("Login", "Email: " + emailValue);
                Log.i("Login", "Password: " + passwordValue);
                // proceed with login
            } else {
                Log.i("Login", "Validation failed");
            }
        });
    }
}
