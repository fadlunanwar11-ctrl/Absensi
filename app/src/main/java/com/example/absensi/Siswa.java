package com.example.absensi;

public class Siswa {
    private String id;
    private String nama;
    private String nilai;

    public Siswa(String id, String nama, String nilai) {
        this.id = id;
        this.nama = nama;
        this.nilai = nilai;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public String getNilai() { return nilai; }
    public void setNilai(String nilai) { this.nilai = nilai; }
}
