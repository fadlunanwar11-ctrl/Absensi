package com.example.absensi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Memasang layout fragment_home
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // --- TEMPAT MENARUH FUNGSI ---

        // 1. Inisialisasi View (Hubungkan ID XML ke Java)
        CardView menuAbsen = view.findViewById(R.id.menuAbsen);
        CardView menuPenilaian = view.findViewById(R.id.menuPenilaian);

        // 2. Berikan Fungsi Klik
        menuAbsen.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Membuka Menu Absen", Toast.LENGTH_SHORT).show();
            // Anda bisa berpindah ke activity/fragment lain di sini
        });

        menuPenilaian.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Membuka Menu Penilaian", Toast.LENGTH_SHORT).show();
        });

        // Contoh: Logika pemisahan role (Sementara manual)
        String role = "guru"; // Nanti ini diambil dari data Login
        if (role.equals("guru")) {
            menuAbsen.setVisibility(View.VISIBLE);
            menuPenilaian.setVisibility(View.VISIBLE);
        }
    }
}