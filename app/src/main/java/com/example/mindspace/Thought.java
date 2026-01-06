package com.example.mindspace;

import androidx.annotation.NonNull;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.mindspace.db.StringListConverter;
import com.example.mindspace.utils.TimeUtils;
import com.example.mindspace.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity(tableName = "thoughts")
@TypeConverters(StringListConverter.class)
public class Thought implements Serializable {

    @PrimaryKey
    @NonNull
    private String _id;

    private String title;
    private String desc;
    private String occurredAt;
    private String lastModified;

    private boolean isDeleted;
    private boolean isDirty;

    private List<String> readsAt;
    private List<String> tags;


    public Thought() {
        this._id = Utils.generateShortUUID();
        this.readsAt = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.isDeleted = false;
        this.isDirty = true;
    }


    public Thought(String title, String desc) {
        this._id = Utils.generateShortUUID();
        this.title = title;
        this.desc = desc;
        this.lastModified = "";
        this.isDirty = true;
        this.readsAt = new ArrayList<String>();
        this.tags = new ArrayList<String>();
    }


    @NonNull
    public String getId() {
        return _id;
    }

    public void setId(@NonNull String id) {
        this._id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.isDirty = true;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
        this.isDirty = true;
    }

    public String getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(String occurredAt) {
        this.occurredAt = occurredAt;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
        this.isDirty = true;
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setDirty(boolean dirty) {
        isDirty = dirty;
    }

    public List<String> getReadsAt() {
        return readsAt;
    }

    public void setReadsAt(List<String> readsAt) {
        this.readsAt = readsAt;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    // ================== Helper Logic ==================

    public void addTime() {
        String localTime = TimeUtils.getISOString();

        if (occurredAt == null || occurredAt.trim().isEmpty()) {
            occurredAt = localTime;
        }

        lastModified = localTime;
        readsAt.add(localTime);
        isDirty = true;
    }

    // ================== Debug ==================

    @NonNull
    @Override
    public String toString() {
        return "Thought{" +
                "id='" + _id + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", occurredAt='" + occurredAt + '\'' +
                ", lastModified='" + lastModified + '\'' +
                ", readsAt=" + readsAt +
                ", tags=" + tags +
                ", isDeleted=" + isDeleted +
                ", isDirty=" + isDirty +
                '}';
    }


}

