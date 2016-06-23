package com.lbbw.scenari;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

/**
 * Created by neegbeahreeves on 6/22/16.
 */
public class CreateScenarioActivity extends AppCompatActivity {
    private EditText questionText;
    private EditText answerAText;
    private EditText answerBText;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createscenario_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        questionText = (EditText)findViewById(R.id.questionsText);
        answerAText = (EditText)findViewById(R.id.questionA);
        answerBText = (EditText)findViewById(R.id.questionB);


    }
}
