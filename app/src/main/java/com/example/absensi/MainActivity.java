package com.example.absensi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    String userRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Ambil role user
        SharedPreferences pref = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE);
        userRole = pref.getString("role", "guru_mapel");

        bottomNavigationView = findViewById(R.id.bottom_nav);

        // Update title menu navigasi secara dinamis
        Menu menu = bottomNavigationView.getMenu();
        MenuItem navKitabRaport = menu.findItem(R.id.nav_raport);
        if (navKitabRaport != null) {
            if ("wali_kelas".equals(userRole)) {
                navKitabRaport.setTitle("RAPORT");
            } else {
                navKitabRaport.setTitle("KITAB");
            }
        }

        // Set fragment awal yang muncul
        loadFragment(new HomeFragment());

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            // Efek Animasi "Timbul" pada View yang diklik
            View itemView = bottomNavigationView.findViewById(id);
            if (itemView != null) {
                applyPopAnimation(itemView);
            }

            if (id == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.nav_raport) {
                if ("wali_kelas".equals(userRole)) {
                    selectedFragment = new RaportFragment();
                } else {
                    selectedFragment = new KitabFragment();
                }
            } else if (id == R.id.nav_profile) {
                selectedFragment = new ProfileFragment();
            }

            return loadFragment(selectedFragment);
        });

        // Menyesuaikan Padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });
    }

    private void applyPopAnimation(View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                0.8f, 1.0f, 
                0.8f, 1.0f, 
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(200);
        view.startAnimation(scaleAnimation);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
