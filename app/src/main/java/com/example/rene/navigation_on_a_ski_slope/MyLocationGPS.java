package com.example.rene.navigation_on_a_ski_slope;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MyLocationGPS extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap; // creating object type map
    DistanceFromPoint distanceFromPoint;
  //  Location loc =  new Location("Loc");
  //  Location myl = new Location("Myloc");
      final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location_gps); //connecting with xml file
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager() //deafault
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
            }

    /**
     * Creates Google Map and shows the current location on map.
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;       //new object google map
        mMap.setMyLocationEnabled(true);    //enabling current location (showing it on map)
        mMap.setOnMapLongClickListener(myOnMapLongClickListener);   //creating listener on map
    }

    /**
     * Shows the angle on long click.
     */
    GoogleMap.OnMapLongClickListener myOnMapLongClickListener = new GoogleMap.OnMapLongClickListener() {
        @Override
        public void onMapLongClick(LatLng latLng) {
            MyPointDouble locInWien =  new MyPointDouble();
            locInWien.y = 48.221094;      //location in wien
            locInWien.x = 16.377882;     //location in wien
         //   Location myLocation = mMap.getMyLocation();
          //  myl.setLatitude(myLocation.getLatitude());
          //  myl.setLongitude(myLocation.getLongitude());
          //  distanceFromPoint = new DistanceFromPoint();
         //   float distance = distanceFromPoint.getDistance(loc,myl);
           // Toast.makeText(getApplicationContext(), distance + "", Toast.LENGTH_LONG).show();
            ConvertingGpsCoordToXY convertingGpsCoordToXY = new ConvertingGpsCoordToXY();
            List<MyPointDouble> myLocHist;


            //simulates user skiing
            MyPointDouble point1, point2, point3, point4, point5, point6, point7, point8, point9, point10, point11, point12, point13;
            point1 = new MyPointDouble();
            point1.y = 48.221051;
            point1.x = 16.378234;
            point2 = new MyPointDouble();
            point2.y = 48.221083;      //64.599018
            point2.x = 16.37820;
            point3 = new MyPointDouble(); //right point
            point3.y = 48.221105;   //64.599318   collect
            point3.x = 16.378168;  //
            point4 = new MyPointDouble();
            point4.y = 48.221072;
            point4.x = 16.378140;     //64.599184
            point5 = new MyPointDouble();
            point5.y = 48.221030;      //64.598910
            point5.x = 16.378116;
            point6 = new MyPointDouble();
            point6.y =48.221048; //64.598934
            point6.x =16.378092;
            point7 = new MyPointDouble(); //left point
            point7.y =48.221087;   //64.598722  collect
            point7.x =16.378090;  //
            point8 = new MyPointDouble();
            point8.y = 48.221110;      //64.598848
            point8.x = 16.378060;
            point9 = new MyPointDouble();
            point9.y = 48.221077;      //64.599061
            point9.x = 16.378031;
            point10 = new MyPointDouble();  //right point collect
            point10.y = 48.221036;    //64.599122
            point10.x = 16.377999;   //
            point11 = new MyPointDouble();
            point11.y = 48.221067;     //64.598926
            point11.x = 16.377978;
            point12 = new MyPointDouble();
            point12.y =48.221115;   //64.598989  collect
            point12.x =16.377959;
            point13 = new MyPointDouble();
            point13.y =48.221115;   //64.598989  collect
            point13.x =16.377959;

/*
            MyPointDouble point1, point2, point3, point4, point5, point6, point7, point8, point9, point10, point11, point12;
            point1 = new MyPointDouble();
            point1.y =  48.219962;   //64.598517
            point1.x =  16.378555;
            point2 = new MyPointDouble();
            point2.y = 48.220169;  //64.598853
            point2.x = 16.378684;
            point3 = new MyPointDouble(); //right point
            point3.y = (48.220441);   //64.599232   collect
            point3.x = (16.378791);  //
            point4 = new MyPointDouble();
            point4.y = (48.220412);
            point4.x = (16.378330);     //64.598742
            point5 = new MyPointDouble();
            point5.y =  (48.220376);      //64.598449
            point5.x =  (16.378073);
            point6 = new MyPointDouble(); //left point
            point6.y =  (48.220383);   //64.598166  collect
            point6.x =  (16.377783);  //
            point7 = new MyPointDouble();
            point7.y =  (48.220590);      //64.598555
            point7.x =  (16.377965);
            point8 = new MyPointDouble();
            point8.y =  (48.220762);      //64.598866
            point8.x =  (16.378104);
            point9 = new MyPointDouble();  //right point collect
            point9.y =  (48.220948);    //64.599234
            point9.x =  (16.378286);   //
            point10 = new MyPointDouble();
            point10.y =  (48.220898);     //64.598808
            point10.x =  (16.377910);
            point11 = new MyPointDouble();
            point11.y =  (48.220877);   //64.598519  collect
            point11.x =  (16.377642);
            point12 = new MyPointDouble();
            point12.y =  (48.221041); //64.598780
            point12.x =  (16.377739);*/

            List<MyPointDouble> myLocations = new ArrayList<>();
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
            myLocations.add(point13);

            myLocHist = new ArrayList<>();


            for(int i = 0; i<myLocations.size(); i++){
                MyPointDouble converted = new MyPointDouble();
                converted.y = convertingGpsCoordToXY.convertLon(myLocations.get(i).y);
                converted.x = convertingGpsCoordToXY.convertLat(myLocations.get(i).x);
                myLocHist.add(converted);
            }

           // if(distance > 100) {
              //  Intent intent = new Intent(context, DisplayImage.class);
              //  startActivity(intent);
                  //slanje gps podataka za konvertiranje
              //  double convX = convertingGpsCoordToXY.convertLat(myLocation.getLatitude());
              //  double convY = convertingGpsCoordToXY.convertLon(myLocation.getLongitude());
                AverageDirection averageDirection = new AverageDirection();
                double angle = averageDirection.AvgDirection(myLocations);
                Toast.makeText(getApplicationContext(), angle + "", Toast.LENGTH_LONG).show();
              /*  Intent intent = new Intent(context, DisplayImage.class);
                startActivity(intent);
                DisplayImage displayImage = new DisplayImage();
            */
            }
      //  }

    };


    @Override
    public void onLocationChanged(Location location) { }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) { }

    @Override
    public void onProviderEnabled(String provider) { }

    @Override
    public void onProviderDisabled(String provider) { }
}
