package com.example.absensi.network;

import com.example.absensi.model.response.ProfileResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ProfileService {

    @GET("profiles")
    Call<List<ProfileResponse>> getProfile(
            @Header("Authorization") String bearerToken,
            @Header("apikey")        String apiKey,
            @Query("id")             String idFilter,
            @Query("select")         String select
    );

    // Update status dan role user (untuk approve)
    @GET("profiles")
    Call<List<ProfileResponse>> getPendingUsers(
            @Header("Authorization") String bearerToken,
            @Header("apikey")        String apiKey,
            @Query("status")         String statusFilter,
            @Query("select")         String select
    );
}