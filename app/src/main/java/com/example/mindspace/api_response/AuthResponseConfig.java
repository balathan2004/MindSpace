package com.example.mindspace.api_response;

import com.google.gson.annotations.SerializedName;

public class AuthResponseConfig extends ResponseConfig {


    @SerializedName("credentials")
    private UserProfile user;

    @SerializedName("accessToken")
    private String accessToken;


    @SerializedName("refreshToken")
    private String refreshToken;


    public AuthResponseConfig(String message, UserProfile user, String accessToken, String refreshToken) {
        super(message);
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }


    public UserProfile getUserProfile() {
        return user;
    }
}