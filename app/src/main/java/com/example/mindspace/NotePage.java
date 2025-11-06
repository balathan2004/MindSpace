package com.example.mindspace;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotePage extends AppCompatActivity {

    LinearLayout chipGroup;

    private final List<String> initNames = Arrays.asList("Cowsika", "Siri", "Harini", "Suvetha");
    private List<String> names = new ArrayList<String>(initNames);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_page);
        chipGroup = findViewById(R.id.tag_chip_group);

        renderNameChips();


        EditText title = findViewById(R.id.note_title);
        EditText note = findViewById(R.id.note_text);
        Button submitButton = findViewById(R.id.submitNote);


        submitButton.setOnClickListener(e -> {
            String note_title = new InputValidator(title).validate();
            String note_text = new InputValidator(note).validate();

            if (note_title == null || note_text == null) {
                Log.i("Note", "Value is empty");
                return;
            }

            Log.i("Note", note_title);
            Log.i("Note", note_text);

        });

    }


    private void addTagInput(LayoutInflater inflater) {


        EditText tag_input = (EditText) inflater.inflate(R.layout.input_tag_note, chipGroup, false);


        tag_input.setOnEditorActionListener((v, actionId, event) -> {

            if (EditorInfo.IME_ACTION_SEND == actionId) {


                String tag_name = new InputValidator(tag_input).validate();

                if (tag_name != null) {
                    Log.i("Note", tag_name);
                    names.add(tag_name);
                    renderNameChips();
                }
                return true;
            }
            return false;
        });
        chipGroup.addView(tag_input);
    }

    private void renderNameChips() {
        chipGroup.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);
        addTagInput(inflater);
        for (String name : names) {

            Button button = renderSingleChip(inflater, name);

            chipGroup.addView(button);
        }
        chipGroup.addView(renderRemoveChip(inflater));

    }


    private Button renderSingleChip(LayoutInflater inflater, String name) {
        Button button = (Button) inflater.inflate(R.layout.button_tag, chipGroup, false);
        button.setText(name);


        button.setOnClickListener(v -> {
            // To get the selected name, you can cast the view back to Chip
            Button clickedChip = (Button) v;
            String selectedName = clickedChip.getText().toString();

            ViewGroup parent = (ViewGroup) button.getParent();

            parent.removeView(button);


            // You can add your actual filtering logic here!
            Toast.makeText(NotePage.this, "Removed: " + selectedName, Toast.LENGTH_SHORT).show();
        });
        return button;
    }

    private Button renderRemoveChip(LayoutInflater inflater) {
        Button button = (Button) inflater.inflate(R.layout.button_tag, chipGroup, false);
        button.setText("Reset");


        button.setOnClickListener(v -> {
            // To get the selected name, you can cast the view back to Chip
            Button clickedChip = (Button) v;
            String selectedName = clickedChip.getText().toString();

            this.renderNameChips();


            // You can add your actual filtering logic here!
            Toast.makeText(NotePage.this, "Removed: " + selectedName, Toast.LENGTH_SHORT).show();
        });
        return button;
    }

}
