package com.example.absensi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RaportFragment extends Fragment {

    private RecyclerView rvRaport;
    private RekapAdapter adapter;
    private List<Santri> dataSantri;

    public RaportFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_raport, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvRaport = view.findViewById(R.id.rvRaport);
        dataSantri = new ArrayList<>();

        // Load data dummy raport
        loadDataRaport();

        rvRaport.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RekapAdapter(dataSantri); // Menggunakan RekapAdapter untuk tampilan list sederhana
        rvRaport.setAdapter(adapter);
    }

    private void loadDataRaport() {
        dataSantri.add(new Santri("1", "Ahmad Fauzi", "A (Sangat Baik)"));
        dataSantri.add(new Santri("2", "Budi Santoso", "B (Baik)"));
        dataSantri.add(new Santri("3", "Candra Wijaya", "B (Baik)"));
        dataSantri.add(new Santri("4", "Dedi Irawan", "A (Sangat Baik)"));
        dataSantri.add(new Santri("5", "Eko Prasetyo", "C (Cukup)"));
    }
}
