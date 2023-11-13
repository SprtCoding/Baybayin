package com.sprtcoding.baybayin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sprtcoding.baybayin.Loading.LoadingDialog;

public class MainDashboard extends AppCompatActivity {
    private LoadingDialog _loadingDialog;
    private CardView _translateCard, _devCard, _historyCard, _learnCard, _gamesCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);
        _var();

        _loadingDialog = new LoadingDialog(this);

        _historyCard.setOnClickListener(view -> {
            _loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                _loadingDialog.dismiss();
                Intent i = new Intent(this, History.class);
                startActivity(i);
            };
            handler.postDelayed(runnable, 2000);
        });

        _learnCard.setOnClickListener(view -> {
            _loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                _loadingDialog.dismiss();
                Intent i = new Intent(this, LearnPage.class);
                startActivity(i);
            };
            handler.postDelayed(runnable, 2000);
        });

        _translateCard.setOnClickListener(view -> {
            _loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                _loadingDialog.dismiss();
                Intent i = new Intent(this, Translator.class);
                startActivity(i);
            };
            handler.postDelayed(runnable, 2000);
        });

        _gamesCard.setOnClickListener(view -> {
            _loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                _loadingDialog.dismiss();
                Intent i = new Intent(this, Games.class);
                startActivity(i);
            };
            handler.postDelayed(runnable, 2000);
        });

        _devCard.setOnClickListener(view -> {
            _loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                _loadingDialog.dismiss();
                Intent i = new Intent(this, Developer.class);
                startActivity(i);
            };
            handler.postDelayed(runnable, 2000);
        });
    }

    private void _var() {
        _translateCard = findViewById(R.id.translateCard);
        _learnCard = findViewById(R.id.learnCard);
        _historyCard = findViewById(R.id.historyCard);
        _gamesCard = findViewById(R.id.gamesCard);
        _devCard = findViewById(R.id.devCard);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}