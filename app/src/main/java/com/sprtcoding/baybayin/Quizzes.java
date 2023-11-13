package com.sprtcoding.baybayin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.sprtcoding.baybayin.Quiz.Question;
import com.sprtcoding.baybayin.Quiz.QuizDbHelper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Quizzes extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    private ImageButton _backBtn;
    private TextView _timerCountdown, _questionCount, _viewScore, _questions, _successMSG, _failMSG;
    private LottieAnimationView _confitti;
    private RadioGroup _rbGroup;
    private RadioButton _rb1, _rb2, _rb3;
    private MaterialButton _btnSubmitNext, _okBtn, _okBtnFail, _yes_btn, _no_btn;
    private ColorStateList textColorDefaultRb;
    private List<Question> questionList;
    CountDownTimer countDownTimer;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    private int score;
    private boolean isAnswered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes);
        _var();

        startTimer();

        textColorDefaultRb = _rb1.getTextColors();

        QuizDbHelper quizDbHelper = new QuizDbHelper(this);
        questionList = quizDbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        _btnSubmitNext.setOnClickListener(view -> {
            if(!isAnswered) {
                if(_rb1.isChecked() || _rb2.isChecked() || _rb3.isChecked()) {
                    checkAnswer();
                }else {
                    Toast.makeText(this, "Pumili ng sagot!", Toast.LENGTH_SHORT).show();
                }
            }else {
                showNextQuestion();
            }
        });

        _backBtn.setOnClickListener(view -> {
            finish();
            countDownTimer.cancel();
        });
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
                _timerCountdown.setText(time);

                // Check if remaining time is 10 seconds and change text color
                if (remainingTime <= 10000) {
                    _timerCountdown.setTextColor(Color.RED);
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

    @SuppressLint("SetTextI18n")
    private void checkAnswer() {
        isAnswered = true;

        RadioButton rbSelected = findViewById(_rbGroup.getCheckedRadioButtonId());
        int answerNr = _rbGroup.indexOfChild(rbSelected) + 1;

        if(answerNr == currentQuestion.getAnswerNr()) {
            score++;
            _viewScore.setText("Score: " + score);
        }
        showSolution();
    }

    @SuppressLint("SetTextI18n")
    private void showSolution() {
        _rb1.setTextColor(Color.RED);
        _rb2.setTextColor(Color.RED);
        _rb3.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()) {
            case 1:
                _rb1.setTextColor(Color.GREEN);
                _questions.setText("Ang sagot na 1 ay tama");
                break;
            case 2:
                _rb2.setTextColor(Color.GREEN);
                _questions.setText("Ang sagot na 2 ay tama");
                break;
            case 3:
                _rb3.setTextColor(Color.GREEN);
                _questions.setText("Ang sagot na 3 ay tama");
                break;
        }

        if(questionCounter < questionCountTotal) {
            _btnSubmitNext.setText("Sunod");
        }else {
            _btnSubmitNext.setText("Tapos na");
        }
    }

    @SuppressLint("SetTextI18n")
    private void showNextQuestion() {
        _rb1.setTextColor(textColorDefaultRb);
        _rb2.setTextColor(textColorDefaultRb);
        _rb3.setTextColor(textColorDefaultRb);
        _rbGroup.clearCheck();

        if(questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            _questions.setText(currentQuestion.getQuestion());
            _rb1.setText(currentQuestion.getOption1());
            _rb2.setText(currentQuestion.getOption2());
            _rb3.setText(currentQuestion.getOption3());

            questionCounter++;
            _questionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            isAnswered = false;
            _btnSubmitNext.setText("Ipasa");
        } else {
            finishQuiz();
            countDownTimer.cancel();
        }
    }

    @SuppressLint("MissingInflatedId")
    private void showTimerDialog() {
        View timerDialog = LayoutInflater.from(Quizzes.this).inflate(R.layout.timer_dialog, null);
        AlertDialog.Builder timerDialogBuilder = new AlertDialog.Builder(Quizzes.this);

        timerDialogBuilder.setView(timerDialog);

        _yes_btn = timerDialog.findViewById(R.id.yes_btn);
        _no_btn = timerDialog.findViewById(R.id.no_btn);
        final AlertDialog timersDialog = timerDialogBuilder.create();

        Objects.requireNonNull(timersDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timersDialog.setCanceledOnTouchOutside(false);

        _yes_btn.setOnClickListener(view -> {
            timersDialog.cancel();
            showNextQuestion();
            startTimer();
        });

        _no_btn.setOnClickListener(view -> {
            timersDialog.cancel();
            finish();
        });

        timersDialog.show();
    }

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    private void finishQuiz() {
        //pass

        View successAlertDialog = LayoutInflater.from(Quizzes.this).inflate(R.layout.quiz_dialog, null);
        AlertDialog.Builder successAlertDialogBuilder = new AlertDialog.Builder(Quizzes.this);

        successAlertDialogBuilder.setView(successAlertDialog);

        _successMSG = successAlertDialog.findViewById(R.id.successMSG);
        _okBtn = successAlertDialog.findViewById(R.id.okBtnSuccess);
        final AlertDialog successDialog = successAlertDialogBuilder.create();

        Objects.requireNonNull(successDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        successDialog.setCanceledOnTouchOutside(false);

        _okBtn.setOnClickListener(view -> {
            successDialog.cancel();
            _confitti.cancelAnimation();
            _confitti.setVisibility(View.GONE);
            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_SCORE, score);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        //end pass

        //fail

        View failAlertDialog = LayoutInflater.from(Quizzes.this).inflate(R.layout.quiz_dialog_fail, null);
        AlertDialog.Builder failAlertDialogBuilder = new AlertDialog.Builder(Quizzes.this);

        failAlertDialogBuilder.setView(failAlertDialog);

        _failMSG = failAlertDialog.findViewById(R.id.failMSG);
        _okBtnFail = failAlertDialog.findViewById(R.id.okBtnFail);
        final AlertDialog failDialog = failAlertDialogBuilder.create();

        Objects.requireNonNull(failDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        failDialog.setCanceledOnTouchOutside(false);

        _okBtnFail.setOnClickListener(view -> {
            failDialog.cancel();
            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_SCORE, score);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        //end fail

        if(score <= 5) {
            failDialog.show();
            _failMSG.setText("Nice Try! Maaari mong mapabuti ang iyong puntos anumang oras.");
        } else {
            _confitti.setVisibility(View.VISIBLE);
            _confitti.playAnimation();
            _confitti.loop(true);
            successDialog.show();
            _successMSG.setText("Napakaganda! ang galing mo.");
        }
    }

    private void _var() {
        _timerCountdown = findViewById(R.id.timerCount);
        _questionCount = findViewById(R.id.questionCount);
        _viewScore = findViewById(R.id.score);
        _questions = findViewById(R.id.questions);
        _rbGroup = findViewById(R.id.rdbGroup);
        _rb1 = findViewById(R.id.option1);
        _rb2 = findViewById(R.id.option2);
        _rb3 = findViewById(R.id.option3);
        _btnSubmitNext = findViewById(R.id.submitNextBtn);
        _confitti = findViewById(R.id.confetti);
        _backBtn = findViewById(R.id.backBtn);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        countDownTimer.cancel();
    }
}