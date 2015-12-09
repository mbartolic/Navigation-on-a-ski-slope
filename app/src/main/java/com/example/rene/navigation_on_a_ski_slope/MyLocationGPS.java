package com.example.rene.navigation_on_a_ski_slope;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;



public class MyLocationGPS extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap; // creating object type map
    DistanceFromPicture distanceFromPicture;
    Location loc =  new Location("Loc");
    Location myl = new Location("Myloc");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location_gps); //connecting with xml file
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager() //deafault
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;       //new object google map
        mMap.setMyLocationEnabled(true);    //enabeling current location (showning it on map)
        mMap.setOnMapLongClickListener(myOnMapLongClickListener);   //creating listener on map
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

          /*  CircleOptions circleOptions = new CircleOptions()
                    .center(new LatLng(46.308408, 16.346646))
                    .radius(50);

            Circle circle = mMap.addCircle(circleOptions);*/


            loc.setLatitude(46.308408);
            loc.setLongitude(16.346646);
            Location myLocation = mMap.getMyLocation();
            myl.setLatitude(myLocation.getLatitude());
            myl.setLongitude(myLocation.getLongitude());
            distanceFromPicture = new DistanceFromPicture();

            Toast.makeText(getApplicationContext(), distanceFromPicture.getDistance(loc, myl) + "", Toast.LENGTH_LONG).show();

          /*  mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(latLng.toString()));*/

         /*   Location myLocation = mMap.getMyLocation();     //try to get users location
            if(myLocation == null){
                Toast.makeText(getApplicationContext(), "My location is not avaible", Toast.LENGTH_LONG).show(); //showing note
            }else {
                PolylineOptions polylineOptions = new PolylineOptions();    // creating object for points
             //   polylineOptions.add(latLng);
                polylineOptions.add(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()));
                // creating point on map with users coordinates
                polylineOptions.add(new LatLng(46.308408, 16.346646)); //points in Varždin
                polylineOptions.add(new LatLng(46.308408, 16.346646));
                polylineOptions.add(new LatLng(46.296015, 16.341840));
                polylineOptions.width(5);                   //line width
                polylineOptions.color(Color.MAGENTA);       //color of line
                polylineOptions.geodesic(true);             //earth is not flat, ni dragi Bog ovo nezna
                mMap.addPolyline(polylineOptions);          //connecting points on map
            }*/
        }

    };


    @Override
    public void onLocationChanged(Location location) {
        /*Location loc =  new Location("Loc");
        loc.setLatitude(46.308408);
        loc.setLongitude(16.346646);
        Location myLocation = mMap.getMyLocation();
        distanceFromPicture = new DistanceFromPicture();
        Toast.makeText(getApplicationContext(), (int) distanceFromPicture.getDistance(loc, myLocation), Toast.LENGTH_LONG).show();
   */ }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
