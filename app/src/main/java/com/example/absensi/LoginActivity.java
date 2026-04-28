package com.example.absensi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(v -> {
            String user = etUsername.getText().toString();
            String pass = etPassword.getText().toString();
/*
            // Logika Login Sederhana (Ganti dengan Database nanti)
            String role = "";
            if (user.equals("admin") && pass.equals("admin")) {
                role = "admin";
            } else if (user.equals("wali") && pass.equals("wali")) {
                role = "wali_kelas";
            } else if (user.equals("guru") && pass.equals("guru")) {
                role = "guru_mapel";
            }

            if (!role.isEmpty()) {
                // Simpan Role ke SharedPreferences
                SharedPreferences pref = getSharedPreferences("USER_DATA", MODE_PRIVATE);
                pref.edit().putString("role", role).apply();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "User/Pass Salah! (admin/admin, wali/wali, guru/guru)", Toast.LENGTH_LONG).show();
            }*/
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }
}