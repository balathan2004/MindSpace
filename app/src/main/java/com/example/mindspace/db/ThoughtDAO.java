package com.example.mindspace.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mindspace.Thought;

import java.util.List;

import kotlinx.coroutines.flow.Flow;

@Dao
public interface ThoughtDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insert(Thought thought);


    @Query("SELECT * FROM thoughts ORDER BY lastModified DESC")
    public Flow<List<Thought>> getAll();


    @Query("SELECT * FROM thoughts WHERE _id=:id")
    Flow<Thought> getById(String id);

    @Delete
    void delete(Thought thought);

    @Query("DELETE FROM thoughts")
    void clear();
}
