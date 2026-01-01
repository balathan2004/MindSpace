package com.example.mindspace;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Thought implements Serializable {


    private String _id;
    private String title;
    private String desc;
    private String occurredAt;


    private String createdAt;

    private String lastModified;

    private List<String> readsAt;

    private List<String> tags;


    public Thought() {

    }

    public Thought(String title, String desc) {
        this._id = Utils.generateShortUUID();
        this.title = title;
        this.desc = desc;
        this.createdAt = "";
        this.lastModified = "";
        this.readsAt = new ArrayList<String>();
        this.tags = new ArrayList<String>();
    }


    public String get_id() {
        return _id;
    }

    public String getOccurredAt() {
        return this.occurredAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOccurredAt(String occurredAt) {
        this.occurredAt = occurredAt;
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

    public Thought setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public void addTime() {
        String localTime = Utils.getISOString();
        if (this.occurredAt ==null || this.occurredAt.trim().contentEquals("")) {
            this.occurredAt = localTime;
        }
        this.lastModified = localTime;
        this.readsAt.add(localTime);
    }

    public void updateReadsAt(){
        String localTime = Utils.getISOString();
        this.readsAt.add(localTime);
    }


    @NonNull
    @Override
    public String toString() {
        return "Note{" +
                "_id='" + _id + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", occurredAt='" + occurredAt + '\'' +
                ", readsAt='" + readsAt + '\'' +
                ", lastModified='" + lastModified + '\'' +
                ", tags=" + tags.toString() +
                '}';
    }


}

