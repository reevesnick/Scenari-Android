package com.lbbw.scenari;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
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

        ParseObject object = null;

        usernameLabel = (TextView)findViewById(R.id.usernameTextView);
        postNum = (TextView)findViewById(R.id.totalPostNumber);
        votesNum = (TextView)findViewById(R.id.totalVotesNumber);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
            }
        });

        ParseImageView profileImage = (ParseImageView)findViewById(R.id.imageView);
        ParseFile imageFile = ParseUser.getCurrentUser().getParseFile("profile_pic");
        if (imageFile != null){
            profileImage.setParseFile(imageFile);
            profileImage.loadInBackground();
        }
        else{
            //profileImage.setImageDrawable(R.drawable.placeholder);
        }

        TextView questionTextView = (TextView) findViewById(R.id.usernameTextView);
        questionTextView.setText("@" +ParseUser.getCurrentUser().getUsername().toString());

        TextView postCount = (TextView)findViewById(R.id.totalPostNumber);
        postCount.setText(ParseUser.getCurrentUser().getNumber("posts").toString());

        TextView votesCount = (TextView)findViewById(R.id.totalVotesNumber);
        votesCount.setText(ParseUser.getCurrentUser().getNumber("totalVotes").toString());

        //username = ParseUser.getCurrentUser().getString("username");
        //postInt = ParseUser.getCurrentUser().getNumber("post");



    }


}
