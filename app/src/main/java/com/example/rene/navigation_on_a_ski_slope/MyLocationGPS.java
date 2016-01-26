package com.example.rene.navigation_on_a_ski_slope;

import android.content.Context;
import android.content.Intent;
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
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class MyLocationGPS extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap; // creating object type map
    DistanceFromPicture distanceFromPicture;
    Location loc =  new Location("Loc");
    Location myl = new Location("Myloc");
    final Context context = this;

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
    }


    GoogleMap.OnMapLongClickListener myOnMapLongClickListener = new GoogleMap.OnMapLongClickListener() {
        @Override
        public void onMapLongClick(LatLng latLng) {
            loc.setLatitude(48.221094);      //location in wien
            loc.setLongitude(16.377882);     //location in wien
          //  Location myLocation = mMap.getMyLocation();
          //  myl.setLatitude(myLocation.getLatitude());
          //  myl.setLongitude(myLocation.getLongitude());
              distanceFromPicture = new DistanceFromPicture();
            float distance = distanceFromPicture.getDistance(loc,myl);
           Toast.makeText(getApplicationContext(), distance + "", Toast.LENGTH_LONG).show();

            Location point1, point2, point3, point4, point5, point6, point7, point8, point9, point10, point11, point12;
            point1 = new Location("point1");
            point1.setLatitude(48.220277);
            point1.setLongitude(16.378566);
            point2 = new Location("point2");
            point2.setLatitude(48.220377);      //64.599018
            point2.setLongitude(16.378641);
            point3 = new Location("point3"); //right point
            point3.setLatitude(48.220559);   //64.599318   collect
            point3.setLongitude(16.378759);  //
            point4 = new Location("point4");
            point4.setLatitude(48.220666);
            point4.setLongitude(16.378518);     //64.599184
            point5 = new Location("point5");
            point5.setLatitude(48.220666);      //64.598910
            point5.setLongitude(16.378244);
            point6 = new Location("point6"); //left point
            point6.setLatitude(48.220670);   //64.598722  collect
            point6.setLongitude(16.378051);  //
            point7 = new Location("point7");
            point7.setLatitude(48.220770);      //64.598848
            point7.setLongitude(16.378078);
            point8 = new Location("point8");
            point8.setLatitude(48.220924);      //64.599061
            point8.setLongitude(16.378137);
            point9 = new Location("point9");  //right point collect
            point9.setLatitude(48.220970);    //64.599122
            point9.setLongitude(16.378152);   //
            point10 = new Location("point10");
            point10.setLatitude(48.220999);     //64.598926
            point10.setLongitude(16.377927);
            point11 = new Location("point11");
            point11.setLatitude(48.221035);   //64.598989  collect
            point11.setLongitude(16.377954);
            point12 = new Location("point12");
            point12.setLatitude(48.221060); //64.598934
            point12.setLongitude(16.377874);
            List<Location> myLocations = new ArrayList<Location>();
            myLocations.add(point1);
            myLocations.add(point2);
            myLocations.add(point3);
            myLocations.add(point4);
            myLocations.add(point5);
            myLocations.add(point6);
            myLocations.add(point7);
            myLocations.add(point8);
            myLocations.add(point9);
            myLocations.add(point10);
            myLocations.add(point11);
            myLocations.add(point12);



            if(distance > 100) {
                Intent intent = new Intent(context, DisplayImage.class);
                startActivity(intent);
                ConvertingGpsCoordToXY convertingGpsCoordToXY = new ConvertingGpsCoordToXY();  //slanje gps podataka za konvertiranje
               // double convX = convertingGpsCoordToXY.convertLat(myLocation.getLatitude());
               // double convY = convertingGpsCoordToXY.convertLon(myLocation.getLongitude());
               // AverageDirection averageDirection = new AverageDirection(convX, convY);
            }
          /*  CircleOptions circleOptions = new CircleOptions()
                    .center(new LatLng(46.308408, 16.346646))
                    .radius(50);

            Circle circle = mMap.addCircle(circleOptions);*/



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
  /*       loc.setLatitude(46.308436);
            loc.setLongitude(16.330984);
            Location myLocation = mMap.getMyLocation();
            myl.setLatitude(myLocation.getLatitude());
            myl.setLongitude(myLocation.getLongitude());
            distanceFromPicture = new DistanceFromPicture();

            Toast.makeText(getApplicationContext(), distanceFromPicture.getDistance(loc, myl) + "", Toast.LENGTH_LONG).show();*/
    }

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
