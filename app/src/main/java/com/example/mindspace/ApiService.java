// ApiService.java
package com.example.mindspace;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
//    @GET("posts")
//    Call<List<Note>> getPosts();
@GET("todos/{id}")
Call<ToDo> getTodoById(@Path("id") int todoId);
}
