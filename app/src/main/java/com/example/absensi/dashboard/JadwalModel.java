package com.example.absensi.dashboard;

import com.google.gson.annotations.SerializedName;

public class JadwalModel {

    @SerializedName("id")
    private int id;

    @SerializedName("guru_id")
    private String guruId;

    @SerializedName("kelas_id")
    private int kelasId;

    @SerializedName("mapel")
    private String mapel;

    @SerializedName("hari")
    private String hari;

    @SerializedName("jam_mulai")
    private String jamMulai;

    @SerializedName("jam_selesai")
    private String jamSelesai;

    @SerializedName("ruangan")
    private String ruangan;
    @SerializedName("kelas")
    private KelasModel kelas;


    public JadwalModel() {
    }

    public int getId() {
        return id;
    }

    public String getGuruId() {
        return guruId;
    }

    public int getKelasId() {
        return kelasId;
    }

    public String getMapel() {
        return mapel;
    }

    public String getHari() {
        return hari;
    }

    public String getJamMulai() {
        return jamMulai;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public String getRuangan() {
        return ruangan;
    }
    public KelasModel getKelas() {
        return kelas;
    }
    public void setId(int id) {
        this.id = id;
    }


    public void setGuruId(String guruId) {
        this.guruId = guruId;
    }

    public void setKelasId(int kelasId) {
        this.kelasId = kelasId;
    }

    public void setMapel(String mapel) {
        this.mapel = mapel;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public void setJamMulai(String jamMulai) {
        this.jamMulai = jamMulai;
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }
    public void setKelas(KelasModel kelas) {
        this.kelas = kelas;
    }

}