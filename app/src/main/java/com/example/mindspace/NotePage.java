package com.example.mindspace;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotePage extends AppCompatActivity {

    ChipGroup chipGroup;

    EditText tag_input;
    TextInputLayout tag_input_container;
    Chip remove_button;
    private final List<String> initNames = Arrays.asList("Cowsika", "Siri", "Harini", "Suvetha");
    private List<String> names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_page);

        Note ReceivedNote = (Note) getIntent().getSerializableExtra("Note");

        EditText title = findViewById(R.id.note_title);
        EditText desc = findViewById(R.id.note_text);


        if (ReceivedNote != null) {
            title.setText(ReceivedNote.getTitle());
            desc.setText(ReceivedNote.getDesc());
            List<String> tagsValue = Arrays.asList(ReceivedNote.getTags());

            names.addAll(tagsValue);

        }

        chipGroup = findViewById(R.id.tag_chip_group);
        tag_input = findViewById(R.id.add_tag);
        tag_input_container = findViewById(R.id.add_tag_container);
        Drawable add_icon = ContextCompat.getDrawable(this, R.drawable.ic_add);


        TextView header = findViewById(R.id.headerTitle);

        header.setText("Notes");
        renderNameChips();
//        renderNameChips();


        Button submitButton = findViewById(R.id.submitNote);

        tag_input_container.setEndIconMode(TextInputLayout.END_ICON_CUSTOM);
        tag_input_container.setEndIconDrawable(add_icon);
        tag_input_container.setEndIconVisible(false);

        tag_input_container.setEndIconOnClickListener(v -> {
            addTag();
        });


        tag_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String currentValue = s.toString();
                if (currentValue.length() > 3) {
                    Log.i("note", "length accepted");
                    tag_input_container.setEndIconVisible(true);
                } else {
                    tag_input_container.setEndIconVisible(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        tag_input.setOnEditorActionListener((v, actionId, event) ->

        {

            if (EditorInfo.IME_ACTION_SEND == actionId) {

                addTag();

                return true;
            }
            tag_input.requestFocus();
            return false;

        });


        submitButton.setOnClickListener(e -> {
            String note_title = new InputValidator(title).setMinLength(20).validate();
            String note_text = new InputValidator(desc).setMinLength(20).validate();

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

    private void addTag() {
        String tag_name = new InputValidator(tag_input).validate();

        if (tag_name != null) {
            Log.i("Note", tag_name);
            names.add(tag_name);
            renderNameChips();
            tag_input.setText("");
        }
    }

}
