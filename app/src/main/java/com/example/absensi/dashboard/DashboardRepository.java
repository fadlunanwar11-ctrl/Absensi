package com.example.absensi.dashboard;

import com.example.absensi.config.RetrofitClient;

import java.util.List;

import retrofit2.Call;

public class DashboardRepository {

    private final DashboardService dashboardService;

    public DashboardRepository() {
        dashboardService = RetrofitClient.getDashboardService();
    }

    /**
     * Mengambil jadwal mengajar guru berdasarkan hari.
     */
    public Call<List<JadwalModel>> getJadwalHariIni(
            String accessToken,
            String guruId,
            String hari
    ) {

        return dashboardService.getJadwalHariIni(
                "Bearer " + accessToken,
                "eq." + guruId,
                "eq." + hari,
                "*"

        );
    }
    public Call<List<SantriModel>> getSantriByKelas(
            String accessToken,
            int kelasId
    ) {

        return dashboardService.getSantriByKelas(
                "Bearer " + accessToken,
                "eq." + kelasId,
                "nama.asc",
                "*"
        );
    }
}