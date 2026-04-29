package com.example.absensi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class PenilaianActivity extends AppCompatActivity {

    private RecyclerView rvPenilaian;
    private PenilaianAdapter adapter;
    private List<Santri> santriList;
    private Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penilaian);

        Toolbar toolbar = findViewById(R.id.toolbarPenilaian);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Penilaian Santri");
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        rvPenilaian = findViewById(R.id.rvPenilaian);
        btnSimpan = findViewById(R.id.btnSimpanNilai);

        // Data Dummy Santri
        santriList = new ArrayList<>();
        santriList.add(new Santri("1001", "Ahmad Fauzi", ""));
        santriList.add(new Santri("1002", "Budi Santoso", ""));
        santriList.add(new Santri("1003", "Citra Lestari", ""));
        santriList.add(new Santri("1004", "Dedi Kurniawan", ""));
        santriList.add(new Santri("1005", "Eka Putri", ""));
        santriList.add(new Santri("1006", "Farhan Malik", ""));
        santriList.add(new Santri("1007", "Gita Permata", ""));
        santriList.add(new Santri("1008", "Hadi Wijaya", ""));

        adapter = new PenilaianAdapter(santriList);
        rvPenilaian.setLayoutManager(new LinearLayoutManager(this));
        rvPenilaian.setAdapter(adapter);

        btnSimpan.setOnClickListener(v -> {
            StringBuilder sb = new StringBuilder();
            for (Santri s : santriList) {
                if (!s.getNilai().isEmpty()) {
                    sb.append(s.getNama()).append(": ").append(s.getNilai()).append("\n");
                }
            }
            
            if (sb.length() > 0) {
                Toast.makeText(this, "Nilai Santri Tersimpan:\n" + sb.toString(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Belum ada nilai santri yang diisi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
