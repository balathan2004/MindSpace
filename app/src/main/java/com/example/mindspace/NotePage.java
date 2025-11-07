package com.example.mindspace;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotePage extends AppCompatActivity {

    ChipGroup chipGroup;

    LinearLayout tag_input_container;

    private final List<String> initNames = Arrays.asList("Cowsika", "Siri", "Harini", "Suvetha");
    private List<String> names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_page);
        chipGroup = findViewById(R.id.tag_chip_group);
        tag_input_container = findViewById(R.id.tag_input_container);

        TextView header = findViewById(R.id.headerTitle);

        header.setText("Notes");

        renderNameChips();


        EditText title = findViewById(R.id.note_title);
        EditText note = findViewById(R.id.note_text);
        Button submitButton = findViewById(R.id.submitNote);
        addTagInput();


        submitButton.setOnClickListener(e -> {
            String note_title = new InputValidator(title).setMinLength(20).validate();
            String note_text = new InputValidator(note).setMinLength(20).validate();

            if (note_title == null || note_text == null) {
                Log.i("Note", "Value is empty");
                return;
            }

            Log.i("Note", note_title);
            Log.i("Note", note_text);

        });

    }


    private void addTagInput() {

        LayoutInflater inflater = LayoutInflater.from(this);
        EditText input = (EditText) inflater.inflate(R.layout.input_tag_note, tag_input_container, false);

        tag_input_container.addView(input);
        input.setOnEditorActionListener((v, actionId, event) -> {

            if (EditorInfo.IME_ACTION_SEND == actionId) {

                String tag_name = new InputValidator(input).setMinLength(3).validate();

                if (tag_name != null) {
                    Log.i("Note", tag_name);
                    names.add(tag_name);
                    renderNameChips();
                    input.setText("");
                }

                return true;
            }
            return false;
        });


        input.requestFocus();
    }

    private void renderNameChips() {
        chipGroup.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);

        for (String name : names) {

            Chip button = (Chip) renderSingleChip(inflater, name);

            chipGroup.addView(button);
        }

        if (!names.isEmpty()) {
            chipGroup.addView(renderRemoveChip(inflater));
        }
        // If the left side is TRUE, the right side is executed.


    }


    private Button renderSingleChip(LayoutInflater inflater, String name) {
        Chip button = (Chip) inflater.inflate(R.layout.button_tag, chipGroup, false);
        button.setText(name);


        button.setOnClickListener(v -> {
            // To get the selected name, you can cast the view back to Chip
            Button clickedChip = (Button) v;
            String selectedName = clickedChip.getText().toString();

            ViewGroup parent = (ViewGroup) button.getParent();

            parent.removeView(button);
            names.remove(selectedName);


            // You can add your actual filtering logic here!
            Toast.makeText(NotePage.this, "Removed: " + selectedName, Toast.LENGTH_SHORT).show();
        });
        return button;
    }

    private Button renderRemoveChip(LayoutInflater inflater) {
        Chip button = (Chip) inflater.inflate(R.layout.button_tag, chipGroup, false);
        button.setText("Reset");

        button.setCloseIconVisible(false);


        button.setOnClickListener(v -> {
            // To get the selected name, you can cast the view back to Chip
            Chip clickedChip = (Chip) v;
            String selectedName = clickedChip.getText().toString();

            names.clear();


            this.renderNameChips();
            chipGroup.removeView(button);

            // You can add your actual filtering logic here!
            Toast.makeText(NotePage.this, "Reset to default", Toast.LENGTH_SHORT).show();
        });
        return button;
    }

}
