package com.example.absensi.network;

import com.example.absensi.model.request.LoginRequest;
import com.example.absensi.model.request.RegisterRequest;
import com.example.absensi.model.response.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("token?grant_type=password")
    Call<AuthResponse> login(@Body LoginRequest body);

    @POST("signup")
    Call<AuthResponse> register(@Body RegisterRequest body);
}