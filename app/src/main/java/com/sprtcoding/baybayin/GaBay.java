package com.sprtcoding.baybayin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

public class GaBay extends AppCompatActivity {
    private PDFView pdfView;
    private ImageButton _backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ga_bay);
        _init();

        // Load a PDF file from assets folder (change the file path as needed)
        pdfView.fromAsset("gabay.pdf")
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();

        _backBtn.setOnClickListener(view -> {
            Intent i = new Intent(this, MainDashboard.class);
            startActivity(i);
            finish();
        });
    }

    private void _init() {
        pdfView = findViewById(R.id.pdfView);
        _backBtn = findViewById(R.id.backBtn);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}