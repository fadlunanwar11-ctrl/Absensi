package com.example.absensi.fragment;

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

import com.example.absensi.R;
import com.example.absensi.activity.LoginActivity;

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

        // Baca data dari SharedPreferences
        android.content.SharedPreferences pref = getActivity()
                .getSharedPreferences("ABSENSI_APP", android.content.Context.MODE_PRIVATE);
        tvName.setText(pref.getString("nama", "-"));
        tvRole.setText(pref.getString("user_role", "-"));

        // 3. Tambahkan Fungsi Klik
        btnEditProfile.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Buka Halaman Edit Profil", Toast.LENGTH_SHORT).show();
        });

        btnChangePassword.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Buka Halaman Ubah Password", Toast.LENGTH_SHORT).show();
        });

        btnLogout.setOnClickListener(v -> {
            // Clear semua session dulu
            pref.edit().clear().apply();
            // Kembali ke LoginActivity
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            getActivity().finish();
        });
    }
}