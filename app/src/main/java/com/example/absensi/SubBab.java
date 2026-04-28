package com.example.absensi;

import java.io.Serializable;

public class SubBab implements Serializable {
    private String judul;
    private String konten;

    public SubBab(String judul, String konten) {
        this.judul = judul;
        this.konten = konten;
    }

    public String getJudul() { return judul; }
    public String getKonten() { return konten; }
}