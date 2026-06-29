package com.example.absensi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.absensi.R;
import com.example.absensi.config.RetrofitClient;
import com.example.absensi.config.SupabaseConfig;
import com.example.absensi.helper.Constant;
import com.example.absensi.model.request.LoginRequest;
import com.example.absensi.model.response.AuthResponse;
import com.example.absensi.model.response.ProfileResponse;
import com.example.absensi.session.SessionManager;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    TextView tvRegister;
    ProgressBar progressBar;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        sessionManager = new SessionManager(this);

        etEmail     = findViewById(R.id.etUsername);
        etPassword  = findViewById(R.id.etPassword);
        btnLogin    = findViewById(R.id.btnLogin);
        tvRegister  = findViewById(R.id.tvRegister);
        progressBar = findViewById(R.id.progressBar);

        etEmail.setHint("Email");

        btnLogin.setOnClickListener(v -> {
            String email    = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email dan password wajib diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            setLoading(true);

            RetrofitClient.getAuthService()
                    .login(new LoginRequest(email, password))
                    .enqueue(new Callback<AuthResponse>() {

                        @Override
                        public void onResponse(Call<AuthResponse> call,
                                               Response<AuthResponse> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                AuthResponse auth = response.body();
                                if (auth.getError() != null) {
                                    setLoading(false);
                                    Toast.makeText(LoginActivity.this,
                                            "Login gagal: " + auth.getErrorDescription(),
                                            Toast.LENGTH_LONG).show();
                                    return;
                                }
                                // Simpan token via SessionManager
                                sessionManager.saveToken(auth.getAccessToken());
                                ambilProfile(auth.getAccessToken(), auth.getUser().getId());
                            } else {
                                setLoading(false);

                                try {
                                    String error = response.errorBody() != null
                                            ? response.errorBody().string()
                                            : "";

                                    android.util.Log.e("SUPABASE_LOGIN", error);

                                    Toast.makeText(
                                            LoginActivity.this,
                                            "HTTP " + response.code() + error,
                                            Toast.LENGTH_LONG
                                    ).show();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<AuthResponse> call, Throwable t) {
                            setLoading(false);
                            Toast.makeText(LoginActivity.this,
                                    "Gagal konek: " + t.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        });

        tvRegister.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class))
        );
    }

    private void ambilProfile(String accessToken, String userId) {
        RetrofitClient.getProfileService()
                .getProfile(
                        "Bearer " + accessToken,
                        SupabaseConfig.ANON_KEY,
                        "eq." + userId,
                        "id,nama,role,status,kelas_id,mapel"
                )
                .enqueue(new Callback<List<ProfileResponse>>() {

                    @Override
                    public void onResponse(Call<List<ProfileResponse>> call,
                                           Response<List<ProfileResponse>> response) {
                        setLoading(false);

                        if (response.isSuccessful() && response.body() != null
                                && !response.body().isEmpty()) {

                            ProfileResponse profile = response.body().get(0);

                            if ("PENDING".equals(profile.getStatus())) {
                                Toast.makeText(LoginActivity.this,
                                        "Akunmu sedang menunggu persetujuan admin",
                                        Toast.LENGTH_LONG).show();
                                return;
                            }

                            if ("INACTIVE".equals(profile.getStatus())) {
                                Toast.makeText(LoginActivity.this,
                                        "Akunmu telah dinonaktifkan, hubungi admin",
                                        Toast.LENGTH_LONG).show();
                                return;
                            }

                            // Simpan data user ke SharedPreferences via Constant
                            getSharedPreferences("ABSENSI_APP", MODE_PRIVATE).edit()
                                    .putString("user_id",      profile.getId())
                                    .putString("nama",         profile.getNama())
                                    .putString("user_role",    profile.getRole())   // <-- key diganti
                                    .putString("access_token", accessToken)
                                    .putString("mapel",        profile.getMapel())
                                    .putInt("kelas_id", profile.getKelasId() != null ? profile.getKelasId() : -1)
                                    .apply();

                            // Masuk MainActivity
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(LoginActivity.this,
                                    "Profile tidak ditemukan, hubungi admin",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProfileResponse>> call, Throwable t) {
                        setLoading(false);
                        Toast.makeText(LoginActivity.this,
                                "Gagal ambil profile: " + t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void setLoading(boolean loading) {
        btnLogin.setEnabled(!loading);
        if (progressBar != null)
            progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
    }
}