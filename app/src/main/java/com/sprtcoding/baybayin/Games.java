package com.sprtcoding.baybayin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sprtcoding.baybayin.Loading.LoadingDialog;
import com.sprtcoding.baybayin.SpotTheSymbols.EasyLevel;

public class Games extends AppCompatActivity {
    private ImageButton _backBtn;
    private TextView _view_score, easy_score, moderate_score, hard_score;
    private CardView _spotCard, close_btn;
    private static final int REQUEST_CODE_SPOT = 1;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_SCORE = "keyScoreEasy";
    private int easyScore = 0, moderateScore = 0, hardScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        _var();

        _spotCard.setOnClickListener(view -> {
            Intent i = new Intent(this, SpotTheSymbol.class);
            startActivity(i);
        });

        _backBtn.setOnClickListener(view -> finish());

        //_view_score.setOnClickListener(view -> showScoreDialog());
    }

    private void _var() {
        _backBtn = findViewById(R.id.backBtn);
        _spotCard = findViewById(R.id.spotCard);
        //_view_score = findViewById(R.id.view_score);
    }

    private int loadScoreEasy() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return prefs.getInt(KEY_SCORE, 0); // 0 is the default value if the score is not found
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    @SuppressLint("MissingInflatedId")
    private void showScoreDialog() {
        View scoreDialog = LayoutInflater.from(Games.this).inflate(R.layout.view_score_dialog, null);
        AlertDialog.Builder scoreDialogBuilder = new AlertDialog.Builder(Games.this);

        scoreDialogBuilder.setView(scoreDialog);

        easy_score = scoreDialog.findViewById(R.id.easy_score);
        moderate_score = scoreDialog.findViewById(R.id.moderate_score);
        hard_score = scoreDialog.findViewById(R.id.hard_score);
        close_btn = scoreDialog.findViewById(R.id.close_btn);

        final AlertDialog scoresDialog = scoreDialogBuilder.create();

        scoresDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        scoresDialog.setCanceledOnTouchOutside(false);

        easyScore = loadScoreEasy();
        easy_score.setText(String.valueOf(easyScore));

        close_btn.setOnClickListener(view -> {
            scoresDialog.cancel();
        });

        scoresDialog.show();
    }
}