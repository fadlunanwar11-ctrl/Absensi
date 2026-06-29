package com.example.absensi.dashboard;

import java.text.SimpleDateFormat;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class DashboardUtils {

    /**
     * Mengembalikan nama hari sesuai database Supabase
     * Contoh: Senin, Selasa, Rabu...
     */
    public static String getHariIndonesia() {

        Calendar calendar = Calendar.getInstance();

        switch (calendar.get(Calendar.DAY_OF_WEEK)) {

            case Calendar.MONDAY:
                return "Senin";

            case Calendar.TUESDAY:
                return "Selasa";

            case Calendar.WEDNESDAY:
                return "Rabu";

            case Calendar.THURSDAY:
                return "Kamis";

            case Calendar.FRIDAY:
                return "Jumat";

            case Calendar.SATURDAY:
                return "Sabtu";

            default:
                return "Minggu";
        }
    }

    /**
     * Format jam
     * 08:00:00 -> 08:00
     */
    public static String formatJam(String jam) {

        if (jam == null)
            return "-";

        if (jam.length() >= 5)
            return jam.substring(0,5);

        return jam;
    }

    /**
     * Tanggal hari ini
     * contoh:
     * 29 Juni 2026
     */
    public static String getTanggalIndonesia(){

        SimpleDateFormat sdf =
                new SimpleDateFormat("dd MMMM yyyy", new Locale("id","ID"));

        return sdf.format(Calendar.getInstance().getTime());

    }
    public static String getStatusJadwal(String mulai, String selesai) {

        try {

            String[] mulaiSplit = mulai.substring(0, 5).split(":");
            String[] selesaiSplit = selesai.substring(0, 5).split(":");

            int mulaiMenit = Integer.parseInt(mulaiSplit[0]) * 60 + Integer.parseInt(mulaiSplit[1]);
            int selesaiMenit = Integer.parseInt(selesaiSplit[0]) * 60 + Integer.parseInt(selesaiSplit[1]);

            Calendar sekarang = Calendar.getInstance();
            int sekarangMenit = sekarang.get(Calendar.HOUR_OF_DAY) * 60
                    + sekarang.get(Calendar.MINUTE);

            if (sekarangMenit < mulaiMenit) {
                return "Akan Datang";
            }

            if (sekarangMenit > selesaiMenit) {
                return "Selesai";
            }

            return "Sedang Berlangsung";

        } catch (Exception e) {
            return "Terjadwal";
        }
    }
}