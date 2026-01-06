package com.example.mindspace;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mindspace.api_response.AuthResponseConfig;
import com.example.mindspace.api_response.DataListResponse;
import com.example.mindspace.databinding.ThoughtCardBinding;
import com.example.mindspace.db.AppDatabase;
import com.example.mindspace.db.ThoughtRepo;
import com.example.mindspace.ui_components.CustomHeader;
import com.example.mindspace.ui_components.CustomLoader;
import com.example.mindspace.ui_components.LoadingButton;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import kotlin.Unit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {

    CustomHeader header;

    LinearLayout note_list;
    LayoutInflater inflater;

    CustomLoader loader;

    ThoughtRepo repo;


    SwipeRefreshLayout swipeRefresh;
    List<Thought> noteArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_page);

        repo = new ThoughtRepo(this);

        header = findViewById(R.id.custom_header);

        loader = findViewById(R.id.custom_loader);

        header.setTitle("Hello world");

        swipeRefresh = findViewById(R.id.swipe_refresh);

        Button add_thought = findViewById(R.id.create_thought_btn);

        note_list = findViewById(R.id.note_list);

        inflater = LayoutInflater.from(this);

        swipeRefresh.setOnRefreshListener(() -> {
            swipeRefresh.setRefreshing(true);
            loadThoughts();
            swipeRefresh.setRefreshing(false);
        });

        getFrom();


        add_thought.setOnClickListener(e -> {
            Intent NotePage = new Intent(this, com.example.mindspace.NotePage.class);
            startActivity(NotePage);
        });
        loadThoughts();
    }


    public void addNotes(Thought Thought) {
        inflater = LayoutInflater.from(this);
        ThoughtCardBinding card = ThoughtCardBinding.inflate(inflater, note_list, false);
        card.setNoteItem(Thought);
        card.executePendingBindings();

        card.getRoot().setOnClickListener(e -> {
            Intent NotePage = new Intent(this, com.example.mindspace.NotePage.class);
            NotePage.putExtra("doc_id", Thought.getId());
            startActivity(NotePage);
        });

        note_list.addView(card.getRoot());
    }


    public void loadThoughts() {
        ApiService apiService = RetroFitClient.GetRetroFit(this).create(ApiService.class);


        Call<DataListResponse<Thought>> call = apiService.getThoughts();

        call.enqueue(new Callback<DataListResponse<Thought>>() {
            @Override
            public void onResponse(Call<DataListResponse<Thought>> call, Response<DataListResponse<Thought>> response) {

                if (response.isSuccessful()) {

                    Log.i("console", "success");

                    DataListResponse<Thought> data = response.body();
                    Log.i("console", "response " + data.getMessage());

                    noteArray.clear();

                    noteArray.addAll(Arrays.asList(data.getData()));

//                    addDocs(Arrays.asList(data.getData()));


                    note_list.removeAllViews();

                    loader.setVisibility(false);

                    for (Thought note : noteArray) {
                        addNotes(note);
                    }

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


            }

            @Override
            public void onFailure(Call<DataListResponse<Thought>> call, Throwable t) {

            }
        });
    }

    public List<Thought> getFrom() {

        LifecycleOwnerKt.getLifecycleScope(this).launch(() -> {
            repo.getAllThoughts().collect(thoughts -> {
                renderUI(thoughts);
                return Unit.INSTANCE;
            });
        });


    }


}
