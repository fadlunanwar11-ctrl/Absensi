package com.example.absensi.dashboard;

import com.google.gson.annotations.SerializedName;

public class KelasModel {

    @SerializedName("nama_kelas")
    private String namaKelas;

    @SerializedName("ruangan")
    private String ruangan;

    public String getNamaKelas() {
        return namaKelas;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }
}