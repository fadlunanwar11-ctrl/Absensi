package com.example.absensi.model.response;

import com.google.gson.annotations.SerializedName;

public class ProfileResponse {

    @SerializedName("id")
    private String id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("role")
    private String role;

    @SerializedName("status")
    private String status;

    @SerializedName("kelas_id")
    private Integer kelasId;

    @SerializedName("mapel")
    private String mapel;

    public String getId()      { return id; }
    public String getNama()    { return nama; }
    public String getRole()    { return role; }
    public String getStatus()  { return status; }
    public Integer getKelasId(){ return kelasId; }
    public String getMapel()   { return mapel; }
}