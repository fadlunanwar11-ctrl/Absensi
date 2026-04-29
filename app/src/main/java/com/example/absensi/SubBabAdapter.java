package com.example.absensi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class SubBabAdapter extends RecyclerView.Adapter<SubBabAdapter.SubBabViewHolder> {

    private List<SubBab> subBabList;
    private List<SubBab> subBabListFull;
    private OnSubBabClickListener listener;

    public interface OnSubBabClickListener {
        void onSubBabClick(SubBab subBab);
    }

    public SubBabAdapter(List<SubBab> subBabList, OnSubBabClickListener listener) {
        this.subBabList = subBabList;
        this.subBabListFull = new ArrayList<>(subBabList);
        this.listener = listener;
    }

    @NonNull
    @Override
    public SubBabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_bab, parent, false);
        return new SubBabViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubBabViewHolder holder, int position) {
        SubBab subBab = subBabList.get(position);
        holder.tvNomor.setText(String.valueOf(position + 1));
        holder.tvJudul.setText(subBab.getJudul());
        
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onSubBabClick(subBab);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subBabList.size();
    }

    public void filter(String text) {
        subBabList.clear();
        if (text.isEmpty()) {
            subBabList.addAll(subBabListFull);
        } else {
            text = text.toLowerCase();
            for (SubBab item : subBabListFull) {
                if (item.getJudul().toLowerCase().contains(text)) {
                    subBabList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class SubBabViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomor, tvJudul;

        public SubBabViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomor = itemView.findViewById(R.id.tvNomorSubBab);
            tvJudul = itemView.findViewById(R.id.tvJudulSubBab);
        }
    }
}
