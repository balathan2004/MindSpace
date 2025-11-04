package com.example.mindspace;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import java.util.Arrays;
import java.util.List;

public class NotePage extends AppCompatActivity {

    LinearLayout chipGroup;

    private final List<String> names = Arrays.asList("Cowsika", "Siri", "Harini", "Suvetha");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_page);
        chipGroup = findViewById(R.id.tag_chip_group);
        renderNameChips();
    }

    private void renderNameChips() {
        chipGroup.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);
        for (String name : names) {
            Button button = (Button) inflater.inflate(R.layout.button_tag, chipGroup, false);
            button.setText(name);


            button.setOnClickListener(v -> {
                // To get the selected name, you can cast the view back to Chip
                Chip clickedChip = (Chip) v;
                String selectedName = clickedChip.getText().toString();

                // You can add your actual filtering logic here!
                Toast.makeText(NotePage.this, "Selected: " + selectedName, Toast.LENGTH_SHORT).show();
            });

            chipGroup.addView(button);
        }
    }


}
