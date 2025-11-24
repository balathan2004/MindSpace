package com.example.mindspace;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.mindspace.databinding.SingleNoteCardBinding;
import com.example.mindspace.ui_components.LoadingButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {

    LinearLayout header;
    TextView headerTitle;
    LinearLayout note_list;

    LayoutInflater inflater;


    @Override
    protected void onCreate(Bundle savedInstaceState) {

        super.onCreate(savedInstaceState);

        setContentView(R.layout.home_page);
        List<Note> noteArray = new ArrayList<>();


        noteArray.add(new Note("GOAL! Stunning Free Kick!",
                "Ronaldo lines it up from 25 yards... and curls it perfectly into the top corner! Keeper had no chance. What a strike!"
        ).setTags(Arrays.asList("messi", "ronaldo")));

        noteArray.add(new Note("Red Card Issued!",
                "A reckless, two-footed challenge from the defender in midfield. The referee doesn't hesitate. That's a straight red."
        ).setTags(Arrays.asList("messi", "ronaldo")));

        header = findViewById(R.id.header);
        headerTitle = findViewById(R.id.headerTitle);

        Button add_thought = findViewById(R.id.create_thought_btn);

        headerTitle.setText("Home");
        LinearLayout headerRight = findViewById(R.id.headerRight);

        header.removeView(headerRight);


        LoadingButton loadingButton=findViewById(R.id.loader_button);

        loadingButton.onClick(e->{
            loadingButton.hideLoading();
            loadingButton.setText("Load","Loading");
        });

//        loadingButton.hideLoading();



        note_list = findViewById(R.id.note_list);

        inflater = LayoutInflater.from(this);


        int i;

        for (i = 0; i < noteArray.size(); i++) {
            Note currentNote = noteArray.get(i);
            addNotes(currentNote);
        }


        add_thought.setOnClickListener(e -> {

            Intent NotePage = new Intent(this, com.example.mindspace.NotePage.class);
            startActivity(NotePage);
        });


    }


    public void addNotes(Note note) {
        inflater = LayoutInflater.from(this);
        SingleNoteCardBinding card = SingleNoteCardBinding.inflate(inflater, note_list, false);
        card.setNoteItem(note);
        card.executePendingBindings();

        card.getRoot().setOnClickListener(e -> {
            Intent NotePage = new Intent(this, com.example.mindspace.NotePage.class);
            NotePage.putExtra("Note", note);
            startActivity(NotePage);
        });

        note_list.addView(card.getRoot());
    }

}
