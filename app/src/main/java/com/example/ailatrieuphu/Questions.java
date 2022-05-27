package com.example.ailatrieuphu;

import java.util.List;

public class Questions {
    private String content;
    private int number;
    private List<Answer> lstAnswer;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Answer> getLstAnswer() {
        return lstAnswer;
    }

    public void setLstAnswer(List<Answer> lstAnswer) {
        this.lstAnswer = lstAnswer;
    }

    public Questions(String content, int number, List<Answer> lstAnswer) {
        this.content = content;
        this.number = number;
        this.lstAnswer = lstAnswer;
    }
}
