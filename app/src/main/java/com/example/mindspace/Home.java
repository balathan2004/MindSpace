package com.example.mindspace;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.mindspace.databinding.SingleNoteCardBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

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
                "Ronaldo lines it up from 25 yards... and curls it perfectly into the top corner! Keeper had no chance. What a strike!",
                "12:35"));

        noteArray.add(new Note("Red Card Issued!",
                "A reckless, two-footed challenge from the defender in midfield. The referee doesn't hesitate. That's a straight red.",
                "34:10"));

        header = findViewById(R.id.header);
        headerTitle = findViewById(R.id.headerTitle);

        headerTitle.setText("Home");
        LinearLayout headerRight = findViewById(R.id.headerRight);

        header.removeView(headerRight);


        note_list = findViewById(R.id.note_list);

        inflater = LayoutInflater.from(this);

        int i;

        for (i = 0; i < noteArray.size(); i++) {
            Note currentNote = noteArray.get(i);
            addNotes(currentNote);
        }


    }

    public void addNotes(Note note) {
        inflater = LayoutInflater.from(this);
        SingleNoteCardBinding card = SingleNoteCardBinding.inflate(inflater, note_list, false);
        card.setNoteItem(note);
        card.executePendingBindings();

        card.getRoot().setOnClickListener(e -> {
            Intent NotePage = new Intent(this, com.example.mindspace.NotePage.class);
            NotePage.putExtra("Note", note);
            startActivity(NotePage)
            ;
        });

        note_list.addView(card.getRoot());
    }

}
