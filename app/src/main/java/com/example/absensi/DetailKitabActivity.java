package com.example.absensi;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailKitabActivity extends AppCompatActivity {

    private Kitab kitab;
    private RecyclerView rvSubBab;
    private SubBabAdapter adapter;
    private FloatingActionButton fabAddSubBab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kitab);

        kitab = (Kitab) getIntent().getSerializableExtra("kitab_data");

        Toolbar toolbar = findViewById(R.id.toolbarDetailKitab);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Detail Kitab");
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        TextView tvNamaKitab = findViewById(R.id.tvDetailNamaKitab);
        tvNamaKitab.setText(kitab.getNama());

        rvSubBab = findViewById(R.id.rvSubBab);
        rvSubBab.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SubBabAdapter(kitab.getSubBabList(), subBab -> {
            Intent intent = new Intent(DetailKitabActivity.this, MateriActivity.class);
            intent.putExtra("sub_bab_data", subBab);
            startActivity(intent);
        });
        rvSubBab.setAdapter(adapter);

        fabAddSubBab = findViewById(R.id.fabAddSubBab);
        
        // Cek Role Admin (Ini bisa dari session/SharedPreferences)
        String role = "admin"; // Contoh manual
        if ("admin".equals(role)) {
            fabAddSubBab.setVisibility(View.VISIBLE);
        }

        fabAddSubBab.setOnClickListener(v -> showAddSubBabDialog());
    }

    private void showAddSubBabDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_kitab, null); // Pakai layout dialog yang sama
        builder.setView(dialogView);

        EditText etJudul = dialogView.findViewById(R.id.etNamaKitab);
        EditText etKonten = dialogView.findViewById(R.id.etDeskripsiKitab);
        etJudul.setHint("Judul Sub Bab");
        etKonten.setHint("Isi Materi / Konten");

        builder.setPositiveButton("Tambah", (dialog, which) -> {
            String judul = etJudul.getText().toString();
            String konten = etKonten.getText().toString();
            if (!judul.isEmpty() && !konten.isEmpty()) {
                kitab.addSubBab(new SubBab(judul, konten));
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Sub Bab ditambahkan", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Batal", null);
        builder.show();
    }
}