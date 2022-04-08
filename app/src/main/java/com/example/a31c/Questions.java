package com.example.a31c;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ProgressBar;
import android.widget.TextView;


public class Questions extends AppCompatActivity {

    Button submitButton, ans0Button, ans1Button, ans2Button; //components
    TextView progressText, welcomeText;
    ProgressBar progressBar;
    int questionsCount = 0; //current question #
    int answered = 0; //integer that is changed mod2, should only ever be 0 or 1.   0 is for when user is choosing options, 1 is for when they see the result
    int selected = 0; //marks the selected answer option from getId(), for ans0,1,2Buttons
    int correct = 0; //counts the number of correct answers to pass to the endscreen
    String name = ""; //default blank value for user's name - should be overwritten if the user inputs any

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        //declaring components
        submitButton = findViewById(R.id.submitButton);
        ans0Button = findViewById(R.id.ans0Button);
        ans1Button = findViewById(R.id.ans1Button);
        ans2Button = findViewById(R.id.ans2Button);
        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progressText);
        welcomeText = findViewById(R.id.welcomeText);

        //array to determine what is a correct answer
        int[] correctOption = {ans0Button.getId(), ans2Button.getId(),
                ans2Button.getId(), ans0Button.getId(), ans1Button.getId(), };

        //sets 0/5 next to progress bar
        progressText.setText(questionsCount + "/" + correctOption.length);

        //attempts to get name from main - outputs welcome message
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("key");
            welcomeText.setText("Welcome " + name);
        }
        else{
            welcomeText.setText("Welcome ");
        }

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View screen){
                //on click to reveal answer
                progressText.setText(questionsCount + "/" + correctOption.length); //updates progress #/5
                welcomeText.setText(""); //removes welcome message after first question answer

                //if clicked while the user is in the answer choosing stage
                if(answered == 0)
                {
                    submitButton.setText("Next");

                    //outcomes for option 1 selected
                    if(selected == ans0Button.getId())
                    {
                        if(correctOption[questionsCount] == ans0Button.getId())
                        {
                            ans0Button.setBackgroundColor(0xFF008000);
                            correct++;

                        }
                        else{
                            ans0Button.setBackgroundColor(0xFFFF0000);
                        }
                    }
                    //outcomes for option 2 selected
                    else if(selected == ans1Button.getId())
                    {
                        if(correctOption[questionsCount] == ans1Button.getId())
                        {
                            ans1Button.setBackgroundColor(0xFF008000);
                            correct++;
                        }
                        else{
                            ans1Button.setBackgroundColor(0xFFFF0000);
                        }
                    }
                    //outcomes for option 3 selected
                    else if(selected == ans2Button.getId())
                    {
                        if(correctOption[questionsCount] == ans2Button.getId())
                        {
                            ans2Button.setBackgroundColor(0xFF008000);
                            correct++;
                        }
                        else{
                            ans2Button.setBackgroundColor(0xFFFF0000);
                        }
                    }

                    //no option selected..., also displays colours

                    if(correctOption[questionsCount] == ans0Button.getId()) {
                        ans0Button.setBackgroundColor(0xFF008000);
                    }
                    else if(correctOption[questionsCount] == ans1Button.getId()) {
                        ans1Button.setBackgroundColor(0xFF008000);
                    }
                    else{
                        ans2Button.setBackgroundColor(0xFF008000);
                    }
                    //updates answered condition, questions count, and progress bar
                    answered = (answered + 1)%2;
                    questionsCount ++;
                    progressBar.setProgress(questionsCount*20);
                }
                //if pressed when the screen has revealed answer
                else
                {
                    //resets colours and the condition of the selected options
                    selected = 0;
                    submitButton.setText("Submit");
                    ans0Button.setBackgroundColor(0xffffffff);
                    ans1Button.setBackgroundColor(0xffffffff);
                    ans2Button.setBackgroundColor(0xffffffff);

                    //if all questions have been answered, changes to the endscreen
                    //passes the values for the name and the number of correct answers as a bundle
                    if(questionsCount == (correctOption.length))
                    {
                        Bundle endscreenExtras = new Bundle();
                        endscreenExtras.putString("name", name);
                        endscreenExtras.putInt("results", correct);

                        Intent endscreen = new Intent(Questions.this, Endscreen.class);
                        endscreen.putExtras(endscreenExtras);
                        startActivityForResult(endscreen, 1);
                    }
                    //resets answered condition
                    answered = (answered + 1)%2;
                }

            }
        });

        //highlights the buttons and changes the selected answers
        ans0Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View screen) {
                if(answered == 0){
                    ans0Button.setBackgroundColor(0xFF808080);
                    ans1Button.setBackgroundColor(0xffffffff);
                    ans2Button.setBackgroundColor(0xffffffff);
                    selected = ans0Button.getId();
                }
            }
        });

        ans1Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View screen) {
                if(answered == 0){
                    ans0Button.setBackgroundColor(0xffffffff);
                    ans1Button.setBackgroundColor(0xFF808080);
                    ans2Button.setBackgroundColor(0xffffffff);
                    selected = ans1Button.getId();
                }
            }
        });

        ans2Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View screen) {
                if(answered == 0){
                    ans0Button.setBackgroundColor(0xffffffff);
                    ans1Button.setBackgroundColor(0xffffffff);
                    ans2Button.setBackgroundColor(0xFF808080);
                    selected = ans2Button.getId();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        //takes result from endscreen and either ends the app or re-runs questions
        if(requestCode == 1 && resultCode == RESULT_OK){
            int result = data.getIntExtra("result", 0);
            System.out.printf(Integer.toString(result));
            if(result == 5){
                Intent questionsAgain = getIntent();
                startActivity(questionsAgain);
            }
            finish();
        }
    }
}