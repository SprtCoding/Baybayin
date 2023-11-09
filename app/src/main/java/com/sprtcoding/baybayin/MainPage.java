package com.sprtcoding.baybayin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.material.button.MaterialButton;
import com.sprtcoding.baybayin.Loading.LoadingDialog;

public class MainPage extends AppCompatActivity {
    private MaterialButton _getStartedBtn;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        _var();

        loadingDialog = new LoadingDialog(this);

        _getStartedBtn.setOnClickListener(view -> {
            loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loadingDialog.dismiss();
                Intent i = new Intent(this, MainDashboard.class);
                startActivity(i);
                finish();
            };
            handler.postDelayed(runnable, 2000);
        });

    }

    private void _var() {
        _getStartedBtn = findViewById(R.id.getStartedBtn);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}