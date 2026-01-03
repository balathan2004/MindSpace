package com.example.mindspace;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.mindspace.api_request.Wrap;
import com.example.mindspace.api_response.AuthResponseConfig;
import com.example.mindspace.api_response.DataResponse;
import com.example.mindspace.api_response.ResponseConfig;
import com.example.mindspace.ui_components.BottomSheetComponent;
import com.example.mindspace.ui_components.CustomHeader;
import com.example.mindspace.ui_components.CustomLoader;
import com.example.mindspace.ui_components.LabelValue;
import com.example.mindspace.utils.Utils;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotePage extends AppCompatActivity {

    ChipGroup chipGroup;

    EditText tag_input;
    EditText title;

    EditText desc;
    TextInputLayout tag_input_container;

    Button submit_button;
    Chip remove_button;
    Thought ThoughtData = null;

    CustomLoader loader;

    String doc_id = null;
    private List<String> tags = new ArrayList<String>();

    ApiService apiService;

    BottomSheetComponent sheet;
    CustomHeader header;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thought_page);

        LinearLayout root = findViewById(R.id.root);

        header = findViewById(R.id.custom_header);
        loader = findViewById(R.id.custom_loader);

        header.setTitle("Note");

        injectRight();


        title = findViewById(R.id.note_title);
        desc = findViewById(R.id.note_text);
        apiService = RetroFitClient.GetRetroFit(this).create(ApiService.class);


        doc_id = (String) getIntent().getSerializableExtra("doc_id");


        getThought(doc_id);

        chipGroup = findViewById(R.id.tag_chip_group);
        tag_input = findViewById(R.id.add_tag);
        tag_input_container = findViewById(R.id.add_tag_container);
        Drawable add_icon = ContextCompat.getDrawable(this, R.drawable.ic_add);


        submit_button = findViewById(R.id.submit_button);


        tag_input_container.setEndIconMode(TextInputLayout.END_ICON_CUSTOM);
        tag_input_container.setEndIconDrawable(add_icon);
        tag_input_container.setEndIconVisible(false);

        tag_input_container.setEndIconOnClickListener(v -> {
            addTag();
        });


        TextChangeHandler(tag_input);


        tag_input.setOnEditorActionListener((v, actionId, event) -> {
            if (EditorInfo.IME_ACTION_SEND == actionId) {
                addTag();
                return true;
            }
            tag_input.requestFocus();
            return false;

        });


        submit_button.setOnClickListener(e -> submit()
        );

    }

    public void initSheet() {
        LinearLayout container = new LinearLayout(this);

        container.setOrientation(LinearLayout.VERTICAL);

        container.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));


        Map<String, String> times = new LinkedHashMap<>();

        times.put("Title", "Cowsika");
        times.put("Created", "Harini");
        times.put("Reads", "Gnanasiri");
        times.put("Create AT ", ThoughtData.getCreatedAt());
        times.put("Occured At", ThoughtData.getOccurredAt());


        for (Map.Entry<String, String> data : times.entrySet()) {
            LabelValue labelRenderer = new LabelValue(this);
            labelRenderer.AttachLabelValue(container, data.getKey(), data.getValue());
        }

        sheet = BottomSheetComponent.newInstance(container);

    }


    public void submit() {
        String note_title = new InputValidator(title).setMinLength(3).validate();
        String note_text = new InputValidator(desc).setMinLength(6).validate();

        if (note_title == null || note_text == null) {
            Toast.makeText(NotePage.this, "Title or Content Missing", Toast.LENGTH_SHORT).show();
            return;
        }

        ThoughtData.setDesc(note_text.trim());
        ThoughtData.setTitle(note_title.trim());

        ThoughtData.setTags(tags);
        ThoughtData.addTime();

        CreateNote();

        Log.i("console", ThoughtData.toString());
    }


    public void TextChangeHandler(EditText input) {


        input.addTextChangedListener(new TextWatcher() {
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
        Chip button = (Chip) inflater.inflate(R.layout.chip_button, chipGroup, false);
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
        remove_button = (Chip) inflater.inflate(R.layout.chip_button, chipGroup, false);
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

            tags.add(tag_name.trim());
            renderNameChips();
            tag_input.setText("");
        }
    }


    private void CreateNote() {

        Call<ResponseConfig> call = apiService.patchThought(Wrap.d(ThoughtData), ThoughtData.get_id());

        call.enqueue(new Callback<ResponseConfig>() {
            @Override
            public void onResponse(Call<ResponseConfig> call, Response<ResponseConfig> response) {
                if (response.isSuccessful()) {
                    ResponseConfig data = response.body();
                    Utils.ShowToast(NotePage.this, data.getMessage().toString());
                } else {
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

    private void getThought(String doc_id) {

        if (doc_id == null) {
            loader.setVisibility(false);
            return;
        }
        ;

        Call<DataResponse<Thought>> call = apiService.getSingleThought(doc_id);


        call.enqueue(new Callback<DataResponse<Thought>>() {
            @Override
            public void onResponse(Call<DataResponse<Thought>> call, Response<DataResponse<Thought>> response) {

                if (response.isSuccessful()) {
                    DataResponse<Thought> res = response.body();


                    ThoughtData = response.body().getData();

                    title.setText(ThoughtData.getTitle());
                    desc.setText(ThoughtData.getDesc());
                    header.setTitle(ThoughtData.getTitle());
                    tags.clear();
                    tags.addAll(ThoughtData.getTags());
                    renderNameChips();
                    initSheet();
                } else {
                    try {
                        Log.i("console", "failure ");
                        String errorJson = response.errorBody().string();
                        AuthResponseConfig err = new Gson().fromJson(errorJson, AuthResponseConfig.class);
                        Log.i("console", "response" + err);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                loader.setVisibility(false);


            }

            @Override
            public void onFailure(Call<DataResponse<Thought>> call, Throwable t) {

            }
        });


    }

    private void handleHistoryClick() {
    }


    private void injectRight() {

        LinearLayout headerRight = header.getHeaderRight();
        headerRight.removeAllViews();


        ImageView historyIcon = new ImageView(this);
        historyIcon.setImageResource(R.drawable.ic_history_icon);
        ImageView markIcon = new ImageView(this);
        markIcon.setImageResource(R.drawable.ic_save_icon);

        headerRight.addView(markIcon);
        headerRight.addView(historyIcon);
    }


}
