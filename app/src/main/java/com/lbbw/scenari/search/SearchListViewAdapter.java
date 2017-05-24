package com.lbbw.scenari.search;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lbbw.scenari.R;
import com.lbbw.scenari.apidata.QuestionData;
import com.lbbw.scenari.customui.Circle_ImageView;
import com.lbbw.scenari.util.ImageLoader;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by neegbeahreeves on 7/14/16.
 */
public class SearchListViewAdapter extends ParseQueryAdapter {
    private Context context;
    LayoutInflater inflater;
    ImageLoader imageLoader;
    private List<QuestionData> recentlist = null;
    private ArrayList<QuestionData> arraylist;


    private String filteredList;
    private ArrayList<ParseObject> userArrayList;


    private Button buttonA;
    private Button buttonB;
    private Button shareButton;
    private Button reportButton;


    public SearchListViewAdapter(Context context, SearchListViewAdapter profileActivity) {
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            // Get the intent, verify the action and get the query

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
            v = View.inflate(getContext(), R.layout.search_list, null);
        }

        Circle_ImageView profileImage = (Circle_ImageView) v.findViewById(R.id.imageView2);
        ParseFile imageFile = object.getParseObject("postCreator").getParseFile("profile_pic");
        if (imageFile != null) {
            profileImage.setParseFile(imageFile);
            profileImage.loadInBackground();
        }


        TextView usernameTextView = (TextView) v.findViewById(R.id.usernametextView);
        usernameTextView.setText("@" + object.getParseObject("postCreator").getString("username").toString());


        TextView dateTextView = (TextView) v.findViewById(R.id.dateView);
        dateTextView.setText(object.getCreatedAt().toString());

        TextView questionTextView = (TextView) v.findViewById(R.id.questiontextView);
        questionTextView.setText(object.getString("question"));

        TextView answerAText = (TextView) v.findViewById(R.id.answerAtextView);
        answerAText.setText(object.getString("answer_a"));

        TextView answerBText = (TextView) v.findViewById(R.id.answerBtextView);
        answerBText.setText(object.getString("answer_b"));

        TextView answerAScoreText = (TextView) v.findViewById(R.id.AScoretextView);
        answerAScoreText.setText("A: " + object.getNumber("answer_a_total").toString() + " votes");

        TextView answerBScoreText = (TextView) v.findViewById(R.id.BScoretextView);
        answerBScoreText.setText("B: " + object.getNumber("answer_b_total").toString() + " votes");


        buttonA = (Button) v.findViewById(R.id.buttonA);
        buttonB = (Button) v.findViewById(R.id.buttonB);
        shareButton = (Button) v.findViewById(R.id.button3);
        reportButton = (Button) v.findViewById(R.id.reportbutton);

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
                sendIntent.putExtra(Intent.EXTRA_TEXT, object.getString("question") + " Answer on Scenari for iOS and Android");
                sendIntent.setType("text/plain");
                v.getContext().startActivity(sendIntent);
            }
        });

        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "mailto:scenarireport@brownboxworks.atlassian.net?subject=Report Post: Object #: " + object.getObjectId() + "&body=Post Review Question: " + object.getString("question");
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                v.getContext().startActivity(i);
            }
        });


        super.getItemView(object, v, parent);

        return v;
    }


//    @Override
//    public Filter getFilter() {
//        Filter filter = new Filter() {
//            @SuppressWarnings("unchecked")
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                // Write your logic for PUBLISHING RESULTS and notify your dataset for change
//
//
//                notifyDataSetChanged();
//            }
//
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                // Write your logic here to PERFORM FILTERING and return filtered result
//                FilterResults results = new FilterResults();
//                ArrayList<String> FilteredArrayNames = new ArrayList<String>();
//
//                // perform your search here using the searchConstraint String.
//
//                constraint = constraint.toString().toLowerCase();
///*                for (int i = 0; i < mDatabaseOfNames.size(); i++) {
//                    String dataNames = mDatabaseOfNames.get(i);
//                    if (dataNames.toLowerCase().startsWith(constraint.toString()))  {
//                        FilteredArrayNames.add(dataNames);
//                    }
//                }*/
//
//                results.count = FilteredArrayNames.size();
//                results.values = FilteredArrayNames;
//                Log.e("VALUES", results.values.toString());
//
//                return results;
//
//            }
//        };
//        return filter;
//    }


}
