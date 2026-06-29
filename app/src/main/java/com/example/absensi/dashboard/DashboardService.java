package com.example.absensi.dashboard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface DashboardService {

    @GET("jadwal_mengajar")
    Call<List<JadwalModel>> getJadwalHariIni(

            @Header("Authorization") String bearerToken,

            @Query("guru_id") String guruId,

            @Query("hari") String hari,

            @Query("select") String select
    );
    @GET("santri")
    Call<List<SantriModel>> getSantriByKelas(

            @Header("Authorization") String bearerToken,

            @Query("kelas_id") String kelasId,

            @Query("order") String order,

            @Query("select") String select

    );
}