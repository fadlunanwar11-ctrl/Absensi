package com.example.absensi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.KelasViewHolder> {
    private List<Kelas> kelasList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Kelas kelas);
    }

    public KelasAdapter(List<Kelas> kelasList, OnItemClickListener listener) {
        this.kelasList = kelasList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public KelasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kelas, parent, false);
        return new KelasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KelasViewHolder holder, int position) {
        Kelas kelas = kelasList.get(position);
        holder.tvNamaKelas.setText(kelas.getNamaKelas());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(kelas));
    }

    @Override
    public int getItemCount() {
        return kelasList.size();
    }

    public static class KelasViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaKelas;
        public KelasViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaKelas = itemView.findViewById(R.id.tvNamaKelas);
        }
    }
}
