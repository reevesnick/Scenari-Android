package com.lbbw.scenari;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

import io.fabric.sdk.android.Fabric;

/**
 * Created by neegbeahreeves on 6/25/16.
 */
public class ScenariApplication extends Application {

    //private Context myContext;

    @Override
    public void onCreate(){
     super.onCreate();
        //getApplicationContext();

     // Fabric SDK
     Fabric.with(this, new Crashlytics());


    // Parse SDK

     Parse.initialize(new Parse.Configuration.Builder(this.getApplicationContext())
             .applicationId("dNNESXhlXqyY5oQNvAmK5u5VOyNspRKRRGE46II9")
             .clientKey("bZHmRQmG4NGdgk82rBBKORGt3LnVsOBeyl53Qywy")
             .server("https://parseapi.back4app.com/")


     .build()
     );


 }
}
