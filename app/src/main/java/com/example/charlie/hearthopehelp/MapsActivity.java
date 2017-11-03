package com.example.charlie.hearthopehelp;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }






    //Called when user clicks on "Report" button
    public void report(View view)
    {
        //Do something in reponse to button...open ReportActivity
        Intent intent = new Intent(this, ReportActivity.class);
        startActivity(intent);
    }

    //Called when user clicks on "Add New Profile" button
    public void addNewProfile(View view)
    {
        //Do something in reponse to button...open ReportActivity
        Intent intent = new Intent(this, AddNewProfileActivity.class);
        startActivity(intent);
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
                Intent intent = new Intent(MapsActivity.this, LoginActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
