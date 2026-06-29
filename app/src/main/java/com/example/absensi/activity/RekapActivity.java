package com.example.absensi.activity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absensi.R;
import com.example.absensi.RekapAdapter;
import com.example.absensi.Santri;

import java.util.ArrayList;
import java.util.List;

public class RekapActivity extends AppCompatActivity {

    private RecyclerView rvRekap;
    private RekapAdapter adapter;
    private List<Santri> dataSantri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap);

        TextView tvTitle = findViewById(R.id.tvTitleRekap);
        TextView tvPeriode = findViewById(R.id.tvPeriodeRekap);
        TextView tvContent = findViewById(R.id.tvContentRekap);
        rvRekap = findViewById(R.id.rvRekap);

        String type = getIntent().getStringExtra("REKAP_TYPE");
        String namaKelas = getIntent().getStringExtra("NAMA_KELAS");
        
        dataSantri = new ArrayList<>();

        if (type != null) {
            String titlePrefix = "";
            switch (type) {
                case "mingguan":
                    titlePrefix = "REKAP MINGGUAN";
                    tvPeriode.setText("Periode: Minggu Ini (Februari 2024)");
                    loadDataMingguan();
                    break;
                case "bulanan":
                    titlePrefix = "REKAP BULANAN";
                    tvPeriode.setText("Periode: Februari 2024");
                    loadDataBulanan();
                    break;
                case "nilai":
                    titlePrefix = "REKAP NILAI";
                    tvPeriode.setText("Periode: Semester Genap 2024");
                    loadDataNilai();
                    break;
            }
            
            if (namaKelas != null) {
                tvTitle.setText(titlePrefix + " - " + namaKelas);
            } else {
                tvTitle.setText(titlePrefix);
            }
            tvContent.setText("Menampilkan data untuk " + (namaKelas != null ? namaKelas : "seluruh kelas") + ":");
        }

        rvRekap.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RekapAdapter(dataSantri);
        rvRekap.setAdapter(adapter);
    }

    private void loadDataMingguan() {
        dataSantri.add(new Santri("Ahmad Fauzi"));
        dataSantri.get(0).setStatus("HADIR (5), IZIN (1)");
        dataSantri.add(new Santri("Budi Santoso"));
        dataSantri.get(1).setStatus("HADIR (6), ALPA (0)");
        dataSantri.add(new Santri("Candra Wijaya"));
        dataSantri.get(2).setStatus("HADIR (4), SAKIT (2)");
    }

    private void loadDataBulanan() {
        dataSantri.add(new Santri("Ahmad Fauzi"));
        dataSantri.get(0).setStatus("H: 22, I: 2, S: 1, A: 0");
        dataSantri.add(new Santri("Budi Santoso"));
        dataSantri.get(1).setStatus("H: 25, I: 0, S: 0, A: 0");
        dataSantri.add(new Santri("Candra Wijaya"));
        dataSantri.get(2).setStatus("H: 20, I: 1, S: 4, A: 0");
    }

    private void loadDataNilai() {
        dataSantri.add(new Santri("1", "Ahmad Fauzi", "85"));
        dataSantri.add(new Santri("2", "Budi Santoso", "92"));
        dataSantri.add(new Santri("3", "Candra Wijaya", "78"));
        dataSantri.add(new Santri("4", "Dedi Irawan", "88"));
    }
}