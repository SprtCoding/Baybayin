package com.sprtcoding.baybayin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.sprtcoding.baybayin.SpotTheSymbols.EasyLevel;
import com.sprtcoding.baybayin.SpotTheSymbols.HardLevel;
import com.sprtcoding.baybayin.SpotTheSymbols.ModerateLevel;

public class SpotTheSymbol extends AppCompatActivity {
    private ImageButton _backBtn;
    private CardView _easy_btn, _moderate_btn, _hard_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_the_symbol);
        init();

        _backBtn.setOnClickListener(view -> finish());

        _easy_btn.setOnClickListener(view -> {
            Intent i = new Intent(this, EasyLevel.class);
            startActivity(i);
        });

        _moderate_btn.setOnClickListener(view -> {
            Intent i = new Intent(this, ModerateLevel.class);
            startActivity(i);
        });

        _hard_btn.setOnClickListener(view -> {
            Intent i = new Intent(this, HardLevel.class);
            startActivity(i);
        });

    }

    private void init() {
        _backBtn = findViewById(R.id.backBtn);
        _easy_btn = findViewById(R.id.easy_btn);
        _moderate_btn = findViewById(R.id.moderate_btn);
        _hard_btn = findViewById(R.id.hard_btn);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}