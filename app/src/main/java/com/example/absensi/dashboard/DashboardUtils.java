package com.example.absensi.dashboard;

import java.text.SimpleDateFormat;
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

}