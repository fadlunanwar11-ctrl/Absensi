package com.example.absensi.model.response;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("user")
    private UserData user;

    @SerializedName("error")
    private String error;

    @SerializedName("error_description")
    private String errorDescription;

    public String getAccessToken()      { return accessToken; }
    public UserData getUser()           { return user; }
    public String getError()            { return error; }
    public String getErrorDescription() { return errorDescription; }

    public static class UserData {
        @SerializedName("id")
        private String id;

        @SerializedName("email")
        private String email;

        public String getId()    { return id; }
        public String getEmail() { return email; }
    }
}