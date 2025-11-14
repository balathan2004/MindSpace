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
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    String emailValue = null;
    String passwordValue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button btn = findViewById(R.id.submit);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        TextView navigateText = findViewById(R.id.navigateText);


        navigateText.setOnClickListener(e -> {
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
        });

        btn.setOnClickListener(e -> {
            emailValue = new InputValidator(email).setMinLength(6).validate();
            passwordValue = new InputValidator(password).setMinLength(6).validate();

            if (emailValue != null && passwordValue != null) {
                Log.i("Login", "Email: " + emailValue);
                Log.i("Login", "Password: " + passwordValue);
                LoginHandler();


            } else {
                Log.i("Login", "Validation failed");
            }
        });
    }


    private void LoginHandler() {


        if (this.emailValue.isEmpty() || this.passwordValue.isEmpty()) {
            Toast.makeText(this, "Values Messing", Toast.LENGTH_SHORT);

        } else {
            ApiService apiService = RetroFitClient.GetRetroFit().create(ApiService.class);
            LoginRequest loginRequest = new LoginRequest(emailValue, passwordValue);
            Log.d("LOGIN", "Success: made request var");
            Call<AuthResponseConfig> call = apiService.login(loginRequest);

            Log.d("LOGIN", "Success: call set");

            call.enqueue(new Callback<AuthResponseConfig>() {

                @Override
                public void onResponse(Call<AuthResponseConfig> call, Response<AuthResponseConfig> response) {

                    if (response.isSuccessful()) {

                        AuthResponseConfig data = response.body();
                        Log.d("LOGIN", "Success: " + data.getUserProfile().getDisplay_name());

                        NavigatetMain(data.getUserProfile());

                    } else {
                        Log.d("LOGIN", "Success: else block");
                        try {
                            Log.e("LOGIN", "RAW ERROR BODY: " + response.errorBody().string());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                @Override
                public void onFailure(Call<AuthResponseConfig> call, Throwable t) {
                    Log.e("LOGIN", "Error: " + t.getMessage());
                }
            });


        }

    }

    private void NavigatetMain(UserProfile userProfile) {
        Intent Home = new Intent(Login.this, Home.class);

        Gson gson = new Gson();
        String userJson = gson.toJson(userProfile);

        SharedPreferences prefs = getSharedPreferences("userCred", MODE_PRIVATE);
        prefs.edit().putBoolean("isLoggedIn", true).apply();
        prefs.edit().putString("user", userJson);

        Intent intent = new Intent(Login.this, Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
        finish();
    }


}
