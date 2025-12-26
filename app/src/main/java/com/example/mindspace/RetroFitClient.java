package com.example.mindspace;


import android.content.Context;
import android.util.Log;

import com.example.mindspace.api_request.TokenIntercepter;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitClient {

    private static TokenIntercepter tokenInterceptor;
    private static final String baseUrl = "https://life-tracker-server-lightuzumaki-lightuzumaki-projects.vercel.app/";
    private static Retrofit retrofit;


    public static Retrofit GetRetroFit(Context context) {
        AuthState authState = (AuthState) context.getApplicationContext();
        if (tokenInterceptor == null) {

            Log.i("console", "GetRetroFitNull: "+authState.getAccessToken());
            tokenInterceptor = new TokenIntercepter(authState);
        } else {
            tokenInterceptor.setAccessToken(authState.getAccessToken());
            Log.i("console", "GetRetroFitFull: "+authState.getAccessToken());
        }

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(tokenInterceptor)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                    .client(client).build();
        }
        return retrofit;
    }


}
