package com.example.mindspace.api_response;

import com.google.gson.annotations.SerializedName;

public class DataListResponse<T> extends ResponseConfig {


    @SerializedName("data")
    private T[] data;

    public DataListResponse(String message, T[] data) {
        super(message);
        this.data = data;
    }

    public T[] getData() {
        return data;
    }
}
