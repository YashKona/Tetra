package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class Questionspage extends AppCompatActivity {
//getting information from list, setting the strings
    private List<QuestionModel> questionsList;
    private QuestionModel currentQuestion;
    private TextView tvQuestion, tvScore, tvQuestionNo, tvTimer;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3;
    private Button btnNext;

    boolean answered;
    CountDownTimer countDownTimer;
    ColorStateList dfRbColor;
    int totalQuestions;
    int qCounter = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getting the ids ang giving variable
        setContentView(R.layout.activity_questionpage);
        questionsList = new ArrayList<>();
        tvQuestion = findViewById(R.id.textQuestion);
        tvScore  = findViewById(R.id.textScore);
        tvQuestionNo = findViewById(R.id.textQuestionNo);
        tvTimer = findViewById(R.id.textTimer);
        radioGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3  = findViewById(R.id.rb3);
        btnNext = findViewById(R.id.btnNext);
        dfRbColor = rb1.getTextColors();

        addQuestions();
        totalQuestions = questionsList.size();
        showNextQuestion();
        //on click listener is where when clicked the following fuctions run
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if answer is not false
                if (answered == false){
                    // select radio buttons and stop timer
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()){
                        checkAnswer();
                        countDownTimer.cancel();
                    //otherwise make a new error using toast
                    }else {
                        Toast.makeText(Questionspage.this, "Please Select an option", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    //start shownextquestion
                    showNextQuestion();
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        finish();
    }
    private void checkAnswer() {
        //if answer is true
        answered = true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo = radioGroup.indexOfChild(rbSelected) + 1;
        if (answerNo == currentQuestion.getCorrectAnsNo()){
            //add one to score
            score++;
            // display score
            tvScore.setText("Score: "+score);
        }
        //set color red if answer is correct set it green
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        switch (currentQuestion.getCorrectAnsNo()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                break;
        }
        if (qCounter < totalQuestions){
            btnNext.setText("Next");

        }else {
            btnNext.setText("Finish");
        }
    }
    private void showNextQuestion() {
        //clear radio button
        radioGroup.clearCheck();
        rb1.setTextColor(dfRbColor);
        rb2.setTextColor(dfRbColor);
        rb3.setTextColor(dfRbColor);
        if (qCounter < totalQuestions){
            timer();
            currentQuestion = questionsList.get(qCounter);
            tvQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            //add score
            qCounter++;
            btnNext.setText("Submit");
            //show question
            tvQuestionNo.setText("Question: "+qCounter+"/"+totalQuestions);
            answered = false;
        }else {
            finish();
        }
    }
    //timer
    private void timer() {
        //countdown, how long, how fast
        countDownTimer = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("Time: 00:"+ millisUntilFinished/1000);
            }
            @Override
            public void onFinish() {
                //when timer runs out then show next question
                showNextQuestion();
            }
        }.start();
    }

    private void addQuestions() {
        //questions, shows the question, options and the correct answer represented by the number
        questionsList.add(new QuestionModel("Which functional group has OH","Alcohol","Alkane","Aldehyde",1));
        questionsList.add(new QuestionModel("How many carbons does Butane have?","7","2","4",3));
        questionsList.add(new QuestionModel("What ions are acids made of?","Hydroxyl","Hydrogen","Hydroxy",2));
        questionsList.add(new QuestionModel("Which of these is a noble gas?","Argon","Nitrogen","Oxygen",1));
        questionsList.add(new QuestionModel("What is the highest pH?","7","20","14",3));
    }
    public void homepage (View view){
        //open homepage
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
        finish();
    }





}