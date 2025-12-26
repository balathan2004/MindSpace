package com.example.mindspace.api_request;

public class DataWrapper<T> {

    private  T data;

    public DataWrapper(T data){
        this.data=data;

    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
