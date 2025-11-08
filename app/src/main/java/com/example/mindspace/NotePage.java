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

    EditText tag_input;
    Chip remove_button;
    private final List<String> initNames = Arrays.asList("Cowsika", "Siri", "Harini", "Suvetha");
    private List<String> names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_page);
        chipGroup = findViewById(R.id.tag_chip_group);
        tag_input = findViewById(R.id.add_tag);


        TextView header = findViewById(R.id.headerTitle);

        header.setText("Notes");

        renderNameChips();


        EditText title = findViewById(R.id.note_title);
        EditText note = findViewById(R.id.note_text);
        Button submitButton = findViewById(R.id.submitNote);


        tag_input.setOnEditorActionListener((v, actionId, event) ->

        {

            if (EditorInfo.IME_ACTION_SEND == actionId) {

                String tag_name = new InputValidator(tag_input).setMinLength(3).validate();

                if (tag_name != null) {
                    Log.i("Note", tag_name);
                    names.add(tag_name);
                    renderNameChips();
                    tag_input.setText("");
                }

                return true;
            }
            tag_input.requestFocus();
            return false;

        });


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
            if (names.isEmpty()) {
                parent.removeView(remove_button);
            }


            // You can add your actual filtering logic here!
            Toast.makeText(NotePage.this, "Removed: " + selectedName, Toast.LENGTH_SHORT).show();
        });
        return button;
    }

    private Button renderRemoveChip(LayoutInflater inflater) {
        remove_button = (Chip) inflater.inflate(R.layout.button_tag, chipGroup, false);
        remove_button.setText("Reset");

        remove_button.setCloseIconVisible(false);


        remove_button.setOnClickListener(v -> {
            // To get the selected name, you can cast the view back to Chip
            Chip clickedChip = (Chip) v;
            String selectedName = clickedChip.getText().toString();

            names.clear();


            this.renderNameChips();
            chipGroup.removeView(remove_button);

            // You can add your actual filtering logic here!
            Toast.makeText(NotePage.this, "Reset to default", Toast.LENGTH_SHORT).show();
        });
        return remove_button;
    }

}
