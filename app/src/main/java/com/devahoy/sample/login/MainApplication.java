package com.devahoy.sample.login;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;

public class MainApplication extends Application {

    private final String APPLICATION_ID = "YOUR_APPLICATION_ID";
    private final String CLIENT_KEY = "YOUR_CLIENT_KEY";
    private final String FACEBOOK_APP_ID = "YOUR_FACEBOOK_APP_ID";

    @Override
    public void onCreate() {
        super.onCreate();
        // Add your initialization code here
        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);

        // Initial Facebook Utils
        ParseFacebookUtils.initialize(FACEBOOK_APP_ID);
    }
}
