package com.example.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.ailatrieuphu.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding mainBinding;
    private List<Questions> mLstQuestion;
    private int currentQuestion = 0;
    private Questions mQquestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControl();

        mLstQuestion = getListQuestion();
        if (mLstQuestion == null) {
            return;
        }

        setDataQuestions(mLstQuestion.get(currentQuestion));
    }

    private void setDataQuestions(Questions questions) {
        if (questions == null) {
            return;
        }

        mainBinding.txtAnswer1.setBackgroundResource(R.drawable.bg_blue_corner_30);
        mainBinding.txtAnswer2.setBackgroundResource(R.drawable.bg_blue_corner_30);
        mainBinding.txtAnswer3.setBackgroundResource(R.drawable.bg_blue_corner_30);
        mainBinding.txtAnswer4.setBackgroundResource(R.drawable.bg_blue_corner_30);

        mQquestions = questions;

        String titleQuestion = "Question " + questions.getNumber();

        mainBinding.txtQuestion.setText(titleQuestion);

        mainBinding.txtContentQuestion.setText(questions.getContent());

        mainBinding.txtAnswer1.setText(questions.getLstAnswer().get(0).getContent());
        mainBinding.txtAnswer2.setText(questions.getLstAnswer().get(1).getContent());
        mainBinding.txtAnswer3.setText(questions.getLstAnswer().get(2).getContent());
        mainBinding.txtAnswer4.setText(questions.getLstAnswer().get(3).getContent());

        mainBinding.txtAnswer1.setOnClickListener(this);
        mainBinding.txtAnswer2.setOnClickListener(this);
        mainBinding.txtAnswer3.setOnClickListener(this);
        mainBinding.txtAnswer4.setOnClickListener(this);
    }

    private void addControl() {
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
    }

    private List<Questions> getListQuestion() {
        List<Answer> answerList1 = new ArrayList<>();
        answerList1.add(new Answer("Ga", true));
        answerList1.add(new Answer("Bo", false));
        answerList1.add(new Answer("Ngua", false));
        answerList1.add(new Answer("Trau", false));

        List<Answer> answerList2 = new ArrayList<>();
        answerList2.add(new Answer("Rap", true));
        answerList2.add(new Answer("Cai luong", false));
        answerList2.add(new Answer("Vong co", false));
        answerList2.add(new Answer("Bolero", false));

        List<Answer> answerList3 = new ArrayList<>();
        answerList3.add(new Answer("MTP", true));
        answerList3.add(new Answer("Soobin", false));
        answerList3.add(new Answer("Hoang Yen Chibi", false));
        answerList3.add(new Answer("Chipu", false));

        List<Questions> list = new ArrayList<>();
        list.add(new Questions("Con nao co canh ?", 1, answerList1));
        list.add(new Questions("The loai nhac nao gioi tre yeu thich ?", 2, answerList2));
        list.add(new Questions("Ca si co nhieu fan nhat ?", 3, answerList3));


        return list;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtAnswer1:
                mainBinding.txtAnswer1.setBackgroundResource(R.drawable.bg_orange_corner_30);
                checkAnswer(mainBinding.txtAnswer1, mQquestions, mQquestions.getLstAnswer().get(0));
                break;
            case R.id.txtAnswer2:
                mainBinding.txtAnswer2.setBackgroundResource(R.drawable.bg_orange_corner_30);
                checkAnswer(mainBinding.txtAnswer2, mQquestions, mQquestions.getLstAnswer().get(1));
                break;
            case R.id.txtAnswer3:
                mainBinding.txtAnswer3.setBackgroundResource(R.drawable.bg_orange_corner_30);
                checkAnswer(mainBinding.txtAnswer3, mQquestions, mQquestions.getLstAnswer().get(2));
                break;
            case R.id.txtAnswer4:
                mainBinding.txtAnswer4.setBackgroundResource(R.drawable.bg_orange_corner_30);
                checkAnswer(mainBinding.txtAnswer4, mQquestions, mQquestions.getLstAnswer().get(3));
                break;
        }
    }

    private void checkAnswer(TextView text, Questions questions, Answer answer) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (answer.isCorrect()) {
                    text.setBackgroundResource(R.drawable.bg_green_corner_30);
                    nextQuestion();
                } else {
                    text.setBackgroundResource(R.drawable.bg_red_corner_30);
                    showAnswerCorrect(questions);
                    gameOver();
                }
            }
        }, 1000);
    }

    private void nextQuestion() {
        if(currentQuestion==mLstQuestion.size()-1)
        {
            showDialog("YOU WIN");
        }
        else
        {
            currentQuestion++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setDataQuestions(mLstQuestion.get(currentQuestion));
                }
            }, 1000);

        }
    }

    private void showAnswerCorrect(Questions questions) {
        if (questions == null || questions.getLstAnswer() == null || questions.getLstAnswer().isEmpty()) {
            return;
        }

        if (questions.getLstAnswer().get(0).isCorrect()) {
            mainBinding.txtAnswer1.setBackgroundResource(R.drawable.bg_green_corner_30);
        } else if (questions.getLstAnswer().get(1).isCorrect()) {
            mainBinding.txtAnswer2.setBackgroundResource(R.drawable.bg_green_corner_30);
        } else if (questions.getLstAnswer().get(2).isCorrect()) {
            mainBinding.txtAnswer3.setBackgroundResource(R.drawable.bg_green_corner_30);
        } else if (questions.getLstAnswer().get(3).isCorrect()) {
            mainBinding.txtAnswer4.setBackgroundResource(R.drawable.bg_green_corner_30);
        }

    }

    private void gameOver() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showDialog("Game Over");
            }
        }, 1000);
    }

    private void showDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                currentQuestion = 0;
                setDataQuestions(mLstQuestion.get(currentQuestion));
                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}