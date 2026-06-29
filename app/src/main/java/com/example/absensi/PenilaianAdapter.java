package com.example.absensi;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PenilaianAdapter extends RecyclerView.Adapter<PenilaianAdapter.SantriViewHolder> {

    private List<Santri> santriList;

    public PenilaianAdapter(List<Santri> santriList) {
        this.santriList = santriList;
    }

    @NonNull
    @Override
    public SantriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_santri_penilaian, parent, false);
        return new SantriViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SantriViewHolder holder, int position) {
        Santri santri = santriList.get(position);
        holder.tvNo.setText(String.valueOf(position + 1));
        holder.tvNama.setText(santri.getNama());
        
        holder.etNilaiPraktek.setText(santri.getNilaiPraktek());
        holder.etNilaiHafalan.setText(santri.getNilaiHafalan());

        holder.etNilaiPraktek.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                santri.setNilaiPraktek(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        holder.etNilaiHafalan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                santri.setNilaiHafalan(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    @Override
    public int getItemCount() {
        return santriList.size();
    }

    public static class SantriViewHolder extends RecyclerView.ViewHolder {
        TextView tvNo, tvNama;
        EditText etNilaiPraktek, etNilaiHafalan;

        public SantriViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNo = itemView.findViewById(R.id.tvNo);
            tvNama = itemView.findViewById(R.id.tvNamaSantri);
            etNilaiPraktek = itemView.findViewById(R.id.etNilaiPraktek);
            etNilaiHafalan = itemView.findViewById(R.id.etNilaiHafalan);
        }
    }
}
