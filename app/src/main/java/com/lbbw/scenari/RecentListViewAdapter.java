package com.lbbw.scenari;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neegbeahreeves on 6/25/16.
 */
public class RecentListViewAdapter extends ParseQueryAdapter {

    LayoutInflater inflater;
    ImageLoader imageLoader;
    private List<QuestionData> recentlist = null;
    private ArrayList<QuestionData> arraylist;
    private ShareActionProvider mShareActionProvider;



    private Button buttonA;
    private Button buttonB;
    private Button shareButton;

    public RecentListViewAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("Questions");
                query.include("postCreator");
                query.orderByDescending("createdAt");
                return query;
            }

        });


    }





    @Override
    public View getItemView(final ParseObject object, View v, ViewGroup parent) {
        //return null;

        if (v == null) {
            v = View.inflate(getContext(), R.layout.homefragment_item, null);
        }
/*
        ParseUser parseObject = Pa.create("Activity");
        parseObject = object.getParseObject("postCreator");
        String username = parseObject.getString("username");
*/
        Circle_ImageView profileImage = (Circle_ImageView) v.findViewById(R.id.imageView2);
        ParseFile imageFile = object.getParseObject("postCreator").getParseFile("profile_pic");
        if (imageFile != null){
            profileImage.setParseFile(imageFile);
            profileImage.loadInBackground();
        }



       // final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);


        TextView usernameTextView = (TextView) v.findViewById(R.id.usernametextView);
        usernameTextView.setText("@"+object.getParseObject("postCreator").getString("username").toString());


        TextView dateTextView = (TextView)v.findViewById(R.id.dateView);
        dateTextView.setText(object.getCreatedAt().toString());

        TextView questionTextView = (TextView) v.findViewById(R.id.questiontextView);
        questionTextView.setText(object.getString("question"));

        TextView answerAText = (TextView) v.findViewById(R.id.answerAtextView);
        answerAText.setText(object.getString("answer_a"));

        TextView answerBText = (TextView) v.findViewById(R.id.answerBtextView);
        answerBText.setText(object.getString("answer_b"));

        TextView answerAScoreText = (TextView)v.findViewById(R.id.AScoretextView);
        answerAScoreText.setText("A: "+object.getNumber("answer_a_total").toString()+" votes");

        TextView answerBScoreText = (TextView)v.findViewById(R.id.BScoretextView);
        answerBScoreText.setText("B: "+object.getNumber("answer_b_total").toString()+" votes");





        buttonA = (Button)v.findViewById(R.id.buttonA);
        buttonB = (Button)v.findViewById(R.id.buttonB);
        shareButton = (Button)v.findViewById(R.id.button);
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = ParseUser.getCurrentUser();
                object.addUnique("answerVoted", user.getObjectId());
                object.increment("answer_a_total");
                object.saveInBackground();

                Toast.makeText(getContext(), "Answer Submitted",
                        Toast.LENGTH_SHORT).show();
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = ParseUser.getCurrentUser();
                object.addUnique("answerVoted", user.getObjectId());
                object.increment("answer_b_total");
                object.saveInBackground();

                Toast.makeText(getContext(), "Answer Submitted",
                        Toast.LENGTH_SHORT).show();



            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,object.getString("question")+ " Answer on Scenari for iOS and Android");
                sendIntent.setType("text/plain");
                v.getContext().startActivity(sendIntent);
            }
        });



        super.getItemView(object, v, parent);

        return v;
    }



}

