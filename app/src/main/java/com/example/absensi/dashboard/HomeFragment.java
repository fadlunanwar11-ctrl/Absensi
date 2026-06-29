package com.example.absensi.dashboard;

import android.os.Bundle;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.List;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.absensi.R;
import com.example.absensi.activity.AbsenActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    // Header
    private TextView tvWelcome;
    private TextView tvUserRole;

    // RecyclerView Jadwal
    private RecyclerView rvJadwalHariIni;
    private JadwalAdapter jadwalAdapter;

    // Menu
    private GridLayout gridGuru;
    private GridLayout gridWali;

    // Repository
    private DashboardRepository repository;
    private SharedPreferences preferences;

    private String userId;
    private String nama;
    private String role;
    private String accessToken;
    private String mapel;
    private int kelasId;
    private TextView tvLabelGuru;
    private TextView tvLabelWali;
    private TextView tvLabelAdmin;

    private CardView menuTambahMurid;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_home,
                container,
                false
        );

        initView(view);

        setupRecyclerView();
        repository = new DashboardRepository();
        preferences = requireActivity()
                .getSharedPreferences("ABSENSI_APP", Context.MODE_PRIVATE);

        loadProfile();
        return view;
    }

    private void initView(View view){

        tvWelcome = view.findViewById(R.id.tvWelcome);
        tvUserRole = view.findViewById(R.id.tvUserRole);

        rvJadwalHariIni = view.findViewById(R.id.rvJadwalHariIni);

        gridGuru = view.findViewById(R.id.gridGuru);
        gridWali = view.findViewById(R.id.gridWali);
        tvLabelGuru = view.findViewById(R.id.tvLabelGuru);
        tvLabelWali = view.findViewById(R.id.tvLabelWali);
        tvLabelAdmin = view.findViewById(R.id.tvLabelAdmin);

        menuTambahMurid = view.findViewById(R.id.menuTambahMurid);

    }

    private void setupRecyclerView(){

        jadwalAdapter = new JadwalAdapter(
                new ArrayList<>(),
                jadwal -> {

                    Intent intent = new Intent(getActivity(), AbsenActivity.class);

                    intent.putExtra("jadwal_id", jadwal.getId());
                    intent.putExtra("kelas_id", jadwal.getKelasId());
                    intent.putExtra("mapel", jadwal.getMapel());
                    intent.putExtra("jam_mulai", jadwal.getJamMulai());
                    intent.putExtra("jam_selesai", jadwal.getJamSelesai());

                    startActivity(intent);

                }
        );

        rvJadwalHariIni.setLayoutManager(
                new LinearLayoutManager(getContext())
        );

        rvJadwalHariIni.setAdapter(jadwalAdapter);

    }
    private void loadProfile() {

        userId = preferences.getString("user_id", "");

        nama = preferences.getString("nama", "");

        role = preferences.getString("user_role", "");

        accessToken = preferences.getString("access_token", "");

        mapel = preferences.getString("mapel", "");

        kelasId = preferences.getInt("kelas_id", -1);

        tvWelcome.setText("Selamat Datang,\n" + nama);

        tvUserRole.setText(role);
        setupRoleUI();
        loadJadwalHariIni();
    }
    private void loadJadwalHariIni() {

        repository.getJadwalHariIni(
                accessToken,
                userId,
                DashboardUtils.getHariIndonesia()
        ).enqueue(new Callback<List<JadwalModel>>() {

            @Override
            public void onResponse(Call<List<JadwalModel>> call,
                                   Response<List<JadwalModel>> response) {

                if (response.isSuccessful() && response.body() != null) {

                    jadwalAdapter.updateData(response.body());

                } else {

                    Toast.makeText(getContext(),
                            "Gagal memuat jadwal",
                            Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<JadwalModel>> call,
                                  Throwable t) {

                Toast.makeText(getContext(),
                        "Error : " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void setupRoleUI() {

        // Sembunyikan semua dulu
        gridGuru.setVisibility(View.GONE);
        gridWali.setVisibility(View.GONE);

        tvLabelGuru.setVisibility(View.GONE);
        tvLabelWali.setVisibility(View.GONE);
        tvLabelAdmin.setVisibility(View.GONE);

        menuTambahMurid.setVisibility(View.GONE);

        if (role == null) return;

        switch (role) {

            case "guru_mapel":
                tvLabelGuru.setVisibility(View.VISIBLE);
                gridGuru.setVisibility(View.VISIBLE);
                break;

            case "wali_kelas":
                tvLabelGuru.setVisibility(View.VISIBLE);
                gridGuru.setVisibility(View.VISIBLE);

                tvLabelWali.setVisibility(View.VISIBLE);
                gridWali.setVisibility(View.VISIBLE);
                break;

            case "admin":
                tvLabelAdmin.setVisibility(View.VISIBLE);
                menuTambahMurid.setVisibility(View.VISIBLE);
                break;
        }
    }

}