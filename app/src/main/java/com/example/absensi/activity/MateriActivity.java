package com.example.absensi.activity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.absensi.R;
import com.example.absensi.SubBab;

public class MateriActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);

        SubBab subBab = (SubBab) getIntent().getSerializableExtra("sub_bab_data");

        Toolbar toolbar = findViewById(R.id.toolbarMateri);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Materi");
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        TextView tvJudul = findViewById(R.id.tvMateriJudul);
        TextView tvKonten = findViewById(R.id.tvMateriKonten);

        if (subBab != null) {
            tvJudul.setText(subBab.getJudul());
            tvKonten.setText(subBab.getKonten());
        }
    }
}