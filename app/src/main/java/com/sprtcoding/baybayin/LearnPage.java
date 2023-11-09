package com.sprtcoding.baybayin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sprtcoding.baybayin.Loading.LoadingDialog;

public class LearnPage extends AppCompatActivity {
    private ImageButton _backBtn;
    private CardView _quizCard, _traceCard, _ga_bayCard;
    private LoadingDialog _loadingDialog;
    private TextView _highScore;
    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighScore";
    private int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_page);
        _var();

        _loadingDialog = new LoadingDialog(this);

        loadHighScore();

        _quizCard.setOnClickListener(view -> {
            _loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                _loadingDialog.dismiss();
                Intent i = new Intent(this, Quizzes.class);
                startActivityForResult(i, REQUEST_CODE_QUIZ);
            };
            handler.postDelayed(runnable, 2000);
        });

        _traceCard.setOnClickListener(view -> {
            _loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                _loadingDialog.dismiss();
                Intent i = new Intent(this, WordTrace.class);
                startActivityForResult(i, REQUEST_CODE_QUIZ);
            };
            handler.postDelayed(runnable, 2000);
        });

        _ga_bayCard.setOnClickListener(view -> {
            _loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                _loadingDialog.dismiss();
                Intent i = new Intent(this, GaBay.class);
                startActivityForResult(i, REQUEST_CODE_QUIZ);
            };
            handler.postDelayed(runnable, 2000);
        });

        _backBtn.setOnClickListener(view -> {
            Intent i = new Intent(this, MainDashboard.class);
            startActivity(i);
            finish();
        });
    }

    private void _var() {
        _backBtn = findViewById(R.id.backBtn);
        _quizCard = findViewById(R.id.quizCard);
        _traceCard = findViewById(R.id.traceCard);
        _highScore = findViewById(R.id.highScore);
        _ga_bayCard = findViewById(R.id.ga_bayCard);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_QUIZ) {
            if(resultCode == RESULT_OK) {
                int score = data.getIntExtra(Quizzes.EXTRA_SCORE, 0);
                if(score > highScore) {
                    updateHighScore(score);
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void loadHighScore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highScore = prefs.getInt(KEY_HIGHSCORE, 0);
        _highScore.setText("High Score: " + highScore);
    }

    @SuppressLint("SetTextI18n")
    private void updateHighScore(int scoreNew) {
        highScore = scoreNew;
        _highScore.setText("High Score: " + highScore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highScore);
        editor.apply();
    }
}