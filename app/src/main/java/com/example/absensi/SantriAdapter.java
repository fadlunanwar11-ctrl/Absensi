package com.example.absensi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class SantriAdapter extends RecyclerView.Adapter<SantriAdapter.SantriViewHolder>{
    private List<Santri> santriList;

    public SantriAdapter(List<Santri> santriList) {
        this.santriList = santriList;
    }

    @NonNull
    @Override
    public SantriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_santri, parent, false);
        return new SantriViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SantriViewHolder holder, int position) {
        Santri santri = santriList.get(position);
        holder.tvNama.setText(santri.getNama());

        // 1. Matikan listener sementara agar tidak trigger saat setting status
        holder.rgStatus.setOnCheckedChangeListener(null);

        // 2. Set status RadioButton sesuai data santri
        if ("HADIR".equals(santri.getStatus())) holder.rgStatus.check(R.id.rbHadir);
        else if ("IZIN".equals(santri.getStatus())) holder.rgStatus.check(R.id.rbIzin);
        else if ("SAKIT".equals(santri.getStatus())) holder.rgStatus.check(R.id.rbSakit);
        else if ("ALPA".equals(santri.getStatus())) holder.rgStatus.check(R.id.rbAlpa);
        else holder.rgStatus.clearCheck();

        // 3. Pasang kembali listener untuk mendeteksi perubahan
        holder.rgStatus.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbHadir) santri.setStatus("HADIR");
            else if (checkedId == R.id.rbIzin) santri.setStatus("IZIN");
            else if (checkedId == R.id.rbSakit) santri.setStatus("SAKIT");
            else if (checkedId == R.id.rbAlpa) santri.setStatus("ALPA");
        });
    }

    @Override
    public int getItemCount() {
        return santriList.size();
    }

    public static class SantriViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama;
        RadioGroup rgStatus;
        public SantriViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNama);
            rgStatus = itemView.findViewById(R.id.rgStatus);
        }
    }

}
