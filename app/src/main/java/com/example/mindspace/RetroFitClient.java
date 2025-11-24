package com.example.mindspace;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitClient {

    private static final String baseUrl = " https://d7c364a82c26.ngrok-free.app/";
    private static Retrofit retrofit;

    public static Retrofit GetRetroFit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }


}
