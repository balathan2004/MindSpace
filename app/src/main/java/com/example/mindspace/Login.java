package com.example.mindspace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mindspace.api_request.LoginRequest;
import com.example.mindspace.api_response.AuthResponseConfig;
import com.example.mindspace.api_response.UserProfile;
import com.example.mindspace.ui_components.LoadingButton;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {

    String emailValue = null;
    String passwordValue = null;
    LoadingButton submit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

//        Button btn = findViewById(R.id.submit);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        TextView navigateText = findViewById(R.id.navigateText);

        submit_button = findViewById(R.id.submit_button);
        submit_button.setLabels("Login","Logging in");



        navigateText.setOnClickListener(e -> {
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
        });

        submit_button.setOnClickListener(e -> {
            submit_button.showLoading();

            emailValue = new InputValidator(email).setMinLength(6).validate();
            passwordValue = new InputValidator(password).setMinLength(6).validate();

            if (emailValue != null && passwordValue != null) {
                Log.i("Login", "Email: " + emailValue);
                Log.i("Login", "Password: " + passwordValue);
                LoginHandler();


            } else {
                Log.i("Login", "Validation failed");
                submit_button.hideLoading();
            }
        });
    }


    private void LoginHandler() {


        if (this.emailValue.isEmpty() || this.passwordValue.isEmpty()) {

            Utils.ShowToast(this, "Values Messing");
            submit_button.hideLoading();


        } else {
            ApiService apiService = RetroFitClient.GetRetroFit().create(ApiService.class);
            LoginRequest loginRequest = new LoginRequest(emailValue, passwordValue);
            Log.d("LOGIN", "Success: made request var");
            Call<AuthResponseConfig> call = apiService.login(loginRequest);


            call.enqueue(new Callback<AuthResponseConfig>() {

                @Override
                public void onResponse(Call<AuthResponseConfig> call, Response<AuthResponseConfig> response) {

                    if (response.isSuccessful()) {

                        AuthResponseConfig data = response.body();

                        Utils.ShowToast(Login.this, "Login Successful");
                        NavigateToMain(data.getUserProfile());
                        submit_button.hideLoading();

                    } else {

                        try {
                            String errorJson = response.errorBody().string();
                            AuthResponseConfig err = new Gson().fromJson(errorJson, AuthResponseConfig.class);
                            Utils.ShowToast(Login.this, err.getMessage());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        submit_button.hideLoading();
                    }
                }

                @Override
                public void onFailure(Call<AuthResponseConfig> call, Throwable t) {
                    Log.e("LOGIN", "Error: " + t.getMessage());
                }
            });


        }

    }

    private void NavigateToMain(UserProfile userProfile) {
        Intent Home = new Intent(Login.this, Home.class);

        Gson gson = new Gson();
        String userJson = gson.toJson(userProfile);

        AuthState state=(AuthState) getApplication();

        state.setLoggedIn(true,userProfile);

        SharedPreferences prefs = getSharedPreferences("userCred", MODE_PRIVATE);
        prefs.edit().putBoolean("isLoggedIn", true).apply();
        prefs.edit().putString("user", userJson).apply();

        Log.e("console", "Error: " + userJson);


        Utils.Navigate(this, Home.class, true);

        finish();
    }


}
