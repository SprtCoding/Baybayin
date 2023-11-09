package com.sprtcoding.baybayin.Model;

public class OptionsModel {
    String selectedAnswer;

    public OptionsModel() {
    }

    public OptionsModel(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

}
