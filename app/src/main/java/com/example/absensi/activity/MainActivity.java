package com.example.absensi.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.absensi.R;
import com.example.absensi.helper.Constant;
import com.example.absensi.fragment.HomeFragment;
import com.example.absensi.fragment.KitabFragment;
import com.example.absensi.fragment.ProfileFragment;
import com.example.absensi.fragment.RaportFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ambil role dari SharedPreferences (disimpan saat login)
        SharedPreferences pref = getSharedPreferences(Constant.PREF_NAME, MODE_PRIVATE);
        role = pref.getString(Constant.USER_ROLE, "");

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        // Load menu sesuai role
        switch (role) {
            case "admin":
                bottomNav.inflateMenu(R.menu.bottom_nav_admin);
                break;
            case "wali_kelas":
                bottomNav.inflateMenu(R.menu.bottom_nav_wali);
                break;
            case "guru_mapel":
            default:
                bottomNav.inflateMenu(R.menu.bottom_nav_guru);
                break;
        }

        // Tampilkan HomeFragment pertama kali
        loadFragment(new HomeFragment());

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home)    return loadFragment(new HomeFragment());
            if (id == R.id.nav_profile) return loadFragment(new ProfileFragment());
            if (id == R.id.nav_raport)  return loadFragment(new RaportFragment());
            if (id == R.id.nav_kitab)   return loadFragment(new KitabFragment());

            // TODO: belum dibuat, sementara fallback ke HomeFragment
            if (id == R.id.nav_kelola)  return loadFragment(new HomeFragment());
            if (id == R.id.nav_rekap)   return loadFragment(new HomeFragment());
            if (id == R.id.nav_monitor) return loadFragment(new HomeFragment());
            if (id == R.id.nav_bukti)   return loadFragment(new HomeFragment());

            return false;
        });
    }

    private boolean loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        return true;
    }
}