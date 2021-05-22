package com.example.myapplication2;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG ="MapsActivity" ;
    private GoogleMap mMap;
    private Geocoder geocoder;
    private TextView button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        geocoder = new Geocoder(this);
        this.button2=(TextView) findViewById(R.id.button2);
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
        Intent intent=getIntent();
        String DEPART = intent.getStringExtra("depart1");
        String ARRIVE = intent.getStringExtra("arrive1");
        mMap.getUiSettings().setZoomControlsEnabled(true);
        try {
            List<Address> addresses = geocoder.getFromLocationName(""+DEPART,1  );
            Address address = addresses.get(0);
            LatLng depart = new LatLng(address.getLatitude(), address.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(depart)
                    .title(address.getLocality());
            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(depart, 12));

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            List<Address> addresses = geocoder.getFromLocationName(""+ARRIVE,1  );
            Address address = addresses.get(0);
            LatLng arrive = new LatLng(address.getLatitude(), address.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(arrive)
                    .title(address.getLocality());
            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(arrive, 12));

        } catch (IOException e) {
            e.printStackTrace();
        }

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(), MainActivity2.class);
                try {
                    List<Address> addresses = geocoder.getFromLocationName(""+DEPART,1  );
                    Address address = addresses.get(0);
                    LatLng depart = new LatLng(address.getLatitude(), address.getLongitude());
                    String latdep = Double.toString(address.getLatitude());
                    String londep =Double.toString(address.getLongitude());
                    otherActivity.putExtra("latdep",latdep);
                    otherActivity.putExtra("londep",londep);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    List<Address> addresses = geocoder.getFromLocationName(""+ARRIVE,1  );
                    Address address = addresses.get(0);
                    LatLng arrive = new LatLng(address.getLatitude(), address.getLongitude());
                    String latarr = Double.toString(address.getLatitude());
                    String lonarr =Double.toString(address.getLongitude());
                    otherActivity.putExtra("latarr",latarr);
                    otherActivity.putExtra("lonarr",lonarr);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivity(otherActivity);
                finish();
            }
        });
    }

}