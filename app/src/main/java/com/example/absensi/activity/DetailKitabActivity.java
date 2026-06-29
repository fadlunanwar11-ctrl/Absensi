package com.example.absensi.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absensi.Kitab;
import com.example.absensi.R;
import com.example.absensi.SubBab;
import com.example.absensi.SubBabAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailKitabActivity extends AppCompatActivity {

    private Kitab kitab;
    private RecyclerView rvSubBab;
    private SubBabAdapter adapter;
    private FloatingActionButton fabAddSubBab;
    private SearchView svSubBab;

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

        svSubBab = findViewById(R.id.svSubBab);
        rvSubBab = findViewById(R.id.rvSubBab);
        rvSubBab.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SubBabAdapter(kitab.getSubBabList(), subBab -> {
            Intent intent = new Intent(DetailKitabActivity.this, MateriActivity.class);
            intent.putExtra("sub_bab_data", subBab);
            startActivity(intent);
        });
        rvSubBab.setAdapter(adapter);

        // Fitur Cari Bab
        svSubBab.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });

        fabAddSubBab = findViewById(R.id.fabAddSubBab);

        SharedPreferences pref = getSharedPreferences("ABSENSI_APP", MODE_PRIVATE);
        String role = pref.getString("user_role", "guru_mapel");

        if ("admin".equals(role)) {
            fabAddSubBab.setVisibility(View.VISIBLE);
        } else {
            fabAddSubBab.setVisibility(View.GONE);
        }

        fabAddSubBab.setOnClickListener(v -> showAddSubBabDialog());
    }

    private void showAddSubBabDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_kitab, null);
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
