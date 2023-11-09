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
import com.sprtcoding.baybayin.Model.ModerateModel;
import com.sprtcoding.baybayin.Model.OptionsModel;
import com.sprtcoding.baybayin.Model.QuestionsModel;
import com.sprtcoding.baybayin.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ModerateLevel extends AppCompatActivity {
    private TextView questionCount, _timerCount, _symbol, _option_1, _option_2, _option_3, _option_4, _option_5
            , _option_6, _option_7, _option_8, _option_9, _option_10, _option_11, _option_12
            , _option_13, _option_14, _option_15, _option_16
            , lastBtnClicked = null, score_tv, total_score, score_text;
    private MaterialButton _yes_btn, _no_btn, submitBtn, _next_btn, _ok_btn;
    private LottieAnimationView anim;
    private int currentNoOfTest, totalNoOfTest, score = 0;
    private String answer = "";
    CountDownTimer countDownTimer;
    private Boolean isAnswered;
    private final List<OptionsModel> options = new ArrayList<>();
    private final List<ModerateModel> questionsModels = new ArrayList<>();
    private ModerateModel currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moderate_level);
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
        long totalTime = 30 * 60 * 1000;

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
        View timerDialog = LayoutInflater.from(ModerateLevel.this).inflate(R.layout.timer_dialog, null);
        AlertDialog.Builder timerDialogBuilder = new AlertDialog.Builder(ModerateLevel.this);

        timerDialogBuilder.setView(timerDialog);

        _yes_btn = timerDialog.findViewById(R.id.yes_btn);
        _no_btn = timerDialog.findViewById(R.id.no_btn);
        final AlertDialog timersDialog = timerDialogBuilder.create();

        timersDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
            Toast.makeText(this, "Please select an answer.", Toast.LENGTH_SHORT).show();
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

    @SuppressLint("MissingInflatedId")
    private void showCorrectDialog() {
        View correctDialog = LayoutInflater.from(ModerateLevel.this).inflate(R.layout.correct_dialog, null);
        AlertDialog.Builder correctDialogBuilder = new AlertDialog.Builder(ModerateLevel.this);

        correctDialogBuilder.setView(correctDialog);

        _next_btn = correctDialog.findViewById(R.id.next_btn);
        final AlertDialog correctsDialog = correctDialogBuilder.create();

        correctsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        correctsDialog.setCanceledOnTouchOutside(false);

        _next_btn.setOnClickListener(view -> {
            correctsDialog.cancel();
            showNextQuestion();
        });

        correctsDialog.show();
    }

    @SuppressLint("MissingInflatedId")
    private void showWrongDialog() {
        View wrongDialog = LayoutInflater.from(ModerateLevel.this).inflate(R.layout.wrong_dialog, null);
        AlertDialog.Builder wrongDialogBuilder = new AlertDialog.Builder(ModerateLevel.this);

        wrongDialogBuilder.setView(wrongDialog);

        _next_btn = wrongDialog.findViewById(R.id.next_btn);
        final AlertDialog wrongsDialog = wrongDialogBuilder.create();

        wrongsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        wrongsDialog.setCanceledOnTouchOutside(false);

        _next_btn.setOnClickListener(view -> {
            wrongsDialog.cancel();
            showNextQuestion();
        });

        wrongsDialog.show();
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

        _option_13.setOnClickListener(view -> {
            String option = _option_13.getText().toString();
            setAnswer(option);
            changeButtonBackground(_option_13);
        });

        _option_14.setOnClickListener(view -> {
            String option = _option_14.getText().toString();
            setAnswer(option);
            changeButtonBackground(_option_14);
        });

        _option_15.setOnClickListener(view -> {
            String option = _option_15.getText().toString();
            setAnswer(option);
            changeButtonBackground(_option_15);
        });

        _option_16.setOnClickListener(view -> {
            String option = _option_16.getText().toString();
            setAnswer(option);
            changeButtonBackground(_option_16);
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
        questionsModels.add(new ModerateModel(
                "ᜉ᜔",
                "ᜀ",
                "ᜁ",
                "ᜂ",
                "\u170D᜔᜔",
                "ᜐ᜔",
                "ᜉ᜔",
                "ᜆ",
                "ᜎ᜔",
                "ᜏ᜔",
                "ᜃ",
                "ᜄ",
                "ᜅ",
                "ᜆ",
                "ᜇ",
                "ᜈ",
                "ᜆ",
                "ᜆ"
        ));
        //2
        questionsModels.add(new ModerateModel(
                "ᜉ᜔",
                "ᜀ",
                "ᜁ",
                "ᜂ",
                "\u170D᜔᜔",
                "ᜐ᜔",
                "ᜉ᜔",
                "ᜆ",
                "ᜎ᜔",
                "ᜏ᜔",
                "ᜃ",
                "ᜄ",
                "ᜅ",
                "ᜆ",
                "ᜇ",
                "ᜈ",
                "ᜀ",
                "ᜀ"
        ));
        //3
        questionsModels.add(new ModerateModel(
                "ᜉ᜔",
                "ᜀ",
                "ᜁ",
                "ᜂ",
                "\u170D᜔᜔",
                "ᜐ᜔",
                "ᜉ᜔",
                "ᜆ",
                "ᜊ᜔",
                "ᜏ᜔",
                "ᜃ",
                "ᜄ",
                "ᜅ",
                "ᜆ",
                "ᜇ",
                "ᜈ",
                "ᜊ᜔",
                "ᜊ᜔"
        ));
        //4
        questionsModels.add(new ModerateModel(
                "ᜉ᜔",
                "ᜀ",
                "ᜁ",
                "ᜂ",
                "\u170D᜔᜔",
                "ᜐ᜔",
                "ᜉ᜔",
                "ᜆ",
                "ᜊ᜔",
                "ᜏ᜔",
                "ᜃ",
                "ᜑ",
                "ᜅ",
                "ᜆ",
                "ᜇ",
                "ᜈ",
                "ᜑ",
                "ᜑ"
        ));
        //5
        questionsModels.add(new ModerateModel(
                "ᜉ᜔",
                "ᜀ",
                "ᜁ",
                "ᜂ",
                "\u170D᜔᜔",
                "ᜐ᜔",
                "ᜉ᜔",
                "ᜆ",
                "ᜊ᜔",
                "ᜏ᜔",
                "ᜃ",
                "ᜑ",
                "ᜅ",
                "ᜆ",
                "ᜇ",
                "ᜈ",
                "ᜃ",
                "ᜃ"
        ));
        //6
        questionsModels.add(new ModerateModel(
                "ᜉ᜔",
                "ᜀ",
                "ᜁ",
                "ᜂ",
                "\u170D᜔᜔",
                "ᜐ᜔",
                "ᜉ᜔",
                "ᜆ",
                "ᜊ᜔",
                "ᜏ᜔",
                "ᜃ",
                "ᜑ",
                "ᜅ",
                "ᜆ",
                "ᜇ",
                "ᜈ",
                "ᜉ᜔",
                "ᜉ᜔"
        ));
        //7
        questionsModels.add(new ModerateModel(
                "ᜉ᜔",
                "ᜀ",
                "ᜁ",
                "ᜂ",
                "\u170D᜔᜔",
                "ᜐ᜔",
                "ᜉ᜔",
                "ᜆ",
                "ᜊ᜔",
                "ᜏ᜔",
                "ᜃ",
                "ᜑ",
                "ᜅ",
                "ᜆ",
                "ᜇ",
                "ᜈ",
                "\u170D᜔",
                "\u170D᜔"
        ));
        //8
        questionsModels.add(new ModerateModel(
                "ᜉ᜔",
                "ᜀ",
                "ᜁ",
                "ᜂ",
                "\u170D᜔᜔",
                "ᜐ᜔",
                "ᜉ᜔",
                "ᜆ",
                "ᜊ᜔",
                "ᜏ᜔",
                "ᜃ",
                "ᜑ",
                "ᜅ",
                "ᜆ",
                "ᜇ",
                "ᜈ",
                "ᜈ",
                "ᜈ"
        ));
        //9
        questionsModels.add(new ModerateModel(
                "ᜉ᜔",
                "ᜀ",
                "ᜁ",
                "ᜂ",
                "\u170D᜔᜔",
                "ᜐ᜔",
                "ᜉ᜔",
                "ᜆ",
                "ᜊ᜔",
                "ᜏ᜔",
                "ᜃ",
                "ᜑ",
                "ᜅ",
                "ᜆ",
                "ᜇ",
                "ᜈ",
                "ᜉ᜔",
                "ᜉ᜔"
        ));
        //10
        questionsModels.add(new ModerateModel(
                "ᜉ᜔",
                "ᜀ",
                "ᜁ",
                "ᜂ",
                "\u170D᜔᜔",
                "ᜐ᜔",
                "ᜉ᜔",
                "ᜆ",
                "ᜊ᜔",
                "ᜏ᜔",
                "ᜃ",
                "ᜑ",
                "ᜅ",
                "ᜆ",
                "ᜇ",
                "ᜈ",
                "ᜐ᜔",
                "ᜐ᜔"
        ));
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
            _option_13.setText(currentQuestion.getOption13());
            _option_14.setText(currentQuestion.getOption14());
            _option_15.setText(currentQuestion.getOption15());
            _option_16.setText(currentQuestion.getOption16());
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

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    private void showCompleteDialog() {
        View testCompleteDialog = LayoutInflater.from(ModerateLevel.this).inflate(R.layout.test_complete_dialog, null);
        AlertDialog.Builder testCompleteDialogBuilder = new AlertDialog.Builder(ModerateLevel.this);

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
            score_text.setText("Oh no! you have a lowest score!");
            anim.setAnimation(R.raw.sadface);
            anim.playAnimation();
        }else {
            score_text.setText("You have a Highest Score. You are great!");
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
        _option_4 = findViewById(R.id.option_4);
        _option_5 = findViewById(R.id.option_5);
        _option_6 = findViewById(R.id.option_6);
        _option_7 = findViewById(R.id.option_7);
        _option_8 = findViewById(R.id.option_8);
        _option_9 = findViewById(R.id.option_9);
        _option_10 = findViewById(R.id.option_10);
        _option_11 = findViewById(R.id.option_11);
        _option_12 = findViewById(R.id.option_12);
        _option_13 = findViewById(R.id.option_13);
        _option_14 = findViewById(R.id.option_14);
        _option_15 = findViewById(R.id.option_15);
        _option_16 = findViewById(R.id.option_16);
        questionCount = findViewById(R.id.questionCount);
        submitBtn = findViewById(R.id.submitBtn);
        score_tv = findViewById(R.id.score);
    }
}