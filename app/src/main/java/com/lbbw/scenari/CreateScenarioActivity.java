package com.lbbw.scenari;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by neegbeahreeves on 6/22/16.
 */
public class CreateScenarioActivity extends AppCompatActivity {
    private EditText questionText;
    private EditText answerAText;
    private EditText answerBText;
    private Button submitButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createscenario_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        questionText = (EditText)findViewById(R.id.questionsText);
        answerAText = (EditText)findViewById(R.id.questionA);
        answerBText = (EditText)findViewById(R.id.questionB);
        submitButton = (Button)findViewById(R.id.postButton);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String question = questionText.getText().toString();
                String answerA = answerAText.getText().toString();
                String answerB = answerBText.getText().toString();



                ParseObject dataObject = new ParseObject("Questions");
                dataObject.put("question", question);
                dataObject.put("answer_a", "A: " +answerA);
                dataObject.put("answer_b", "B: "+answerB);
                dataObject.put("postCreator", ParseUser.getCurrentUser());
                dataObject.put("answer_a_total", 0);
                dataObject.put("answer_b_total", 0);
                dataObject.saveInBackground();

                Toast.makeText(CreateScenarioActivity.this, "Scenario created",
                        Toast.LENGTH_SHORT).show();

                Intent profile = new Intent(CreateScenarioActivity.this, MainActivity.class);
                startActivity(profile);
            }

        });

    }
}
