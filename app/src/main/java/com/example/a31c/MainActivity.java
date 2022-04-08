package com.example.a31c;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    EditText editText;
    View screen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        editText = findViewById(R.id.editText);

        //on button press..
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View screen){
                startActivityQuestions();
            }
        });
    }

    //starts quiz. if the user has input a name, it will be passed. if the user doesn't input a name,
    //the app is written in such a way that it will function okay
    public void startActivityQuestions(){
        String name = new String(String.valueOf(editText.getText()));
        Intent questionIntent = new Intent(this, Questions.class);
        questionIntent.putExtra("key",name);
        startActivity(questionIntent);
        finish();
    }
}