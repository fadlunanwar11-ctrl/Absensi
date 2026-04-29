package com.example.absensi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tvRegister;
    Spinner spinnerRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
        spinnerRole = findViewById(R.id.spinnerRole);

        // 1. Setup Spinner Role
        String[] roles = {"Guru Mapel", "Wali Kelas", "Admin"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRole.setAdapter(adapter);

        btnLogin.setOnClickListener(v -> {
            // String user = etUsername.getText().toString();
            // String pass = etPassword.getText().toString();

            // 2. Ambil Role dari Spinner
            String selectedRoleText = spinnerRole.getSelectedItem().toString();
            String roleValue = "guru_mapel"; // default

            if (selectedRoleText.equals("Admin")) {
                roleValue = "admin";
            } else if (selectedRoleText.equals("Wali Kelas")) {
                roleValue = "wali_kelas";
            } else {
                roleValue = "guru_mapel";
            }

            // 3. Simpan Role ke SharedPreferences
            SharedPreferences pref = getSharedPreferences("USER_DATA", MODE_PRIVATE);
            pref.edit().putString("role", roleValue).apply();

            // 4. Pindah ke MainActivity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            
            /* 
            // Logika Login Sederhana (Komentar Sementara)
            String role = "";
            if (user.equals("admin") && pass.equals("admin")) {
                role = "admin";
            } else if (user.equals("wali") && pass.equals("wali")) {
                role = "wali_kelas";
            } else if (user.equals("guru") && pass.equals("guru")) {
                role = "guru_mapel";
            }

            if (!role.isEmpty()) {
                SharedPreferences pref = getSharedPreferences("USER_DATA", MODE_PRIVATE);
                pref.edit().putString("role", role).apply();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "User/Pass Salah!", Toast.LENGTH_LONG).show();
            }
            */
        });

        tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }
}
