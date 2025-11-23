package com.example.mindspace;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitClient {

    private static final String baseUrl = " https://15d502ab2d88.ngrok-free.app/";
    private static Retrofit retrofit;

    public static Retrofit GetRetroFit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }


}
