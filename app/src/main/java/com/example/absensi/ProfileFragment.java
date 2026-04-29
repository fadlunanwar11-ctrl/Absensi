package com.example.absensi;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 1. Inflate layout untuk fragment ini
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 2. Inisialisasi View (Cari ID dari layout)
        TextView tvName = view.findViewById(R.id.tvProfileName);
        TextView tvRole = view.findViewById(R.id.tvProfileRole);
        LinearLayout btnEditProfile = view.findViewById(R.id.btnEditProfile);
        LinearLayout btnChangePassword = view.findViewById(R.id.btnChangePassword);
        Button btnLogout = view.findViewById(R.id.btnLogout);

        // Contoh set data (Nanti bisa diambil dari database/sharedpreferences)
        tvName.setText("Roni");
        tvRole.setText("admin");

        // 3. Tambahkan Fungsi Klik
        btnEditProfile.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Buka Halaman Edit Profil", Toast.LENGTH_SHORT).show();
        });

        btnChangePassword.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Buka Halaman Ubah Password", Toast.LENGTH_SHORT).show();
        });

        btnLogout.setOnClickListener(v -> {
            // Fungsi Logout: Kembali ke LoginActivity
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish(); // Menutup MainActivity agar tidak bisa "back" lagi
        });
    }
}