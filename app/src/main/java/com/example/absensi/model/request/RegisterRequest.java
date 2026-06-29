package com.example.absensi.model.request;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("data")
    private UserMetadata data;

    public RegisterRequest(String email, String password, String nama) {
        this.email    = email;
        this.password = password;
        this.data     = new UserMetadata(nama);
    }

    public static class UserMetadata {
        @SerializedName("nama")
        private String nama;

        public UserMetadata(String nama) { this.nama = nama; }
    }
}