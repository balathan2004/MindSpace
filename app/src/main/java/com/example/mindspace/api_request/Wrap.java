package com.example.mindspace.api_request;

public final class Wrap {
    private Wrap() {
    }

    public static <T> DataWrapper<T> d(T data) {
        return new DataWrapper<>(data);
    }
}
