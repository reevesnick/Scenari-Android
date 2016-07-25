package com.lbbw.scenari;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.crashlytics.android.Crashlytics;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;
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

        // Parse Push
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("GCMSenderId", "710119163065");
        installation.saveInBackground();;

        ParseUser.enableAutomaticUser();

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);




        ParseFacebookUtils.initialize(this.getApplicationContext());


        // Facebook SDK
        // Initialize the SDK before executing any other operations,
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

    }

}
