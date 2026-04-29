package com.example.absensi;

public class Santri {
    private String id;
    private String nama;
    private String nilai;
    private String status = "";

    public Santri(String id, String nama, String nilai) {
        this.id = id;
        this.nama = nama;
        this.nilai = nilai;
    }

    public Santri(String nama) {
        this.nama = nama;
        this.id = "";
        this.nilai = "";
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public String getNilai() { return nilai; }
    public void setNilai(String nilai) { this.nilai = nilai; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
