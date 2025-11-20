package com.example.mindspace;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Note implements Serializable {


    private String _id;
    private String title;
    private String desc;
    private String time;

    private String createdAt;

    private String lastModified;

    private List<String> readsAt;

    private List<String> tags;

    public Note(String title, String desc) {
        this._id = Utils.generateShortUUID();
        this.title = title;
        this.desc = desc;
        this.createdAt = Utils.getISOString();
        this.lastModified = Utils.getISOString();
        this.readsAt = Arrays.asList(Utils.getISOString());
        this.tags = new ArrayList<String>();
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

    public List<String> getTags() {
        return tags;
    }

    public Note setTags(List<String> tags) {
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
                ", tags=" + tags.toString() +
                '}';
    }


}

