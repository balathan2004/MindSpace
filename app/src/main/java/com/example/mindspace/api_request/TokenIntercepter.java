package com.example.mindspace.api_request;

import com.example.mindspace.AuthState;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenIntercepter implements Interceptor {

    private String accessToken;

    public TokenIntercepter(AuthState state) {
        this.accessToken = state.getAccessToken();
    }

    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();
        Request.Builder builder = original.newBuilder().header("Authorization", "Bearer " + accessToken);
        Request request = builder.build();
        return chain.proceed(request);
    }
}
