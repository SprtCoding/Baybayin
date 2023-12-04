package com.sprtcoding.baybayin.SpotTheSymbols;

import static com.sprtcoding.baybayin.Games.KEY_SCORE;
import static com.sprtcoding.baybayin.Games.SHARED_PREFS;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.sprtcoding.baybayin.Model.HardModel;
import com.sprtcoding.baybayin.Model.OptionsModel;
import com.sprtcoding.baybayin.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class HardLevel extends AppCompatActivity {
    TextView questionCount, _timerCount, _symbol, _option_1, _option_2, _option_3, lastBtnClicked = null, score_tv, total_score, score_text;
    MaterialButton _yes_btn, _no_btn, submitBtn, _next_btn, _ok_btn;
    LottieAnimationView anim;
    private int currentNoOfTest, totalNoOfTest, score = 0;
    private String answer = "";
    CountDownTimer countDownTimer;
    Boolean isAnswered;
    private final List<OptionsModel> options = new ArrayList<>();
    private final List<HardModel> questionsModels = new ArrayList<>();
    HardModel currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_level);
        _init();

        addQuestions();

        totalNoOfTest = questionsModels.size();
        Collections.shuffle(questionsModels);

        showNextQuestion();

        startTimer();

        setClickListener();

        submitBtn.setOnClickListener(view -> getAnswer(answer));
    }

    private void startTimer() {
        long totalTime = 20 * 60 * 1000;

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
        View timerDialog = LayoutInflater.from(HardLevel.this).inflate(R.layout.timer_dialog, null);
        AlertDialog.Builder timerDialogBuilder = new AlertDialog.Builder(HardLevel.this);

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

    @SuppressLint("SetTextI18n")
    private void getAnswer(String answer) {
        if (options.isEmpty()) {
            // No option is selected, show a toast
            Toast.makeText(this, "Hanapin ang sagot, bago magpatuloy.", Toast.LENGTH_SHORT).show();
        } else if (options.get(0).getSelectedAnswer().equals(answer)) {
            score++;
            score_tv.setText("Puntos: " + score);
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

    @SuppressLint("MissingInflatedId")
    private void showCorrectDialog() {
        View correctDialog = LayoutInflater.from(HardLevel.this).inflate(R.layout.correct_dialog, null);
        AlertDialog.Builder correctDialogBuilder = new AlertDialog.Builder(HardLevel.this);

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
        View wrongDialog = LayoutInflater.from(HardLevel.this).inflate(R.layout.wrong_dialog, null);
        AlertDialog.Builder wrongDialogBuilder = new AlertDialog.Builder(HardLevel.this);

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

    private void setClickListener() {
        _option_1.setOnClickListener(view -> {
            String option = _option_1.getText().toString().trim();
            setAnswer(option);
            changeButtonBackground(_option_1);
        });

        _option_2.setOnClickListener(view -> {
            String option = _option_2.getText().toString().trim();
            setAnswer(option);
            changeButtonBackground(_option_2);
        });

        _option_3.setOnClickListener(view -> {
            String option = _option_3.getText().toString().trim();
            setAnswer(option);
            changeButtonBackground(_option_3);
        });
    }

    private void setAnswer(String option) {
        options.clear();
        options.add(new OptionsModel(
                option
        ));
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

    private void addQuestions() {
        //1
        questionsModels.add(new HardModel(
                "ᜉᜒᜎᜒᜉᜒᜈᜐ᜔",
                "ᜉᜒᜉᜒᜈ᜔",
                "ᜉᜒᜎᜒᜉᜒᜈ᜔",
                "Pilipinas",
                "ᜉᜒᜎᜒᜉᜒᜈᜐ᜔"
        ));
        //2
        questionsModels.add(new HardModel(
                "ᜋᜄᜈ᜔ᜇᜅ᜔ ᜀ\u170Dᜏ᜔᜔᜔᜔",
                "ᜋᜄᜈ᜔ᜇᜅ᜔ ᜂᜋᜄ",
                "ᜋᜄᜈ᜔ᜇᜅ᜔ ᜑᜉ᜔ᜈ᜔",
                "Magandang Araw",
                "ᜋᜄᜈ᜔ᜇᜅ᜔ ᜀ\u170Dᜏ᜔᜔᜔᜔"
        ));
        //3
        questionsModels.add(new HardModel(
                "ᜊᜑᜌ᜔",
                "ᜊᜌ᜔ᜊᜌᜒᜈ᜔",
                "ᜊᜌᜈᜒ",
                "Baybayin",
                "ᜊᜌ᜔ᜊᜌᜒᜈ᜔"
        ));
        //4
        questionsModels.add(new HardModel(
                "ᜋᜄᜈ᜔ᜇᜅ᜔ ᜑᜉ᜔ᜈ᜔",
                "ᜋᜄᜈ᜔ᜇᜅ᜔ ᜀᜃ᜔",
                "ᜋᜄᜈ᜔ᜇᜅ᜔ ᜂᜋᜄ",
                "Magandang Umaga",
                "ᜋᜄᜈ᜔ᜇᜅ᜔ ᜂᜋᜄ"
        ));
        //5
        questionsModels.add(new HardModel(
                "ᜃᜓᜋᜓᜐ᜔ᜆ",
                "ᜃᜓᜋᜁᜈ᜔",
                "ᜃᜓᜋᜈ᜔ᜆ",
                "Kumusta",
                "ᜃᜓᜋᜓᜐ᜔ᜆ"
        ));
        //6
        questionsModels.add(new HardModel(
                "ᜋ\u170Dᜋᜒᜅ᜔ ᜇᜋᜒᜆ᜔᜔᜔",
                "ᜋ\u170Dᜋᜒᜅ᜔ ᜐᜎᜋᜆ᜔",
                "ᜋ\u170Dᜋᜒᜅ᜔ ᜋ\u170Dᜋᜒᜒ᜔",
                "Maraming Salamat",
                "ᜋ\u170Dᜋᜒᜅ᜔ ᜐᜎᜋᜆ᜔"
        ));
        //7
        questionsModels.add(new HardModel(
                "ᜉᜀᜐ",
                "ᜉᜐ᜔ᜃ᜔",
                "ᜉᜀᜎᜋ᜔",
                "Paalam",
                "ᜉᜀᜎᜋ᜔"
        ));
        //8
        questionsModels.add(new HardModel(
                "ᜋᜑᜎ᜔ ᜃᜒᜆ",
                "ᜋᜑᜎ᜔ ᜃ",
                "ᜋᜑᜎ᜔ ᜆᜌ᜔",
                "Mahal Kita",
                "ᜋᜑᜎ᜔ ᜃᜒᜆ"
        ));
        //9
        questionsModels.add(new HardModel(
                "ᜋᜊᜓᜑᜌ᜔᜔",
                "ᜋᜊᜓᜆᜒ",
                "ᜋᜊᜑ᜔",
                "Mabuhay",
                "ᜋᜊᜓᜑᜌ᜔"
        ));
        //10
        questionsModels.add(new HardModel(
                "ᜀ\u170Dᜏ᜔ ᜅ᜔ ᜉᜐ᜔ᜃ᜔",
                "ᜀ\u170Dᜏ᜔ ᜅ᜔ ᜉᜐ᜔ᜃ᜔",
                "ᜀ\u170Dᜏ᜔ ᜅ᜔ ᜉᜒᜌᜒᜐ᜔ᜆ",
                "Araw ng Pasko",
                "ᜀ\u170Dᜏ᜔ ᜅ᜔ ᜉᜐ᜔ᜃ᜔"
        ));
        //11
        questionsModels.add(new HardModel(
                "ᜋᜎᜒᜄᜌᜅ᜔ ᜃᜀ\u170Dᜏᜈ᜔",
                "ᜋᜎᜒᜄᜌᜅ᜔ ᜆoᜈ᜔",
                "ᜋᜎᜒᜄᜌᜅ᜔ ᜀ\u170Dᜏ᜔",
                "Maligayang Kaarawan",
                "ᜋᜎᜒᜄᜌᜅ᜔ ᜃᜀ\u170Dᜏᜈ᜔"
        ));
        //12
        questionsModels.add(new HardModel(
                "ᜃᜎᜓᜉᜒᜆᜈ᜔",
                "ᜃᜐᜒᜌᜑᜈ᜔",
                "ᜃᜆᜒᜉᜓᜈᜈ᜔",
                "Katipunan",
                "ᜃᜆᜒᜉᜓᜈᜈ᜔"
        ));
        //13
        questionsModels.add(new HardModel(
                "ᜊᜌᜈᜒ",
                "ᜊᜌᜈ᜔",
                "ᜊᜑᜌ᜔",
                "Bayan",
                "ᜊᜌᜈ᜔"
        ));
        //14
        questionsModels.add(new HardModel(
                "ᜃᜐᜌ᜔ᜐᜌᜈ᜔",
                "ᜃᜎᜒᜄᜌᜑᜈ᜔",
                "ᜃᜐᜄᜈᜑᜈ᜔",
                "Kasaysayan",
                "ᜃᜐᜌ᜔ᜐᜌᜈ᜔"
        ));
        //15
        questionsModels.add(new HardModel(
                "ᜉᜅᜎᜈ᜔",
                "ᜉᜎᜌᜏ᜔",
                "ᜉᜎᜌᜈ᜔",
                "Pangalan",
                "ᜉᜅᜎᜈ᜔"
        ));
    }

    @SuppressLint("SetTextI18n")
    private void showNextQuestion() {
        if(currentNoOfTest < totalNoOfTest) {
            currentQuestion = questionsModels.get(currentNoOfTest);

            _option_1.setText(currentQuestion.getOption1());
            _option_2.setText(currentQuestion.getOption2());
            _option_3.setText(currentQuestion.getOption3());
            _symbol.setText(currentQuestion.getQuestion());
            answer = currentQuestion.getAnswer();

            currentNoOfTest++;
            questionCount.setText("Mga tanong: " + currentNoOfTest + "/" + totalNoOfTest);
            isAnswered = false;
        } else {
            countDownTimer.cancel();
            showCompleteDialog();
        }
    }

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    private void showCompleteDialog() {
        View testCompleteDialog = LayoutInflater.from(HardLevel.this).inflate(R.layout.test_complete_dialog, null);
        AlertDialog.Builder testCompleteDialogBuilder = new AlertDialog.Builder(HardLevel.this);

        testCompleteDialogBuilder.setView(testCompleteDialog);

        _ok_btn = testCompleteDialog.findViewById(R.id.ok_btn);
        total_score = testCompleteDialog.findViewById(R.id.total_score);
        score_text = testCompleteDialog.findViewById(R.id.score_text);
        anim = testCompleteDialog.findViewById(R.id.anim);

        final AlertDialog testCompletedDialog = testCompleteDialogBuilder.create();

        Objects.requireNonNull(testCompletedDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        testCompletedDialog.setCanceledOnTouchOutside(false);

        total_score.setText(String.valueOf(score));

        if(score <= 3) {
            score_text.setText("Oh hindi! ikaw ay may pinakamababang puntos!");
            anim.setAnimation(R.raw.sadface);
            anim.playAnimation();
        }else {
            score_text.setText("Mayroon kang Pinakamataas na puntos. Magaling ka!");
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

    private void _init() {
        _timerCount = findViewById(R.id.timerCount);
        _symbol = findViewById(R.id.symbol);
        _option_1 = findViewById(R.id.option_1);
        _option_2 = findViewById(R.id.option_2);
        _option_3 = findViewById(R.id.option_3);
        questionCount = findViewById(R.id.questionCount);
        submitBtn = findViewById(R.id.submitBtn);
        score_tv = findViewById(R.id.score);
    }
}