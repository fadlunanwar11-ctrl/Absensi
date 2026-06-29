package com.example.absensi.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absensi.PenilaianAdapter;
import com.example.absensi.R;
import com.example.absensi.Santri;

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
        santriList.add(new Santri("", "", ""));


        adapter = new PenilaianAdapter(santriList);
        rvPenilaian.setLayoutManager(new LinearLayoutManager(this));
        rvPenilaian.setAdapter(adapter);

        btnSimpan.setOnClickListener(v -> {
            StringBuilder sb = new StringBuilder();
            for (Santri s : santriList) {
                String nilaiPraktek = s.getNilaiPraktek();
                String nilaiHafalan = s.getNilaiHafalan();
                if (!nilaiPraktek.isEmpty() || !nilaiHafalan.isEmpty()) {
                    sb.append(s.getNama())
                            .append(": Praktek=").append(nilaiPraktek)
                            .append(", Hafalan=").append(nilaiHafalan)
                            .append("\n");
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
