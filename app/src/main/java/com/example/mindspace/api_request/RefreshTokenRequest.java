package com.example.mindspace.api_request;

public class RefreshTokenRequest {


    private String refreshToken;

    public RefreshTokenRequest(String token) {
        this.refreshToken = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
