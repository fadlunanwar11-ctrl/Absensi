package com.example.absensi.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absensi.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;

import java.util.List;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder> {

    private final List<JadwalModel> jadwalList;
    private final OnJadwalClickListener listener;
    public JadwalAdapter(List<JadwalModel> jadwalList,
                         OnJadwalClickListener listener) {

        this.jadwalList = jadwalList;
        this.listener = listener;
    }
    public interface OnJadwalClickListener{
        void onClick(JadwalModel jadwal);
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
        holder.itemView.setOnClickListener(v -> {
            if(listener != null){
                listener.onClick(jadwal);
            }
        });
        holder.tvMapel.setText(jadwal.getMapel());

        holder.tvRuangan.setText(jadwal.getRuangan());

        // sementara
        if (jadwal.getKelas() != null) {

            holder.tvKelas.setText(
                    jadwal.getKelas().getNamaKelas()
            );

            holder.tvRuangan.setText(
                    jadwal.getKelas().getRuangan()
            );

        } else {

            holder.tvKelas.setText("-");
            holder.tvRuangan.setText("-");
        }

        String status = DashboardUtils.getStatusJadwal(
                jadwal.getJamMulai(),
                jadwal.getJamSelesai()
        );

        holder.chipStatus.setText(status);
        Context context = holder.itemView.getContext();

        switch (status) {

            case "Sedang Berlangsung":

                holder.chipStatus.setChipBackgroundColorResource(R.color.green);
                holder.chipStatus.setTextColor(
                        ContextCompat.getColor(context, android.R.color.white)
                );

                holder.dotJadwal.setCardBackgroundColor(
                        ContextCompat.getColor(context, R.color.green)
                );
                break;

            case "Akan Datang":

                holder.chipStatus.setChipBackgroundColorResource(R.color.blue);
                holder.chipStatus.setTextColor(
                        ContextCompat.getColor(context, android.R.color.white)
                );

                holder.dotJadwal.setCardBackgroundColor(
                        ContextCompat.getColor(context, R.color.blue)
                );
                break;

            default:

                holder.chipStatus.setChipBackgroundColorResource(R.color.gray);
                holder.chipStatus.setTextColor(
                        ContextCompat.getColor(context, android.R.color.white)
                );

                holder.dotJadwal.setCardBackgroundColor(
                        ContextCompat.getColor(context, R.color.gray)
                );
                break;
        }


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