package com.lbbw.scenari;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

    Context context;
    LayoutInflater inflater;
    ImageLoader imageLoader;
    private List<RecentData> recentlist = null;
    private ArrayList<RecentData> arraylist;

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
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        //return null;

        if (v == null) {
            v = View.inflate(getContext(), R.layout.recentfragment_item, null);
        }
/*
        ParseUser parseObject = Pa.create("Activity");
        parseObject = object.getParseObject("postCreator");
        String username = parseObject.getString("username");
*/
        ParseImageView profileImage = (ParseImageView)v.findViewById(R.id.imageView2);
        ParseFile imageFile = object.getParseObject("postCreator").getParseFile("profile_pic");
        if (imageFile != null){
            profileImage.setParseFile(imageFile);
            profileImage.loadInBackground();
        }




        TextView usernameTextView = (TextView) v.findViewById(R.id.usernameTextView);
        if (usernameTextView != null) {
            usernameTextView.setText(object.getParseObject("postCreator").getString("username").toString());
        }




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





        super.getItemView(object, v, parent);

        return v;
    }


}

