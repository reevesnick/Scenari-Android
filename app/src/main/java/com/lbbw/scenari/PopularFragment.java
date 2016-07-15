package com.lbbw.scenari;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

/**
 * Created by neegbeahreeves on 6/22/16.
 */
public class PopularFragment extends ListFragment {
    private ParseQueryAdapter<ParseObject> recentAdapter;
    private PopularListViewAdapter popularListViewAdapter;
    private ListView listView;
    private SwipeRefreshLayout mySwipeRefreshLayrout;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.homefragment_main, container, false);
        popularListViewAdapter = new PopularListViewAdapter(this.getActivity());
        setListAdapter(popularListViewAdapter);
        // listView.setAdapter(recentListViewAdapter);
        popularListViewAdapter.loadObjects();
        return rootView;



    }




    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }

/*
    public class RecentListViewAdapter extends ParseQueryAdapter<QuestionData> {

        public RecentListViewAdapter(Context context) {
            super(context, new ParseQueryAdapter.QueryFactory<QuestionData>() {
                public ParseQuery create() {
                    ParseQuery query = new ParseQuery<QuestionData>("Questions");
                    query.orderByAscending("createdAt");
                    return query;
                }

            });

        }




        @Override
        public View getItemView(QuestionData object, View v, ViewGroup parent) {
            //return null;

            if (v == null) {
                v = View.inflate(getContext(), R.layout.homefragment_item, null);
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
