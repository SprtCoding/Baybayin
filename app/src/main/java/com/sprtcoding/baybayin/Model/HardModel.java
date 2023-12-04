package com.sprtcoding.baybayin.Model;

public class HardModel {
    String Option1, Option2, Option3,Question, Answer;

    public HardModel() {
    }

    public HardModel(String option1, String option2, String option3, String question, String answer) {
        Option1 = option1;
        Option2 = option2;
        Option3 = option3;
        Question = question;
        Answer = answer;
    }

    public String getOption1() {
        return Option1;
    }

    public void setOption1(String option1) {
        Option1 = option1;
    }

    public String getOption2() {
        return Option2;
    }

    public void setOption2(String option2) {
        Option2 = option2;
    }

    public String getOption3() {
        return Option3;
    }

    public void setOption3(String option3) {
        Option3 = option3;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }
}
