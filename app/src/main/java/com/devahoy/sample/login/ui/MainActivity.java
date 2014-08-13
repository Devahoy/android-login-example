package com.devahoy.sample.login.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devahoy.sample.login.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class MainActivity extends ActionBarActivity {

    Button mChangePassword;
    TextView mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChangePassword = (Button) findViewById(R.id.change_password);
        mUsername = (TextView) findViewById(R.id.say_hi);

        ParseUser user = ParseUser.getCurrentUser();
        mUsername.setText(getString(R.string.say_hi) + " " + user.getUsername());

        mChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogPassword();
            }
        });
    }

    private void showDialogPassword() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);

        final EditText emailAddress = (EditText) view.findViewById(R.id.email_address);
        builder.setView(view);

        builder.setPositiveButton(getString(android.R.string.ok),
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String email = emailAddress.getText().toString();
                if(!TextUtils.isEmpty(email)) {

                    ParseUser.requestPasswordResetInBackground(email,
                        new RequestPasswordResetCallback() {
                            public void done(ParseException e) {
                                if (e == null) {
                                    Toast.makeText(getApplicationContext(),
                                            getString(R.string.change_password_message),
                                            Toast.LENGTH_SHORT).show();
                                    goToLogin();
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            e.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                    });
                }
            }
        });
        builder.setNegativeButton(getString(android.R.string.cancel), null);
        builder.show();
    }

    private void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
