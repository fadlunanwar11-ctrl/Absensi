package com.example.absensi.dashboard;

import com.google.gson.annotations.SerializedName;

public class SantriModel {

    @SerializedName("id")
    private int id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("kelas_id")
    private int kelasId;

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public int getKelasId() {
        return kelasId;
    }
}