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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
