package com.example.absensi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class KitabFragment extends Fragment {

    private RecyclerView rvKitab;
    private KitabAdapter adapter;
    private List<Kitab> kitabList;
    private SearchView svKitab;
    private FloatingActionButton fabAddKitab;

    public KitabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kitab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        svKitab = view.findViewById(R.id.svKitab);
        rvKitab = view.findViewById(R.id.rvKitab);
        fabAddKitab = view.findViewById(R.id.fabAddKitab);
        
        rvKitab.setLayoutManager(new LinearLayoutManager(getContext()));

        // Data awal
        kitabList = new ArrayList<>();
        kitabList.add(new Kitab("1", "Kitab Safinah", "Membahas dasar-dasar fiqih ibadah."));
        kitabList.add(new Kitab("2", "Kitab Jurumiyah", "Dasar-dasar ilmu nahwu (tata bahasa)."));

        adapter = new KitabAdapter(kitabList);
        rvKitab.setAdapter(adapter);

        // --- LOGIKA ROLE REAL ---
        // Mengambil role dari SharedPreferences yang disimpan saat Login
        SharedPreferences pref = getActivity().getSharedPreferences("USER_DATA", Context.MODE_PRIVATE);
        String role = pref.getString("role", "user"); // default "user" jika tidak ketemu

        if ("admin".equals(role)) {
            fabAddKitab.setVisibility(View.VISIBLE);
        } else {
            fabAddKitab.setVisibility(View.GONE);
        }

        fabAddKitab.setOnClickListener(v -> showAddKitabDialog());

        svKitab.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });
    }

    private void showAddKitabDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_kitab, null);
        builder.setView(dialogView);

        EditText etNama = dialogView.findViewById(R.id.etNamaKitab);
        EditText etDeskripsi = dialogView.findViewById(R.id.etDeskripsiKitab);

        builder.setPositiveButton("Tambah", (dialog, which) -> {
            String nama = etNama.getText().toString();
            String deskripsi = etDeskripsi.getText().toString();
            if (!nama.isEmpty() && !deskripsi.isEmpty()) {
                kitabList.add(new Kitab(String.valueOf(kitabList.size() + 1), nama, deskripsi));
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Kitab berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Batal", null);
        builder.show();
    }
}