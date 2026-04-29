package com.example.absensi;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DaftarKelasActivity extends AppCompatActivity {

    private RecyclerView rvKelas;
    private KelasAdapter adapter;
    private List<Kelas> kelasList;
    private String rekapType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_kelas);

        rekapType = getIntent().getStringExtra("REKAP_TYPE");

        rvKelas = findViewById(R.id.rvKelas);
        FloatingActionButton fabAddKelas = findViewById(R.id.fabAddKelas);

        kelasList = new ArrayList<>();
        kelasList.add(new Kelas("1", "Kelas 7A"));
        kelasList.add(new Kelas("2", "Kelas 7B"));
        kelasList.add(new Kelas("3", "Kelas 8A"));

        adapter = new KelasAdapter(kelasList, kelas -> {
            Intent intent = new Intent(DaftarKelasActivity.this, RekapActivity.class);
            intent.putExtra("REKAP_TYPE", rekapType);
            intent.putExtra("KELAS_ID", kelas.getId());
            intent.putExtra("NAMA_KELAS", kelas.getNamaKelas());
            startActivity(intent);
        });

        rvKelas.setLayoutManager(new LinearLayoutManager(this));
        rvKelas.setAdapter(adapter);

        fabAddKelas.setOnClickListener(v -> showAddKelasOptions());
    }

    private void showAddKelasOptions() {
        String[] options = {"Tambah Kelas Manual", "Impor Murid via Excel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pilih Aksi");
        builder.setItems(options, (dialog, which) -> {
            if (which == 0) {
                showAddKelasDialog();
            } else {
                importExcel();
            }
        });
        builder.show();
    }

    private void showAddKelasDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_kitab, null); // Reusing dialog layout for simplicity
        builder.setView(view);

        EditText etNamaKelas = view.findViewById(R.id.etNamaKitab);
        etNamaKelas.setHint("Nama Kelas (Contoh: Kelas 9C)");
        view.findViewById(R.id.etDeskripsiKitab).setVisibility(View.GONE);

        builder.setPositiveButton("Tambah", (dialog, which) -> {
            String nama = etNamaKelas.getText().toString();
            if (!nama.isEmpty()) {
                kelasList.add(new Kelas(String.valueOf(kelasList.size() + 1), nama));
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Kelas berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Batal", null);
        builder.show();
    }

    private void importExcel() {
        // Implementasi seleksi file excel
        Toast.makeText(this, "Membuka File Picker untuk Excel...", Toast.LENGTH_SHORT).show();
        // Disini biasanya menggunakan Intent.ACTION_GET_CONTENT
    }
}
