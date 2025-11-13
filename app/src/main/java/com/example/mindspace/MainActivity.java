package com.example.mindspace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_page);
        Intent intent = new Intent(MainActivity.this, AuthStack.class);
        startActivity(intent);

    }

}
