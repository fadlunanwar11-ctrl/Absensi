package com.example.absensi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RekapAdapter extends RecyclerView.Adapter<RekapAdapter.RekapViewHolder> {
    private List<Santri> rekapList;

    public RekapAdapter(List<Santri> rekapList) {
        this.rekapList = rekapList;
    }

    @NonNull
    @Override
    public RekapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rekap, parent, false);
        return new RekapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RekapViewHolder holder, int position) {
        Santri santri = rekapList.get(position);
        holder.tvNama.setText(santri.getNama());
        
        // Menampilkan status atau nilai di kolom sebelah kanan
        if (santri.getNilai() != null && !santri.getNilai().isEmpty()) {
            holder.tvStatus.setText(santri.getNilai());
        } else {
            holder.tvStatus.setText(santri.getStatus());
        }
    }

    @Override
    public int getItemCount() {
        return rekapList.size();
    }

    public static class RekapViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvStatus;
        public RekapViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNamaRekap);
            tvStatus = itemView.findViewById(R.id.tvStatusRekap);
        }
    }
}
