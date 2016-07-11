package com.lbbw.scenari;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

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
                query.orderByAscending("createdAt");
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

        TextView usernameTextView = (TextView) v.findViewById(R.id.usernameTextView);
        //usernameTextView.setText(object.getString("username"));

        TextView questionTextView = (TextView) v.findViewById(R.id.questiontextView);
        questionTextView.setText(object.getString("question"));

        TextView answerAText = (TextView) v.findViewById(R.id.answerAtextView);
        answerAText.setText(object.getString("answer_a"));

        TextView answerBText = (TextView) v.findViewById(R.id.answerBtextView);
        answerBText.setText(object.getString("answer_b"));
/*
        TextView answerAScoreText = (TextView)v.findViewById(R.id.AScoretextView);
       answerAScoreText.setText(object.getInt("answer_a_total"));

        TextView answerBScoreText = (TextView)v.findViewById(R.id.BScoretextView);
       answerBScoreText.setText(object.getInt("answer_b_total"));
*/





        super.getItemView(object, v, parent);

        return v;
    }
}

