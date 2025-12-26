package com.example.mindspace.api_response;

import com.google.gson.annotations.SerializedName;

public class DataResponse<T> extends ResponseConfig {


    @SerializedName("data")
    private T data;

    public DataResponse(String message, T data) {
        super(message);
        this.data = data;
    }


}
