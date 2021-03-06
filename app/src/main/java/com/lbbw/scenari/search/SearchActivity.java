package com.lbbw.scenari.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.TextView;

import com.lbbw.scenari.R;
import com.lbbw.scenari.customui.RoundImage;
import com.lbbw.scenari.profile.ProfileActivity;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

/**
 * Created by neegbeahreeves on 6/23/16.
 */
public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private TextView usernameLabel;
    private TextView postNum;
    private TextView votesNum;

    private ParseFile profilePic;
    RoundImage roundedImage;

    private ParseQueryAdapter<ParseObject> mAdapter;
    private SearchListViewAdapter searchListViewAdapter;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().show();

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        listView = (ListView)findViewById(R.id.listView2);

        searchListViewAdapter = new SearchListViewAdapter(this, searchListViewAdapter);
        listView.setAdapter(searchListViewAdapter);
        searchListViewAdapter.loadObjects();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_search, menu);
////
//        // Get the SearchView and set the searchable configuration
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
//        // Assumes current activity is the searchable activity
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
//
//        searchView.setSubmitButtonEnabled(true);
//        searchView.setOnQueryTextListener(this);
//
        return true;
    }



    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_profile){
            Intent profile = new Intent(this, ProfileActivity.class);
            startActivity(profile);
        }




        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //searchListViewAdapter.getFilter().filter(newText);

        return true;
    }
}
