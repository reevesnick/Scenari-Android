package com.lbbw.scenari;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lbbw.scenari.util.ImageLoader;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neegbeahreeves on 7/14/16.
 */
public class ProfileListViewAdapter extends ParseQueryAdapter {
    Context context;
    LayoutInflater inflater;
    ImageLoader imageLoader;
    private List<QuestionData> recentlist = null;
    private ArrayList<QuestionData> arraylist;
    private Button deleteButton;

    private Button buttonA;
    private Button buttonB;
    private Button shareButton;

    public ProfileListViewAdapter(Context context, ProfileListViewAdapter profileActivity) {
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("Questions");
                query.include("postCreator");
                query.whereEqualTo("postCreator", ParseUser.getCurrentUser());
                query.orderByDescending("createdAt");
                return query;
            }

        });

    }




    @Override
    public View getItemView(final ParseObject object, View v, ViewGroup parent) {
        //return null;

        if (v == null) {
            v = View.inflate(getContext(), R.layout.profilelistactivity, null);
        }
/*
        ParseUser parseObject = Pa.create("Activity");
        parseObject = object.getParseObject("postCreator");
        String username = parseObject.getString("username");
*/
        /*
        ParseImageView profileImage = (ParseImageView)v.findViewById(R.id.imageView2);
        ParseFile imageFile = object.getParseObject("postCreator").getParseFile("profile_pic");
        if (imageFile != null){
            profileImage.setParseFile(imageFile);
            profileImage.loadInBackground();
        }



        // final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

/*
        TextView usernameTextView = (TextView) v.findViewById(R.id.usernametextView);
        usernameTextView.setText("@"+object.getParseObject("postCreator").getString("username").toString());


        TextView dateTextView = (TextView)v.findViewById(R.id.dateView);
        dateTextView.setText(object.getCreatedAt().toString());
*/
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


        deleteButton = (Button)v.findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.deleteInBackground();
            }
        });

    shareButton = (Button)v.findViewById(R.id.button3);
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
