package com.example.absensi.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absensi.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;

import java.util.List;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder> {

    private final List<JadwalModel> jadwalList;

    public JadwalAdapter(List<JadwalModel> jadwalList) {
        this.jadwalList = jadwalList;
    }
    public void updateData(List<JadwalModel> newData){
        jadwalList.clear();
        jadwalList.addAll(newData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_jadwal_timeline, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        JadwalModel jadwal = jadwalList.get(position);

        holder.tvJam.setText(
                DashboardUtils.formatJam(jadwal.getJamMulai())
                        + " - "
                        + DashboardUtils.formatJam(jadwal.getJamSelesai())
        );

        holder.tvMapel.setText(jadwal.getMapel());

        holder.tvRuangan.setText(jadwal.getRuangan());

        // sementara
        holder.tvKelas.setText("Kelas");

        holder.chipStatus.setText("Terjadwal");

    }

    @Override
    public int getItemCount() {
        return jadwalList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvJam;
        TextView tvMapel;
        TextView tvKelas;
        TextView tvRuangan;

        Chip chipStatus;

        MaterialCardView dotJadwal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvJam = itemView.findViewById(R.id.tvJam);
            tvMapel = itemView.findViewById(R.id.tvMataPelajaran);
            tvKelas = itemView.findViewById(R.id.tvKelas);
            tvRuangan = itemView.findViewById(R.id.tvRuangan);

            chipStatus = itemView.findViewById(R.id.chipStatus);

            dotJadwal = itemView.findViewById(R.id.dotJadwal);
        }
    }

}