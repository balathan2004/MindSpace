package com.example.mindspace.api_response;

import com.google.gson.annotations.SerializedName;

public class AuthResponseConfig extends ResponseConfig {


    @SerializedName("credentials")
    private UserProfile user;

    public AuthResponseConfig(String message, UserProfile user) {
        super(message);
        this.user = user;
    }


    public UserProfile getUserProfile() {
        return user;
    }
}