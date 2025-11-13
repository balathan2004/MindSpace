package com.example.mindspace;

public class ToDo {

    private String userId;
    private int id;

    private String title;
    private boolean completed;


    public ToDo(String userId, int id, String title, boolean completed) {

        this.completed = completed;
        this.title = title;
        this.userId = userId;
        this.id = id;

    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isCompleted() {
        return completed;
    }
}
