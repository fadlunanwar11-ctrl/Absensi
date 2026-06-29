package com.example.absensi.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.absensi.R;
import com.example.absensi.activity.AbsenActivity;
import com.example.absensi.activity.DaftarKelasActivity;
import com.example.absensi.activity.PenilaianActivity;
import com.example.absensi.activity.RekapActivity;

public class HomeFragment extends Fragment {

    private String userRole;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. Inisialisasi View Header
        TextView tvUserRole = view.findViewById(R.id.tvUserRole);
        
        // Label Sections
        TextView tvLabelGuru = view.findViewById(R.id.tvLabelGuru);
        TextView tvLabelWali = view.findViewById(R.id.tvLabelWali);
        TextView tvLabelAdmin = view.findViewById(R.id.tvLabelAdmin);
        
        // Container Sections
        GridLayout gridGuru = view.findViewById(R.id.gridGuru);
        GridLayout gridWali = view.findViewById(R.id.gridWali);
        
        // Menu Items
        CardView menuAbsen = view.findViewById(R.id.menuAbsen);
        CardView menuPenilaian = view.findViewById(R.id.menuPenilaian);
        CardView menuRekapMingguan = view.findViewById(R.id.menuRekapMingguan);
        CardView menuRekapBulanan = view.findViewById(R.id.menuRekapBulanan);
        CardView menuRekapNilai = view.findViewById(R.id.menuRekapNilai);
        CardView menuTambahMurid = view.findViewById(R.id.menuTambahMurid);

        // Statistik
        View sectionStatistik = view.findViewById(R.id.sectionStatistik);

        // 2. Ambil data role dari SharedPreferences
        if (getActivity() != null) {
            SharedPreferences pref = getActivity().getSharedPreferences("ABSENSI_APP", Context.MODE_PRIVATE);
            userRole = pref.getString("user_role", "guru_mapel");

            // 3. Update Teks Header & Visibilitas Section sesuai Role
            if (tvUserRole != null) {
                // Reset default visibilities
                if (tvLabelGuru != null) tvLabelGuru.setVisibility(View.VISIBLE);
                if (gridGuru != null) gridGuru.setVisibility(View.VISIBLE);
                if (sectionStatistik != null) sectionStatistik.setVisibility(View.VISIBLE);
                
                switch (userRole) {
                    case "admin":
                        tvUserRole.setText("Administrator");
                        
                        // Admin: Sembunyikan label Wali dan Admin
                        if (tvLabelWali != null) tvLabelWali.setVisibility(View.GONE);
                        if (tvLabelAdmin != null) tvLabelAdmin.setVisibility(View.GONE);
                        
                        // Tampilkan grid wali (untuk akses rekap) tapi sembunyikan Rekap Nilai
                        if (gridWali != null) gridWali.setVisibility(View.VISIBLE);
                        if (menuRekapNilai != null) menuRekapNilai.setVisibility(View.GONE);
                        
                        // Sembunyikan menu Absen, Penilaian, dan Statistik agar benar-benar tidak kelihatan
                        if (menuAbsen != null) menuAbsen.setVisibility(View.GONE);
                        if (menuPenilaian != null) menuPenilaian.setVisibility(View.GONE);
                        if (sectionStatistik != null) sectionStatistik.setVisibility(View.GONE);

                        // Tampilkan menu tambah murid
                        if (menuTambahMurid != null) menuTambahMurid.setVisibility(View.VISIBLE);
                        break;
                        
                    case "wali_kelas":
                        tvUserRole.setText("Wali Kelas");
                        // Wali Kelas: Sembunyikan Guru, Tampilkan Wali
                        if (tvLabelGuru != null) tvLabelGuru.setVisibility(View.GONE);
                        if (gridGuru != null) gridGuru.setVisibility(View.GONE);
                        if (tvLabelWali != null) tvLabelWali.setVisibility(View.VISIBLE);
                        if (gridWali != null) gridWali.setVisibility(View.VISIBLE);
                        if (tvLabelAdmin != null) tvLabelAdmin.setVisibility(View.GONE);
                        if (menuTambahMurid != null) menuTambahMurid.setVisibility(View.GONE);
                        break;
                        
                    case "guru_mapel":
                    default:
                        tvUserRole.setText("Guru Mapel");
                        // Guru: Tampilkan Guru, Sembunyikan Sisanya
                        if (tvLabelGuru != null) tvLabelGuru.setVisibility(View.VISIBLE);
                        if (gridGuru != null) gridGuru.setVisibility(View.VISIBLE);
                        if (tvLabelWali != null) tvLabelWali.setVisibility(View.GONE);
                        if (gridWali != null) gridWali.setVisibility(View.GONE);
                        if (tvLabelAdmin != null) tvLabelAdmin.setVisibility(View.GONE);
                        if (menuTambahMurid != null) menuTambahMurid.setVisibility(View.GONE);
                        break;
                }
            }
        }

        // 4. Fungsi Klik (Menu Navigation)
        if (menuAbsen != null) {
            menuAbsen.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), AbsenActivity.class);
                startActivity(intent);
            });
        }

        if (menuPenilaian != null) {
            menuPenilaian.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), PenilaianActivity.class);
                startActivity(intent);
            });
        }

        if (menuRekapMingguan != null) {
            menuRekapMingguan.setOnClickListener(v -> goToRekapOrKelas("mingguan"));
        }

        if (menuRekapBulanan != null) {
            menuRekapBulanan.setOnClickListener(v -> goToRekapOrKelas("bulanan"));
        }

        if (menuRekapNilai != null) {
            menuRekapNilai.setOnClickListener(v -> goToRekapOrKelas("nilai"));
        }

        if (menuTambahMurid != null) {
            menuTambahMurid.setOnClickListener(v -> {
                // Diarahkan ke DaftarKelasActivity untuk mengelola data murid per kelas
                Intent intent = new Intent(getContext(), DaftarKelasActivity.class);
                intent.putExtra("ACTION_TYPE", "ADD_MURID");
                startActivity(intent);
            });
        }
    }

    private void goToRekapOrKelas(String type) {
        if ("admin".equals(userRole)) {
            // Jika Admin, harus pilih kelas dulu
            Intent intent = new Intent(getContext(), DaftarKelasActivity.class);
            intent.putExtra("REKAP_TYPE", type);
            startActivity(intent);
        } else {
            // Role lain langsung ke rekap
            Intent intent = new Intent(getContext(), RekapActivity.class);
            intent.putExtra("REKAP_TYPE", type);
            startActivity(intent);
        }
    }
}
