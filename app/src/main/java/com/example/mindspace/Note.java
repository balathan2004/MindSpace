package com.example.mindspace;

import java.io.Serializable;

public class Note implements Serializable {


    private String title;
    private String desc;
    private String time;

    private String[] tags;

    public Note(String title, String desc, String time) {
        this.title = title;
        this.desc = desc;
        this.time = time;
        this.tags = new String[0];
    }

    public Note setTags(String[] tags) {
        this.tags = tags;
        return this;
    }

    public String getTime() {
        return this.time;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDesc() {
        return this.desc;
    }

    public String[] getTags() {
        return tags;
    }
}
