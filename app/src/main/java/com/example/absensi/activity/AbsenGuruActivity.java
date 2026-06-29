package com.example.absensi.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.absensi.R;
import com.google.android.material.button.MaterialButton;

public class AbsenGuruActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imgPreviewBukti;
    private LinearLayout placeholderCamera;
    private MaterialButton btnUploadBukti, btnBatalFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bukti_masuk);

        imgPreviewBukti = findViewById(R.id.imgPreviewBukti);
        placeholderCamera = findViewById(R.id.placeholderCamera);
        btnUploadBukti = findViewById(R.id.btnUploadBukti);
        btnBatalFoto = findViewById(R.id.btnBatalFoto);

        placeholderCamera.setOnClickListener(v -> openCamera());
        imgPreviewBukti.setOnClickListener(v -> openCamera());

        btnUploadBukti.setOnClickListener(v -> {
            if (imgPreviewBukti.getVisibility() == View.VISIBLE) {
                Toast.makeText(this, "Bukti mengajar berhasil diupload!", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Silakan ambil foto bukti mengajar terlebih dahulu", Toast.LENGTH_SHORT).show();
            }
        });

        btnBatalFoto.setOnClickListener(v -> finish());
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgPreviewBukti.setImageBitmap(imageBitmap);
            imgPreviewBukti.setVisibility(View.VISIBLE);
            placeholderCamera.setVisibility(View.GONE);
        }
    }
}
