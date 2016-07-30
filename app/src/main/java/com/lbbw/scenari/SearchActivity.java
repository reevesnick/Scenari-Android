package com.lbbw.scenari;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

/**
 * Created by neegbeahreeves on 6/23/16.
 */
public class SearchActivity extends AppCompatActivity {
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

        ParseObject object = null;



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().show();

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
/*
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.com_parse_ui_app_logo));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
            }
        });
*/
        /*
        ParseImageView profileImage = (ParseImageView)findViewById(R.id.imageView);
        ParseFile imageFile = ParseUser.getCurrentUser().getParseFile("profile_pic");

        if (imageFile != null){
            profileImage.setParseFile(imageFile);
            profileImage.loadInBackground();
        }
        else{
        }


*/
        listView = (ListView)findViewById(R.id.listView2);

        searchListViewAdapter = new SearchListViewAdapter(this, searchListViewAdapter);
        listView.setAdapter(searchListViewAdapter);
        // listView.setAdapter(recentListViewAdapter);
        searchListViewAdapter.loadObjects();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        //SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        // Assumes current activity is the searchable activity
        //searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        //searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default


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

        //noinspection SimplifiableIfStatement
        /*
        if (id == R.id.action_settings) {
            return true;
        }
        */

        if (id == R.id.action_profile){
            Intent profile = new Intent(this, ProfileActivity.class);
            startActivity(profile);
        }




        return super.onOptionsItemSelected(item);
    }
}
