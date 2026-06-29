package com.example.absensi;

import java.io.Serializable;
import java.util.Collection;

public class Santri implements Serializable {
    private String id;
    private String nama;
    private String nilaiPraktek = "";
    private String nilaiHafalan = "";
    private String nilaiHarian = "";
    private String nilaiUTS = "";
    private String nilaiUAS = "";
    private String status = "";

    public Santri(String id, String nama, String s) {
        this.id = id;
        this.nama = nama;
    }

    public Santri(String nama) {
        this.nama = nama;
        this.id = java.util.UUID.randomUUID().toString();
    }

    public String getId() { return id; }
    public String getNama() { return nama; }

    public String getNilaiPraktek() { return nilaiPraktek; }
    public void setNilaiPraktek(String nilaiPraktek) { this.nilaiPraktek = nilaiPraktek; }

    public String getNilaiHafalan() { return nilaiHafalan; }
    public void setNilaiHafalan(String nilaiHafalan) { this.nilaiHafalan = nilaiHafalan; }

    public String getNilaiHarian() { return nilaiHarian; }
    public void setNilaiHarian(String nilaiHarian) { this.nilaiHarian = nilaiHarian; }

    public String getNilaiUTS() { return nilaiUTS; }
    public void setNilaiUTS(String nilaiUTS) { this.nilaiUTS = nilaiUTS; }

    public String getNilaiUAS() { return nilaiUAS; }
    public void setNilaiUAS(String nilaiUAS) { this.nilaiUAS = nilaiUAS; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getRataRata() {
        try {
            double harian = nilaiHarian.isEmpty() ? 0 : Double.parseDouble(nilaiHarian);
            double uts = nilaiUTS.isEmpty() ? 0 : Double.parseDouble(nilaiUTS);
            double uas = nilaiUAS.isEmpty() ? 0 : Double.parseDouble(nilaiUAS);
            return (harian + uts + uas) / 3.0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }


    public Collection<Object> getNilai() {
        return null;
    }
}
