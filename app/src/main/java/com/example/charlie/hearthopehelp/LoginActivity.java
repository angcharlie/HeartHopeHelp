package com.example.charlie.hearthopehelp;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;





public class LoginActivity extends AppCompatActivity {

    LoginButton login;
    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        //AppEventsLogger.activateApp(this);

        login = (LoginButton) findViewById(R.id.login_button);
        login.setReadPermissions("user_friends");

        // Callback registration FACEBOOK API
        login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code

                Toast toast = Toast.makeText(getApplicationContext(), "SUCCESSFUL LOGIN!", Toast.LENGTH_SHORT);
                toast.show();

                Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                startActivity(intent);
                finish();

            }

            @Override
            public void onCancel() {
                // App code
                Toast toast = Toast.makeText(getApplicationContext(), "LOGIN CANCELED!", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast toast = Toast.makeText(getApplicationContext(), "LOGIN FAILED", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }//end onCreate

    @Override
    public void onResume(){
        super.onResume();

        if(Profile.getCurrentProfile() != null && AccessToken.getCurrentAccessToken() != null)  //user is logged in
        {
            //Since user already logged in, go directly to MapsActivity
            Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
            startActivity(intent);

            //Retrieving fb user's name
            String fbName = Profile.getCurrentProfile().getName();
            Toast toast = Toast.makeText(getApplicationContext(), "Welcome back " + fbName, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    //Displaying overflow menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_app, menu);
        return true;
    }

    //onClick events for Menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                //Settings item selected
                Toast toast = Toast.makeText(getApplicationContext(), "Settings item clicked!", Toast.LENGTH_SHORT);
                toast.show();
                return true;
            case R.id.action_about:
                //About item selected
                Toast toast2 = Toast.makeText(getApplicationContext(), "About item clicked", Toast.LENGTH_SHORT);
                toast2.show();
                return true;
            case R.id.action_logout:
                //Logout item selected
                Toast toast3 = Toast.makeText(getApplicationContext(), "You are not logged in", Toast.LENGTH_SHORT);
                toast3.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //Called when user clicks on "Report" button
    public void report(View view)
    {
        //Do something in reponse to button...open ReportActivity
        Intent intent = new Intent(this, ReportActivity.class);
        startActivity(intent);
    }


}
