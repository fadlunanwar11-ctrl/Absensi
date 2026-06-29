package com.example.absensi;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absensi.activity.DetailKitabActivity;

import java.util.ArrayList;
import java.util.List;

public class KitabAdapter extends RecyclerView.Adapter<KitabAdapter.KitabViewHolder> {

    private List<Kitab> kitabList;
    private List<Kitab> kitabListFull;

    public KitabAdapter(List<Kitab> kitabList) {
        this.kitabList = kitabList;
        this.kitabListFull = new ArrayList<>(kitabList);
    }

    @NonNull
    @Override
    public KitabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kitab, parent, false);
        return new KitabViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KitabViewHolder holder, int position) {
        Kitab kitab = kitabList.get(position);
        holder.tvNama.setText(kitab.getNama());
        holder.tvDeskripsi.setText(kitab.getDeskripsi());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailKitabActivity.class);
            intent.putExtra("kitab_data", kitab);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return kitabList.size();
    }

    public void filter(String text) {
        kitabList.clear();
        if (text.isEmpty()) {
            kitabList.addAll(kitabListFull);
        } else {
            text = text.toLowerCase();
            for (Kitab item : kitabListFull) {
                if (item.getNama().toLowerCase().contains(text) || 
                    item.getDeskripsi().toLowerCase().contains(text)) {
                    kitabList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class KitabViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvDeskripsi;

        public KitabViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNamaKitab);
            tvDeskripsi = itemView.findViewById(R.id.tvDeskripsiKitab);
        }
    }
}