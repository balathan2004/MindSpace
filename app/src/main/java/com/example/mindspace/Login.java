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
            String emailValue = validateInput(email,6);
            Log.i("Login", "email:" + emailValue);
            Log.i("Login", password.getText().toString());

        });


    }

    String validateInput(EditText input,int minLenght) {

        String value = input.getText().toString();

        if (value.isEmpty()) {
            input.setError("Input is empty");
            return null;
        }
        if(value.length()<minLenght){
            input.setError("Input should be "+minLenght+"chars");
            return null;
        }
        return value;

    }


}
