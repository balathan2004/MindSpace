package com.example.mindspace;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitClient {

    private static final String baseUrl = "https://724af60bb547.ngrok-free.app/api/";
    private static Retrofit retrofit;

    public static Retrofit GetRetroFit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }


}
