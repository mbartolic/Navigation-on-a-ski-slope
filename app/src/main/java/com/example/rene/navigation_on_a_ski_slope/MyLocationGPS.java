package com.example.rene.navigation_on_a_ski_slope;

import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class MyLocationGPS extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location_gps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.setOnMapLongClickListener(myOnMapLongClickListener);
/*
        PolylineOptions rectOptions =  new PolylineOptions()
                .add(new LatLng(46.308408, 16.346646))
                .add(new LatLng(46.308408, 16.346646))
                .add(new LatLng(46.296015, 16.341840))
                .width(8)
                .color(Color.BLUE);

        mMap.addPolyline(rectOptions);*/
    }


    GoogleMap.OnMapLongClickListener myOnMapLongClickListener = new GoogleMap.OnMapLongClickListener() {
        @Override
        public void onMapLongClick(LatLng latLng) {
          /*  mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(latLng.toString()));*/

            Location myLocation = mMap.getMyLocation();
            if(myLocation == null){
                Toast.makeText(getApplicationContext(), "My location is not avaible", Toast.LENGTH_LONG).show();
            }else {
                PolylineOptions polylineOptions = new PolylineOptions();
             //   polylineOptions.add(latLng);
                polylineOptions.add(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()));
                polylineOptions.add(new LatLng(46.308408, 16.346646));
                polylineOptions.add(new LatLng(46.308408, 16.346646));
                polylineOptions.add(new LatLng(46.296015, 16.341840));
                polylineOptions.width(5);
                polylineOptions.color(Color.MAGENTA);
                polylineOptions.geodesic(true);
                mMap.addPolyline(polylineOptions);
            }
        }

    };




}
