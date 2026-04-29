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
        holder.tvNama.setText(santri.getNama());
        holder.tvId.setText("ID: " + santri.getId());
        holder.etNilai.setText(santri.getNilai());

        // Update nilai di list saat user mengetik
        holder.etNilai.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                santri.setNilai(s.toString());
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
        TextView tvNama, tvId;
        EditText etNilai;

        public SantriViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNamaSantri);
            tvId = itemView.findViewById(R.id.tvIdSantri);
            etNilai = itemView.findViewById(R.id.etNilaiSantri);
        }
    }
}
