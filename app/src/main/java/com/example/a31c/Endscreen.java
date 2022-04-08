package com.example.a31c;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Endscreen extends AppCompatActivity {
    Button buttonFinish, buttonAgain;
    TextView congratsText, scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endscreen);
        congratsText = findViewById(R.id.congratsText);
        scoreText = findViewById(R.id.scoreText);

        //takes values from questions activity and changes text to endscreen info
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int correct = extras.getInt("results");
            String name = extras.getString("name");
            congratsText.setText("Congratulations " + name);
            scoreText.setText("YOUR SCORE:\n" + correct + "/5");
        }
        else{
            congratsText.setText("Congratulations ");
            scoreText.setText("YOUR SCORE:\nis unavailable due to an error");
        }

        buttonFinish = findViewById(R.id.buttonFinish);
        buttonAgain = findViewById(R.id.buttonAgain);

        //re-plays questions
        Intent resultIntent = new Intent();
        buttonAgain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View screen){
                int result = 5;
                resultIntent.putExtra("result", result);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        //ends app
        buttonFinish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View screen){
                int result = -5;
                resultIntent.putExtra("result", result);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

}