package com.example.absensi.config;

import com.example.absensi.network.AuthService;
import com.example.absensi.network.ProfileService;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit authRetrofit;
    private static Retrofit restRetrofit;

    // Interceptor yang inject apikey saja ke semua request
    // Authorization TIDAK diinject di sini -- dikirim manual per request
    // supaya endpoint profiles bisa pakai access_token user (bukan anon key)
    private static OkHttpClient getHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("apikey", SupabaseConfig.ANON_KEY)  // wajib di semua request
                            .header("Content-Type", "application/json")
                            .build();
                    return chain.proceed(request);
                })
                .addInterceptor(logging)
                .build();
    }

    public static AuthService getAuthService() {
        if (authRetrofit == null) {
            authRetrofit = new Retrofit.Builder()
                    .baseUrl(SupabaseConfig.AUTH_URL)
                    .client(getHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return authRetrofit.create(AuthService.class);
    }

    public static ProfileService getProfileService() {
        if (restRetrofit == null) {
            restRetrofit = new Retrofit.Builder()
                    .baseUrl(SupabaseConfig.REST_URL)
                    .client(getHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return restRetrofit.create(ProfileService.class);
    }
}