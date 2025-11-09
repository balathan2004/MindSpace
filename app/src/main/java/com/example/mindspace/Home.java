package com.example.mindspace;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    TextView headerTitle;


    @Override
    protected void onCreate(Bundle savedInstaceState) {

        super.onCreate(savedInstaceState);

        setContentView(R.layout.home_page);
        headerTitle=findViewById(R.id.headerTitle);
        headerTitle.setText("Home");


    }

}
