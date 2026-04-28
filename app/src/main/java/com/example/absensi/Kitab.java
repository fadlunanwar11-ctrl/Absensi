package com.example.absensi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Kitab implements Serializable {
    private String id;
    private String nama;
    private String deskripsi;
    private List<SubBab> subBabList;

    public Kitab(String id, String nama, String deskripsi) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.subBabList = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public String getDeskripsi() { return deskripsi; }
    public List<SubBab> getSubBabList() { return subBabList; }
    
    public void addSubBab(SubBab subBab) {
        this.subBabList.add(subBab);
    }
}