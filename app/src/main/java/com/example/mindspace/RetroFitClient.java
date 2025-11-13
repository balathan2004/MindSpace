package com.example.mindspace;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitClient {

//    private static final String baseUrl = "http://localhost:3000/";
    private static final String baseUrl = "https://jsonplaceholder.typicode.com/";
    private static Retrofit retrofit;

    public static Retrofit GetRetroFit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }


}
