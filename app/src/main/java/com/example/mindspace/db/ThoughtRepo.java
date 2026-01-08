package com.example.mindspace.db;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.example.mindspace.Thought;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import kotlinx.coroutines.flow.Flow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.FlowLiveDataConversions;


public class ThoughtRepo {

    private final ThoughtDAO dao;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public ThoughtRepo(Context context) {
        dao = AppDatabase.get(context).thoughtDao();
    }


    public LiveData<List<Thought>> getAllThoughts() {
        return FlowLiveDataConversions.asLiveData(dao.getAll());
    }

    public LiveData<Thought> getThoughtById(String id){
        return FlowLiveDataConversions.asLiveData(dao.getById(id));
    }




    public void insert (Thought thought,Runnable onSuccess,Runnable onFailure){
        executor.execute(()->{
            long res= dao.insert(thought);
            if (res > 0 && onSuccess != null) {
                new Handler(Looper.getMainLooper()).post(onSuccess);
            }else{
                new Handler(Looper.getMainLooper()).post(onFailure);
            }
        });
    }


}
