package com.lbbw.scenari;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseUser;

/**
 * Created by neegbeahreeves on 6/23/16.
 */
public class ProfileActivity extends AppCompatActivity {
    private TextView usernameLabel;
    private TextView postNum;
    private TextView votesNum;

    private ParseFile profilePic;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileactivity_main);

        usernameLabel = (TextView)findViewById(R.id.usernameTextView);
        postNum = (TextView)findViewById(R.id.totalPostNumber);
        votesNum = (TextView)findViewById(R.id.totalVotesNumber);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String username = usernameLabel.getText().toString();
        String postInt = postNum.getText().toString();
        String votesInt = votesNum.getText().toString();

        //username = ParseUser.getCurrentUser().getString("username");
        //postInt = ParseUser.getCurrentUser().getNumber("post");



    }


}
