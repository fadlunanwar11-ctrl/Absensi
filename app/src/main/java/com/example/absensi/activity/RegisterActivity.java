package com.example.absensi.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.absensi.R;
import com.example.absensi.config.RetrofitClient;
import com.example.absensi.model.request.RegisterRequest;
import com.example.absensi.model.response.AuthResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText etNama, etEmail, etPassword;
    Button btnRegister;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        etNama      = findViewById(R.id.etNama);
        etEmail     = findViewById(R.id.etEmail);
        etPassword  = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        progressBar = findViewById(R.id.progressBar);

        etEmail.setHint("Email");

        btnRegister.setOnClickListener(v -> {
            String nama     = etNama.getText().toString().trim();
            String email    = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (nama.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua field wajib diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 6) {
                Toast.makeText(this, "Password minimal 6 karakter", Toast.LENGTH_SHORT).show();
                return;
            }

            setLoading(true);

            RetrofitClient.getAuthService()
                    .register(new RegisterRequest(email, password, nama))
                    .enqueue(new Callback<AuthResponse>() {

                        @Override
                        public void onResponse(Call<AuthResponse> call,
                                               Response<AuthResponse> response) {
                            setLoading(false);
                            if (response.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this,
                                        "Pendaftaran berhasil!\nTunggu persetujuan admin sebelum login.",
                                        Toast.LENGTH_LONG).show();
                                finish(); // kembali ke halaman login
                            } else {
                                String error = "";

                                try {
                                    if (response.errorBody() != null) {
                                        error = response.errorBody().string();
                                    }
                                } catch (Exception e) {
                                    error = e.getMessage();
                                }

                                Toast.makeText(RegisterActivity.this,
                                        "Error " + response.code() + "\n" + error,
                                        Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AuthResponse> call, Throwable t) {
                            setLoading(false);
                            Toast.makeText(RegisterActivity.this,
                                    "Gagal konek: " + t.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        });

        findViewById(R.id.tvLogin).setOnClickListener(v -> finish());
    }

    private void setLoading(boolean loading) {
        btnRegister.setEnabled(!loading);
        if (progressBar != null)
            progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
    }
    
}