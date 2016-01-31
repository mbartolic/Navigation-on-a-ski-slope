package com.example.rene.navigation_on_a_ski_slope;

import android.content.Context;
import android.graphics.PointF;
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
import java.util.Objects;

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
            PointF locInWien =  new PointF();
            locInWien.y = (float) 48.221094;      //location in wien
            locInWien.x = (float) 16.377882;     //location in wien
         //   Location myLocation = mMap.getMyLocation();
          //  myl.setLatitude(myLocation.getLatitude());
          //  myl.setLongitude(myLocation.getLongitude());
          //  distanceFromPicture = new DistanceFromPicture();
         //   float distance = distanceFromPicture.getDistance(loc,myl);
           // Toast.makeText(getApplicationContext(), distance + "", Toast.LENGTH_LONG).show();
            ConvertingGpsCoordToXY convertingGpsCoordToXY = new ConvertingGpsCoordToXY();
            List<PointF> myLocHist;

            //simulates user skiing
         /*   PointF point1, point2, point3, point4, point5, point6, point7, point8, point9, point10, point11, point12;
            point1 = new PointF();
            point1.y = (float) 48.220277;
            point1.x = (float) 16.378566;
            point2 = new PointF();
            point2.y = (float) (48.220377);      //64.599018
            point2.x = (float)(16.378641);
            point3 = new PointF(); //right point
            point3.y = (float) (48.220559);   //64.599318   collect
            point3.x = (float)(16.378759);  //
            point4 = new PointF();
            point4.y = (float) (48.220666);
            point4.x = (float)(16.378518);     //64.599184
            point5 = new PointF();
            point5.y = (float) (48.220666);      //64.598910
            point5.x = (float)(16.378244);
            point6 = new PointF(); //left point
            point6.y = (float) (48.220670);   //64.598722  collect
            point6.x = (float)(16.378051);  //
            point7 = new PointF();
            point7.y = (float) (48.220770);      //64.598848
            point7.x = (float)(16.378078);
            point8 = new PointF();
            point8.y = (float) (48.220924);      //64.599061
            point8.x = (float)(16.378137);
            point9 = new PointF();  //right point collect
            point9.y = (float) (48.220970);    //64.599122
            point9.x = (float)(16.378152);   //
            point10 = new PointF();
            point10.y = (float) (48.220999);     //64.598926
            point10.x = (float)(16.377927);
            point11 = new PointF();
            point11.y = (float) (48.221035);   //64.598989  collect
            point11.x = (float)(16.377954);
            point12 = new PointF();
            point12.y = (float) (48.221060); //64.598934
            point12.x = (float)(16.377874);*/
            PointF point1, point2, point3, point4, point5, point6, point7, point8, point9, point10, point11, point12;
            point1 = new PointF();
            point1.y = (float) 48.219962;   //64.598517
            point1.x = (float) 16.378555;
            point2 = new PointF();
            point2.y = (float) (48.220169);  //64.598853
            point2.x = (float) (16.378684);
            point3 = new PointF(); //right point
            point3.y = (float) (48.220441);   //64.599232   collect
            point3.x = (float) (16.378791);  //
            point4 = new PointF();
            point4.y = (float) (48.220412);
            point4.x = (float) (16.378330);     //64.598742
            point5 = new PointF();
            point5.y = (float) (48.220376);      //64.598449
            point5.x = (float) (16.378073);
            point6 = new PointF(); //left point
            point6.y = (float) (48.220383);   //64.598166  collect
            point6.x = (float) (16.377783);  //
            point7 = new PointF();
            point7.y = (float) (48.220590);      //64.598555
            point7.x = (float) (16.377965);
            point8 = new PointF();
            point8.y = (float) (48.220762);      //64.598866
            point8.x = (float) (16.378104);
            point9 = new PointF();  //right point collect
            point9.y = (float) (48.220948);    //64.599234
            point9.x = (float) (16.378286);   //
            point10 = new PointF();
            point10.y = (float) (48.220898);     //64.598808
            point10.x = (float) (16.377910);
            point11 = new PointF();
            point11.y = (float) (48.220877);   //64.598519  collect
            point11.x = (float) (16.377642);
            point12 = new PointF();
            point12.y = (float) (48.221041); //64.598780
            point12.x = (float) (16.377739);
            List<PointF> myLocations = new ArrayList<>();
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

            myLocHist = new ArrayList<>();
            PointF converted = new PointF();
            for(int i = 0; i<myLocations.size(); i++){
                converted.x = convertingGpsCoordToXY.convertLon(myLocations.get(i).y);
                converted.y = convertingGpsCoordToXY.convertLat(myLocations.get(i).x);
                myLocHist.add(converted);
            }

           // if(distance > 100) {
              //  Intent intent = new Intent(context, DisplayImage.class);
              //  startActivity(intent);
                  //slanje gps podataka za konvertiranje
              //  double convX = convertingGpsCoordToXY.convertLat(myLocation.getLatitude());
              //  double convY = convertingGpsCoordToXY.convertLon(myLocation.getLongitude());
                AverageDirection averageDirection = new AverageDirection();
                float angle = (float) averageDirection.AvgDirection(myLocHist);
                Toast.makeText(getApplicationContext(), angle + "", Toast.LENGTH_LONG).show();
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
