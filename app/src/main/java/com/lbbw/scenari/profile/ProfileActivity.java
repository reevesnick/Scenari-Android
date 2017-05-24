package com.lbbw.scenari;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import com.lbbw.scenari.customui.Circle_ImageView;
import com.frosquivel.magicalcamera.MagicalCamera;


/**
 * Created by neegbeahreeves on 6/23/16.
 */
public class ProfileActivity extends AppCompatActivity {
    private TextView usernameLabel;
    private TextView postNum;
    private TextView votesNum;


    private ParseFile profilePic;
    RoundImage roundedImage;

    private ParseQueryAdapter<ParseObject> mAdapter;
    private ProfileListViewAdapter profileListViewAdapter;
    private ListView listView;
    private Context mContext = this;

    private int RESIZE_PHOTO_PIXELS_PERCENTAGE = 1000;
    MagicalCamera magicalCamera = new MagicalCamera(this,RESIZE_PHOTO_PIXELS_PERCENTAGE);




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileactivity_main);

        ParseObject object = null;

        usernameLabel = (TextView) findViewById(R.id.usernameTextView);
        postNum = (TextView) findViewById(R.id.totalPostNumber);
        votesNum = (TextView) findViewById(R.id.totalVotesNumber);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().show();

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Circle_ImageView profileImage = (Circle_ImageView) findViewById(R.id.imageView);
        ParseFile imageFile = ParseUser.getCurrentUser().getParseFile("profile_pic");

        if (imageFile != null) {
            profileImage.setParseFile(imageFile);
            profileImage.loadInBackground();
        } else {
            //profileImage.setImageDrawable(R.drawable.placeholder);
        }

        TextView questionTextView = (TextView) findViewById(R.id.usernameTextView);
        questionTextView.setText("@" + ParseUser.getCurrentUser().getUsername().toString());

        TextView postCount = (TextView) findViewById(R.id.totalPostNumber);
        postCount.setText(ParseUser.getCurrentUser().getNumber("posts").toString());

        TextView votesCount = (TextView) findViewById(R.id.totalVotesNumber);
        votesCount.setText(ParseUser.getCurrentUser().getNumber("totalVotes").toString());

        listView = (ListView) findViewById(R.id.listView);
        profileListViewAdapter = new ProfileListViewAdapter(this, profileListViewAdapter);
        listView.setAdapter(profileListViewAdapter);
        profileListViewAdapter.loadObjects();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((SwipeLayout) (listView.getChildAt(position - listView.getFirstVisiblePosition()))).open(true);
            }
        });
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("ListView", "OnTouch");
                return false;
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "OnItemLongClickListener", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.e("ListView", "onScrollStateChanged");
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("ListView", "onItemSelected:" + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("ListView", "onNothingSelected:");
            }
        });



    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //call this method ever
        magicalCamera.resultPhoto(requestCode, resultCode, data);
        Bitmap.CompressFormat png = MagicalCamera.PNG;

        // ParseFile imageFile = ParseUser.getCurrentUser().getParseFile("profile_pic");
        ParseUser user = ParseUser.getCurrentUser();

        Circle_ImageView profileImage = (Circle_ImageView) findViewById(R.id.imageView);
        //with this form you obtain the bitmap
        profileImage.setImageBitmap(magicalCamera.getMyPhoto());
        //user.put("profile_pic", profileImage);



        //if you need save your bitmap in device use this method
        if(magicalCamera.savePhotoInMemoryDevice(magicalCamera.getMyPhoto(),"myPhotoName","myDirectoryName", MagicalCamera.JPEG, true)){
            Toast.makeText(ProfileActivity.this, "The photo is save in device, please check this", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(ProfileActivity.this, "Sorry your photo dont write in devide, please contact with fabian7593@gmail and say this error", Toast.LENGTH_SHORT).show();
        }
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

        if (id == R.id.action_share_profile){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, ParseUser.getCurrentUser().getUsername().toString()+" got " +ParseUser.getCurrentUser().getNumber("posts").toString()+ " posts and "+ParseUser.getCurrentUser().getNumber("totalVotes").toString()+ " total votes. Download the Scenari App for iOS and Android");
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, "Share via"));
        }

        if (id == R.id.action_upload_photo_camera){
   //         MagicalCamera magicalCamera = new MagicalCamera(this,RESIZE_PHOTO_PIXELS_PERCENTAGE);
            magicalCamera.takePhoto();
        }

        if (id == R.id.action_upload_photo_picker){
 //           MagicalCamera magicalCamera = new MagicalCamera(this,RESIZE_PHOTO_PIXELS_PERCENTAGE);
            magicalCamera.selectedPicture("my_header_name");
        }

        if (id == R.id.action_profile){
            Intent profile = new Intent(this, ProfileActivity.class);
            startActivity(profile);
        }

        return super.onOptionsItemSelected(item);
    }


}
