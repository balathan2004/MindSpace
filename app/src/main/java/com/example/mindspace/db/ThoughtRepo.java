package com.example.mindspace.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mindspace.Thought;

import java.util.List;

import kotlinx.coroutines.flow.Flow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.FlowLiveDataConversions;


public class ThoughtRepo {

    private final ThoughtDAO dao;

    public ThoughtRepo(Context context) {
        dao = AppDatabase.get(context).thoughtDao();
    }


    public LiveData<List<Thought>> getAllThoughts() {
        return FlowLiveDataConversions.asLiveData(dao.getAll());
    }

    public LiveData<Thought> getThoughtById(String id){
        return FlowLiveDataConversions.asLiveData(dao.getById(id));
    }


    public void insert(Thought thought) {
        dao.insert(thought);
    }


}
