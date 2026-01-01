package com.example.mindspace;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mindspace.api_request.RefreshTokenRequest;
import com.example.mindspace.api_request.Wrap;
import com.example.mindspace.api_response.AuthResponseConfig;
import com.example.mindspace.api_response.UserProfile;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class AuthStack extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("userCred", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);


        String refreshToken = prefs.getString("refreshToken", null);

        AuthState state = (AuthState) getApplication();

        if (refreshToken == null) {
            Log.e("console", "cred is null");
            StartLogin();
        } else {
//            Log.e("console", refreshToken);
            ApiService apiService = RetroFitClient.GetRetroFit(this).create(ApiService.class);
            RefreshTokenRequest req = new RefreshTokenRequest(refreshToken);

            Call<AuthResponseConfig> call = apiService.updateRefreshToken(Wrap.d(req));


            call.enqueue(new Callback<AuthResponseConfig>() {
                @Override
                public void onResponse(Call<AuthResponseConfig> call, Response<AuthResponseConfig> response) {
                    if (response.isSuccessful()) {
                        AuthResponseConfig data = response.body();
//                        Log.i("console", "onResponse: " + data.getMessage());
                        state.setLoggedIn(data);
                        startMainStack();

                    } else {
                        try {
                            String errorJson = response.errorBody().string();
                            AuthResponseConfig err = new Gson().fromJson(errorJson, AuthResponseConfig.class);
                            StartLogin();
                        } catch (IOException e) {
                            throw new RuntimeException(e);

                        }
                    }
                }

                @Override
                public void onFailure(Call<AuthResponseConfig> call, Throwable t) {
                    Log.e("console", "ON Failure: " + t.getMessage());
                    StartLogin();
                }
            });


        }


    }


    private void StartLogin() {
        Utils.Navigate(this, Login.class, false);
    }

    private void startMainStack() {

        Utils.Navigate(this, Home.class, true);
        finish();
    }

}
