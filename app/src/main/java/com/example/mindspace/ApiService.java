// ApiService.java
package com.example.mindspace;

import com.example.mindspace.api_request.CreateThoughtRequest;
import com.example.mindspace.api_request.DataWrapper;
import com.example.mindspace.api_request.LoginRequest;
import com.example.mindspace.api_request.RefreshTokenRequest;
import com.example.mindspace.api_response.AuthResponseConfig;
import com.example.mindspace.api_response.DataListResponse;
import com.example.mindspace.api_response.DataResponse;
import com.example.mindspace.api_response.ResponseConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {


    @POST("auth/login")
    Call<AuthResponseConfig> login(@Body DataWrapper<LoginRequest> body);


    @POST("auth/refreshToken")
    Call<AuthResponseConfig> updateRefreshToken(@Body DataWrapper<RefreshTokenRequest> refreshTokenRequest);

    @POST("mindspace/thoughts")
    Call<ResponseConfig> createThought(@Body DataWrapper<CreateThoughtRequest> createThought);


    @GET("mindspace/thoughts")
    Call<DataListResponse<Thought>> getThoughts();

    @GET("mindspace/thoughts/:id")

    Call<DataResponse<Thought>> getSingleThought();

    @GET("api/get_docs")
    Call<ResponseConfig> getDocs();


}
