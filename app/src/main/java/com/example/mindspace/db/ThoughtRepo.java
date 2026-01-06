package com.example.mindspace.db;

import android.content.Context;

import com.example.mindspace.Thought;

import java.util.List;

import kotlinx.coroutines.flow.Flow;

public class ThoughtRepo {

    private final ThoughtDAO dao;

    public ThoughtRepo(Context context) {
        dao = AppDatabase.get(context).thoughtDao();
    }

    public Flow<List<Thought>> getAllThoughts() {
        return dao.getAll();
    }


    public Flow<Thought> getById(String id) {
        return dao.getById(id);
    }

    public void insert(Thought thought) {
        dao.insert(thought);
    }


}
