package com.example.absensi;

public class Kelas {
    private String id;
    private String namaKelas;

    public Kelas(String id, String namaKelas) {
        this.id = id;
        this.namaKelas = namaKelas;
    }

    public String getId() { return id; }
    public String getNamaKelas() { return namaKelas; }
}
