// ApiService.java
package com.example.mindspace;

import com.example.mindspace.api_request.CreateThoughtRequest;
import com.example.mindspace.api_request.LoginRequest;
import com.example.mindspace.api_request.RefreshTokenRequest;
import com.example.mindspace.api_response.AuthResponseConfig;
import com.example.mindspace.api_response.ResponseConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {


    @POST("auth/login")
    Call<AuthResponseConfig> login(@Body LoginRequest loginRequest);


    @POST("auth/refreshToken")
    Call<AuthResponseConfig>updateRefreshToken(@Body RefreshTokenRequest refreshTokenRequest);

    @POST("mindspace")
    Call<ResponseConfig> createThought(@Body CreateThoughtRequest createThought);


    @GET("api/get_docs")
    Call<ResponseConfig> getDocs();


}
