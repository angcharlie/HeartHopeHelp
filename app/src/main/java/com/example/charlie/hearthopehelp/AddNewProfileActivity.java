package com.example.charlie.hearthopehelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

public class AddNewProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_profile);
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
                FacebookSdk.sdkInitialize(getApplicationContext());
                LoginManager.getInstance().logOut();    //log user out

                //Go back to login activity
                Intent intent = new Intent(AddNewProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
