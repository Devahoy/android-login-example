package com.devahoy.sample.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.devahoy.sample.login.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        Button loginButton = (Button)
                findViewById(R.id.facebook_login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFacebookLogin();
            }
        });
    }

    private void openFacebookLogin() {
        ParseFacebookUtils.logIn(this, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (e == null) {

                    if (parseUser == null) {
                        // user cancel
                    } else if (parseUser.isNew()) {
                        // User is new
                    } else {
                        // User login though facebook
                        Log.i("ParseUser", parseUser.getUsername());
                    }

                } else {
                    // any errors.
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
    }
}