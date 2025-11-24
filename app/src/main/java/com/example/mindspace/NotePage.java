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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.mindspace.api_request.CreateThoughtRequest;
import com.example.mindspace.api_response.AuthResponseConfig;
import com.example.mindspace.api_response.ResponseConfig;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotePage extends AppCompatActivity {

    ChipGroup chipGroup;

    EditText tag_input;
    TextInputLayout tag_input_container;

    Button submit_button;
    Chip remove_button;
    Note NoteData = null;
    private List<String> tags = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_page);


        NoteData = (Note) getIntent().getSerializableExtra("Note");

        EditText title = findViewById(R.id.note_title);
        EditText desc = findViewById(R.id.note_text);
        ImageView backarrow = findViewById(R.id.back_arrow);


        backarrow.setOnClickListener(e -> {
            finish();
        });


        if (NoteData != null) {
            title.setText(NoteData.getTitle());
            desc.setText(NoteData.getDesc());
            List<String> tagsValue = NoteData.getTags();
            tags.addAll(tagsValue);
        } else {
            NoteData = new Note("", "");
        }

        chipGroup = findViewById(R.id.tag_chip_group);
        tag_input = findViewById(R.id.add_tag);
        tag_input_container = findViewById(R.id.add_tag_container);
        Drawable add_icon = ContextCompat.getDrawable(this, R.drawable.ic_add);


        TextView header = findViewById(R.id.headerTitle);

        header.setText("Notes");
        renderNameChips();


        submit_button = findViewById(R.id.submit_button);


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
                    Log.i("console", "length accepted");
                    tag_input_container.setEndIconVisible(true);
                } else {
                    tag_input_container.setEndIconVisible(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        tag_input.setOnEditorActionListener((v, actionId, event) -> {
            if (EditorInfo.IME_ACTION_SEND == actionId) {
                addTag();
                return true;
            }
            tag_input.requestFocus();
            return false;

        });


        submit_button.setOnClickListener(e -> {
            String note_title = new InputValidator(title).setMinLength(3).validate();
            String note_text = new InputValidator(desc).setMinLength(6).validate();

            if (note_title == null || note_text == null) {
                Toast.makeText(NotePage.this, "Title or Content Missing", Toast.LENGTH_SHORT).show();
                return;
            }

            NoteData.setDesc(note_text);
            NoteData.setTitle(note_title);

            NoteData.setTags(tags);

            CreateNote();

            Log.i("console", NoteData.toString());

        });

    }


    private void renderNameChips() {
        chipGroup.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);

        for (String name : tags) {
            Chip button = (Chip) renderSingleChip(inflater, name);
            chipGroup.addView(button);
        }

        if (!tags.isEmpty()) {
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
            tags.remove(selectedName);
            if (tags.isEmpty()) {
                parent.removeView(remove_button);
            }
        });
        return button;
    }

    private Button renderRemoveChip(LayoutInflater inflater) {
        remove_button = (Chip) inflater.inflate(R.layout.button_tag, chipGroup, false);
        remove_button.setText("Reset");

        remove_button.setCloseIconVisible(false);


        remove_button.setOnClickListener(v -> {

            tags.clear();
            this.renderNameChips();
            chipGroup.removeView(remove_button);
            // You can add your actual filtering logic here!
        });
        return remove_button;
    }

    private void addTag() {
        String tag_name = new InputValidator(tag_input).setMinLength(3).validate();

        if (tag_name != null) {
            Log.i("console", tag_name);


            tags.add(tag_name);

            renderNameChips();
            tag_input.setText("");
        }
    }

    private void CreateNote(){

        Utils.ShowToast(NotePage.this,"Called CreateNote");

        ApiService apiService=RetroFitClient.GetRetroFit().create(ApiService.class);
        CreateThoughtRequest request=new CreateThoughtRequest(NoteData);
        Call<ResponseConfig> call = apiService.createThought(request);

        call.enqueue(new Callback<ResponseConfig>() {
            @Override
            public void onResponse(Call<ResponseConfig> call, Response<ResponseConfig> response) {
                if(response.isSuccessful()){
                    ResponseConfig data=response.body();
                    Utils.ShowToast(NotePage.this,data.getMessage().toString());
                }else{
                    try {
                        String errorJson = response.errorBody().string();
                        AuthResponseConfig err = new Gson().fromJson(errorJson, AuthResponseConfig.class);
                        Utils.ShowToast(NotePage.this, err.getMessage());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseConfig> call, Throwable t) {
                Log.e("console", "Error: " + t.getMessage());
            }
        });


    }



}
