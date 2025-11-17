package com.example.mindspace;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Arrays;

public class Note implements Serializable {


    private String _id;
    private String title;
    private String desc;
    private String time;

    private String[] tags;

    public Note(String title, String desc, String time) {
        this._id = Utils.generateShortUUID();
        this.title = title;
        this.desc = desc;
        this.time = time;
        this.tags = new String[0];
    }


    public String getTime() {
        return this.time;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String[] getTags() {
        return tags;
    }

    public Note setTags(String[] tags) {
        this.tags = tags;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        return "Note{" +
                "_id='" + _id + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", time='" + time + '\'' +
                ", tags=" + Arrays.toString(tags) +
                '}';
    }


}

