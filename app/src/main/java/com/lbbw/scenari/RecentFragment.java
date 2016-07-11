package com.lbbw.scenari;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by neegbeahreeves on 6/22/16.
 */
public class RecentFragment extends ListFragment {
    private ParseQueryAdapter<ParseObject> recentAdapter;
    private RecentListViewAdapter recentListViewAdapter;
    private ListView listView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.recentfragment_main, container, false);
        recentListViewAdapter = new RecentListViewAdapter(this.getActivity());
        setListAdapter(recentListViewAdapter);
       // listView.setAdapter(recentListViewAdapter);
        recentListViewAdapter.loadObjects();
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }

/*
    public class RecentListViewAdapter extends ParseQueryAdapter<RecentData> {

        public RecentListViewAdapter(Context context) {
            super(context, new ParseQueryAdapter.QueryFactory<RecentData>() {
                public ParseQuery create() {
                    ParseQuery query = new ParseQuery<RecentData>("Questions");
                    query.orderByAscending("createdAt");
                    return query;
                }

            });

        }




        @Override
        public View getItemView(RecentData object, View v, ViewGroup parent) {
            //return null;

            if (v == null) {
                v = View.inflate(getContext(), R.layout.recentfragment_item, null);
            }

            super.getItemView(object, v, parent);


            TextView questionText = (TextView) v.findViewById(R.id.questiontextView);
            questionText.setText(object.getQuestion());

            TextView answerAText = (TextView) v.findViewById(R.id.answerAtextView);
            answerAText.setText(object.getAnswerA());

            TextView answerBText = (TextView) v.findViewById(R.id.answerBtextView);
            answerBText.setText(object.getAnswerB());


            return v;
        }

    }

*/
}
