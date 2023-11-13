package com.sprtcoding.baybayin.SpotTheSymbols;

import static com.sprtcoding.baybayin.Games.KEY_SCORE;
import static com.sprtcoding.baybayin.Games.SHARED_PREFS;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.sprtcoding.baybayin.Model.OptionsModel;
import com.sprtcoding.baybayin.Model.QuestionsModel;
import com.sprtcoding.baybayin.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class EasyLevel extends AppCompatActivity {
    public static final String EXTRA_SPOT_EASY_SCORE = "spotEasyScore";
    public static final String EXTRA_SPOT_MODERATE_SCORE = "spotModerateScore";
    public static final String EXTRA_SPOT_HARD_SCORE = "spotHardScore";
    private TextView questionCount, _timerCount, _symbol, _option_1, _option_2, _option_3, _option_4, _option_5
            , _option_6, _option_7, _option_8, _option_9, _option_10, _option_11, _option_12
            , lastBtnClicked = null, score_tv, total_score, score_text;
    private MaterialButton _yes_btn, _no_btn, submitBtn, _next_btn, _ok_btn;
    private LottieAnimationView anim;
    private int currentNoOfTest, totalNoOfTest, score = 0;
    private String answer = "";
    CountDownTimer countDownTimer;
    private Boolean isAnswered;
    private final List<OptionsModel> options = new ArrayList<>();
    private final List<QuestionsModel> questionsModels = new ArrayList<>();
    private QuestionsModel currentQuestion;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_level);
        init();

        addQuestions();

        totalNoOfTest = questionsModels.size();
        Collections.shuffle(questionsModels);

        showNextQuestion();

        setClickListener();

        submitBtn.setOnClickListener(view -> getAnswer(answer));

        startTimer();

    }

    @SuppressLint("SetTextI18n")
    private void showNextQuestion() {
        if(currentNoOfTest < totalNoOfTest) {
            currentQuestion = questionsModels.get(currentNoOfTest);

            _option_1.setText(currentQuestion.getOption1());
            _option_2.setText(currentQuestion.getOption2());
            _option_3.setText(currentQuestion.getOption3());
            _option_4.setText(currentQuestion.getOption4());
            _option_5.setText(currentQuestion.getOption5());
            _option_6.setText(currentQuestion.getOption6());
            _option_7.setText(currentQuestion.getOption7());
            _option_8.setText(currentQuestion.getOption8());
            _option_9.setText(currentQuestion.getOption9());
            _option_10.setText(currentQuestion.getOption10());
            _option_11.setText(currentQuestion.getOption11());
            _option_12.setText(currentQuestion.getOption12());
            _symbol.setText(currentQuestion.getQuestion());
            answer = currentQuestion.getAnswer();

            currentNoOfTest++;
            questionCount.setText("Question: " + currentNoOfTest + "/" + totalNoOfTest);
            isAnswered = false;
        } else {
            countDownTimer.cancel();
            showCompleteDialog();
        }
    }

    private void addQuestions() {
        questionsModels.add(new QuestionsModel(
                "F",
                "A",
                "E",
                "U",
                "R",
                "S",
                "P",
                "Q",
                "L",
                "2",
                "K",
                "G",
                "A",
                "A"
        ));

        questionsModels.add(new QuestionsModel(
                "A",
                "B",
                "C",
                "D",
                "S",
                "Y",
                "W",
                "K",
                "P",
                "L",
                "T",
                "M",
                "B",
                "B"
        ));

        questionsModels.add(new QuestionsModel(
                "F",
                "H",
                "Q",
                "G",
                "Y",
                "U",
                "T",
                "S",
                "J",
                "A",
                "N",
                "K",
                "G",
                "G"
        ));
        questionsModels.add(new QuestionsModel(
                "Z",
                "X",
                "C",
                "V",
                "B",
                "N",
                "D",
                "S",
                "G",
                "H",
                "W",
                "Y",
                "V",
                "V"
        ));
        questionsModels.add(new QuestionsModel(
                "H",
                "G",
                "F",
                "E",
                "W",
                "Q",
                "T",
                "Y",
                "X",
                "B",
                "N",
                "K",
                "E",
                "E"
        ));
    }

    @SuppressLint("SetTextI18n")
    private void getAnswer(String answer) {
        if (options.isEmpty()) {
            // No option is selected, show a toast
            Toast.makeText(this, "Pumili ng sagot.", Toast.LENGTH_SHORT).show();
        } else if (options.get(0).getSelectedAnswer().equals(answer)) {
            score++;
            score_tv.setText("Score: " + score);
            showCorrectDialog();

            // Change the background color of the selected option back to its original color
            if (lastBtnClicked != null) {
                lastBtnClicked.setBackgroundResource(R.color.white);
                lastBtnClicked.setTextColor(Color.BLACK); // Set it to the original color
            }
        } else {
            showWrongDialog();

            // Change the background color of the selected option back to its original color
            if (lastBtnClicked != null) {
                lastBtnClicked.setBackgroundResource(R.color.white);
                lastBtnClicked.setTextColor(Color.BLACK); // Set it to the original color
            }
        }
    }

    private void setClickListener() {
        _option_1.setOnClickListener(view -> {
            String option = _option_1.getText().toString();
            setAnswer(option);
            changeButtonBackground(_option_1);
        });

        _option_2.setOnClickListener(view -> {
            String option = _option_2.getText().toString();
            setAnswer(option);
            changeButtonBackground(_option_2);
        });

        _option_3.setOnClickListener(view -> {
            String option = _option_3.getText().toString();
            setAnswer(option);
            changeButtonBackground(_option_3);
        });

        _option_4.setOnClickListener(view -> {
            String option = _option_4.getText().toString();
            setAnswer(option);
            changeButtonBackground(_option_4);
        });

        _option_5.setOnClickListener(view -> {
            String option = _option_5.getText().toString();
            setAnswer(option);
            changeButtonBackground(_option_5);
        });

        _option_6.setOnClickListener(view -> {
            String option = _option_6.getText().toString();
            setAnswer(option);
            changeButtonBackground(_option_6);
        });

        _option_7.setOnClickListener(view -> {
            String option = _option_7.getText().toString();
            setAnswer(option);
            changeButtonBackground(_option_7);
        });

        _option_8.setOnClickListener(view -> {
            String option = _option_8.getText().toString();
            setAnswer(option);
            changeButtonBackground(_option_8);
        });

        _option_9.setOnClickListener(view -> {
            String option = _option_9.getText().toString();
            setAnswer(option);
            changeButtonBackground(_option_9);
        });

        _option_10.setOnClickListener(view -> {
            String option = _option_10.getText().toString();
            setAnswer(option);
            changeButtonBackground(_option_10);
        });

        _option_11.setOnClickListener(view -> {
            String option = _option_11.getText().toString();
            setAnswer(option);
            changeButtonBackground(_option_11);
        });

        _option_12.setOnClickListener(view -> {
            String option = _option_12.getText().toString();
            setAnswer(option);
            changeButtonBackground(_option_12);
        });
    }

    private void changeButtonBackground(TextView option_1) {
        // Revert the background color of the previously clicked button (if any)
        if (lastBtnClicked != null) {
            lastBtnClicked.setBackgroundResource(R.color.white);
            lastBtnClicked.setTextColor(Color.BLACK);// Set it to the original color or transparent
        }

        // Change the background color of the currently clicked button
        option_1.setBackgroundResource(R.color.green); // You can set any color you want
        option_1.setTextColor(Color.WHITE);

        // Update the lastClickedButton reference to the currently clicked button
        lastBtnClicked = option_1;
    }

    private void setAnswer(String option) {
        options.clear();
        options.add(new OptionsModel(
                option
        ));
    }

    private void init() {
        _timerCount = findViewById(R.id.timerCount);
        _symbol = findViewById(R.id.symbol);
        _option_1 = findViewById(R.id.option_1);
        _option_2 = findViewById(R.id.option_2);
        _option_3 = findViewById(R.id.option_3);
        _option_4 = findViewById(R.id.option_4);
        _option_5 = findViewById(R.id.option_5);
        _option_6 = findViewById(R.id.option_6);
        _option_7 = findViewById(R.id.option_7);
        _option_8 = findViewById(R.id.option_8);
        _option_9 = findViewById(R.id.option_9);
        _option_10 = findViewById(R.id.option_10);
        _option_11 = findViewById(R.id.option_11);
        _option_12 = findViewById(R.id.option_12);
        questionCount = findViewById(R.id.questionCount);
        submitBtn = findViewById(R.id.submitBtn);
        score_tv = findViewById(R.id.score);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    private void startTimer() {
        long totalTime = 60 * 1000;

        countDownTimer = new CountDownTimer(totalTime + 1000, 1000) {
            @Override
            public void onTick(long remainingTime) {
                @SuppressLint("DefaultLocale") String time = String.format("%02d:%02d min",
                        TimeUnit.MILLISECONDS.toMinutes(remainingTime),
                        TimeUnit.MILLISECONDS.toSeconds(remainingTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(remainingTime))
                );
                _timerCount.setText(time);
                // Check if remaining time is 10 seconds and change text color
                if (remainingTime <= 10000) {
                    _timerCount.setTextColor(Color.RED);
                }
            }

            @Override
            public void onFinish() {
                // Timer has finished, show a toast message
                showTimerDialog();
            }
        };

        countDownTimer.start();
    }

    private void showTimerDialog() {
        View timerDialog = LayoutInflater.from(EasyLevel.this).inflate(R.layout.timer_dialog, null);
        AlertDialog.Builder timerDialogBuilder = new AlertDialog.Builder(EasyLevel.this);

        timerDialogBuilder.setView(timerDialog);

        _yes_btn = timerDialog.findViewById(R.id.yes_btn);
        _no_btn = timerDialog.findViewById(R.id.no_btn);
        final AlertDialog timersDialog = timerDialogBuilder.create();

        Objects.requireNonNull(timersDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timersDialog.setCanceledOnTouchOutside(false);

        _yes_btn.setOnClickListener(view -> {
            timersDialog.cancel();
            startTimer();
        });

        _no_btn.setOnClickListener(view -> {
            timersDialog.cancel();
            finish();
        });

        timersDialog.show();
    }

    @SuppressLint("MissingInflatedId")
    private void showCorrectDialog() {
        View correctDialog = LayoutInflater.from(EasyLevel.this).inflate(R.layout.correct_dialog, null);
        AlertDialog.Builder correctDialogBuilder = new AlertDialog.Builder(EasyLevel.this);

        correctDialogBuilder.setView(correctDialog);

        _next_btn = correctDialog.findViewById(R.id.next_btn);
        final AlertDialog correctsDialog = correctDialogBuilder.create();

        Objects.requireNonNull(correctsDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        correctsDialog.setCanceledOnTouchOutside(false);

        _next_btn.setOnClickListener(view -> {
            correctsDialog.cancel();
            showNextQuestion();
        });

        correctsDialog.show();
    }

    @SuppressLint("MissingInflatedId")
    private void showWrongDialog() {
        View wrongDialog = LayoutInflater.from(EasyLevel.this).inflate(R.layout.wrong_dialog, null);
        AlertDialog.Builder wrongDialogBuilder = new AlertDialog.Builder(EasyLevel.this);

        wrongDialogBuilder.setView(wrongDialog);

        _next_btn = wrongDialog.findViewById(R.id.next_btn);
        final AlertDialog wrongsDialog = wrongDialogBuilder.create();

        Objects.requireNonNull(wrongsDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        wrongsDialog.setCanceledOnTouchOutside(false);

        _next_btn.setOnClickListener(view -> {
            wrongsDialog.cancel();
            showNextQuestion();
        });

        wrongsDialog.show();
    }

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    private void showCompleteDialog() {
        View testCompleteDialog = LayoutInflater.from(EasyLevel.this).inflate(R.layout.test_complete_dialog, null);
        AlertDialog.Builder testCompleteDialogBuilder = new AlertDialog.Builder(EasyLevel.this);

        testCompleteDialogBuilder.setView(testCompleteDialog);

        _ok_btn = testCompleteDialog.findViewById(R.id.ok_btn);
        total_score = testCompleteDialog.findViewById(R.id.total_score);
        score_text = testCompleteDialog.findViewById(R.id.score_text);
        anim = testCompleteDialog.findViewById(R.id.anim);

        final AlertDialog testCompletedDialog = testCompleteDialogBuilder.create();

        testCompletedDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        testCompletedDialog.setCanceledOnTouchOutside(false);

        total_score.setText(String.valueOf(score));

        if(score <= 3) {
            score_text.setText("Oh hindi! ikaw ay may pinakamababang iskor!");
            anim.setAnimation(R.raw.sadface);
            anim.playAnimation();
        }else {
            score_text.setText("Mayroon kang Pinakamataas na Score. Magaling ka!");
            anim.setAnimation(R.raw.congrats);
            anim.playAnimation();
        }

        _ok_btn.setOnClickListener(view -> {
            testCompletedDialog.cancel();
            // Save the score in shared preferences
            saveScoreToSharedPreferences(score);
            finish();
        });

        testCompletedDialog.show();
    }

    private void saveScoreToSharedPreferences(int score) {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_SCORE, score);
        editor.apply();
    }
}