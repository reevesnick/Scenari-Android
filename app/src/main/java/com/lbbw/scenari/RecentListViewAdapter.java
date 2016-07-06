package com.lbbw.scenari;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neegbeahreeves on 6/25/16.
 */
public class RecentListViewAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ImageLoader imageLoader;
    private List<RecentData> recentlist = null;
    private ArrayList<RecentData> arraylist;

    public RecentListViewAdapter(Context context,
                               List<RecentData> recentlist) {
        this.context = context;
        this.recentlist = recentlist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<RecentData>();
        this.arraylist.addAll(recentlist);
        imageLoader = new ImageLoader(context);
    }


    public class ViewHolder {
       // TextView date;
        TextView username;
        TextView question;
        TextView answerA;
        TextView answerB;
        TextView answerAscore;
        TextView answerBscore;
        TextView title;
        ImageView profilepic;
    }


    @Override
    public int getCount() {
        return recentlist.size();
    }

    @Override
    public Object getItem(int position) {
        return recentlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        //return null;
        final ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.recentfragment_item, null);

            holder.username = (TextView) view.findViewById(R.id.usernameTextView);
            holder.question = (TextView) view.findViewById(R.id.questiontextView);
            holder.answerA = (TextView) view.findViewById(R.id.answerAtextView);
            holder.answerB = (TextView) view.findViewById(R.id.answerBtextView);
            
        }
        else{
            holder = (ViewHolder) view.getTag();
            holder.username.setText(recentlist.get(position).getUsername());
            holder.question.setText(recentlist.get(position).getQuestion());
            holder.answerA.setText(recentlist.get(position).getAnswerA());
            holder.answerB.setText(recentlist.get(position).getAnswerB());

        }
        return view;
    }
}
