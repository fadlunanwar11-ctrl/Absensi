package com.example.absensi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {
    EditText etFullName, etPassword, etValidasiPassword;
    Button btnSignUp;
    TextView tvLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.register);

        etFullName = findViewById(R.id.etFullName);
        etPassword = findViewById(R.id.etPassword);
        etValidasiPassword = findViewById(R.id.etValidasiPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvLogin = findViewById(R.id.tvLogin);

        btnSignUp.setOnClickListener(v -> {
            String fullName = etFullName.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String validasiPassword = etValidasiPassword.getText().toString().trim();

            if (fullName.isEmpty()) {
                etFullName.setError("Full Name harus diisi");
            } else if (password.isEmpty()) {
                etPassword.setError("Password harus diisi");
            } else if (validasiPassword.isEmpty()) {
                etValidasiPassword.setError("Validasi Password harus diisi");
            } else if (!password.equals(validasiPassword)) {
                etValidasiPassword.setError("Password tidak sama");
            } else {
                // Jika semua validasi lolos
                etFullName.setError(null);
                etPassword.setError(null);
                etValidasiPassword.setError(null);

                Toast.makeText(RegisterActivity.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tvLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}