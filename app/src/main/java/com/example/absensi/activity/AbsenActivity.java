package com.example.absensi.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absensi.R;
import com.example.absensi.Santri;
import com.example.absensi.SantriAdapter;

import java.util.ArrayList;
import java.util.List;

public class AbsenActivity extends AppCompatActivity {
    private int jadwalId;
    private int kelasId;

    private String mapel;
    private String jamMulai;
    private String jamSelesai;
    private List<Santri> dataSantri;
    private SantriAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_absen);

        RecyclerView rvAbsenSantri = findViewById(R.id.rvAbsenSantri);

        //untuk list absensi
        dataSantri = new ArrayList<>();
        adapter = new SantriAdapter(dataSantri);
        rvAbsenSantri.setLayoutManager(new LinearLayoutManager(this));
        rvAbsenSantri.setAdapter(adapter);

        jadwalId = getIntent().getIntExtra("jadwal_id", -1);
        kelasId = getIntent().getIntExtra("kelas_id", -1);
        mapel = getIntent().getStringExtra("mapel");
        jamMulai = getIntent().getStringExtra("jam_mulai");
        jamSelesai = getIntent().getStringExtra("jam_selesai");

        Toolbar toolbar = findViewById(R.id.toolbarAbsen);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Absen Santri");
        }
        toolbar.setNavigationOnClickListener(v -> finish());
        // Memasang Adapter
        adapter = new SantriAdapter(dataSantri);
        rvAbsenSantri.setLayoutManager(new LinearLayoutManager(this));
        rvAbsenSantri.setAdapter(adapter);

        // Fungsi Tombol Simpan
        findViewById(R.id.btnSimpanAbsen).setOnClickListener(v -> {
            simpanDataAbsen();
        });

        // Menangani Padding (biar tidak tertutup navigasi sistem)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.absenactivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void simpanDataAbsen() {
        int hadir = 0, izin = 0, sakit = 0, alpa = 0;

        for (Santri s : dataSantri) {
            switch (s.getStatus()) {
                case "HADIR": hadir++; break;
                case "IZIN": izin++; break;
                case "SAKIT": sakit++; break;
                case "ALPA": alpa++; break;
            }
        }

        String ringkasan = "Hadir: " + hadir + ", Izin: " + izin + ", Sakit: " + sakit + ", Alpa: " + alpa;
        Toast.makeText(this, "Berhasil Disimpan!\n" + ringkasan, Toast.LENGTH_LONG).show();
        
        // Selesai dan kembali ke halaman sebelumnya
        // finish(); 
    }
}
