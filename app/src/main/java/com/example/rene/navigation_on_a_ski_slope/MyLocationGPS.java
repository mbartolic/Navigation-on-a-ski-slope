package com.example.rene.navigation_on_a_ski_slope;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.model.Coordinates;
import com.example.model.Instruction;
import com.example.mvp.presenter.CoordinatesPresenter;
import com.example.mvp.presenter.impl.CoordinatesPresenterImpl;
import com.example.mvp.view.CoordiantesView;
import com.example.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MyLocationGPS extends AppCompatActivity implements LocationListener, CoordiantesView {

    //Objekt klase koja implementira sucelje CoordinatesPresenter
    CoordinatesPresenter coordinatesPresenter;
    private static final String LOG_KEY = "coordianes";
    List<Location> trackLocations;


    DistanceFromPoint distanceFromPoint;
  //  Location loc =  new Location("Loc");
  //  Location myl = new Location("Myloc");
      final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location_gps); //connecting with xml file

        //Inicijalizacija klase koja implementira sucelje CoordinatesPresenter
        coordinatesPresenter = new CoordinatesPresenterImpl(this);
        //Dohvacanje koordinata preko sucelja CoordinatesPresenter
        coordinatesPresenter.getData(Constants.SOURCE_POINTS, Constants.DESTINATION_POINTS, Constants.CAR_ROUTE_TYPE, Constants.VOICE_INSTRUCTIONS, Constants.LANGUAGE);
    }



    public void storeFetchedCoordinates(Coordinates coordinates) {
        MyPointDouble coord=new MyPointDouble();
        for (Instruction instruction : coordinates.getPaths().get(0).getInstructions()) {
            coord.x=(Double.parseDouble(instruction.getCoordinate().get(0)));
            coord.y=(Double.parseDouble(instruction.getCoordinate().get(1)));

            Toast.makeText(getApplicationContext(), instruction.getCoordinate().get(0) + " druga koordimata"+ instruction.getCoordinate().get(1)+"" ,
                    Toast.LENGTH_SHORT).show();
        }
    }

//OVAJ DIO NE RADI
    public void storeFetchedSign(Coordinates coordinates) {
        int i=0;
        for (Instruction instruction : coordinates.getPaths().get(0).getInstructions()) {
            i = (instruction.getSign());

            Toast.makeText(getApplicationContext(), i +"" ,
                    Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Shows the angle on long click.
     */
    @Override
    public void onStart(){
        super.onStart();

        Intent inte = new Intent();
        trackLocations = inte.getParcelableExtra("trackLoc");

//-----------------------------------------------------------------------------------------------------------------------//
        MyPointDouble locInWien =  new MyPointDouble();
            MyPointDouble locAfterTurn = new MyPointDouble();
            List<MyPointDouble> turnPoint = new ArrayList<>();
            locInWien.y = 48.221094;      //location in wien
            locInWien.x = 16.377882;     //location in wien
            locAfterTurn.y = 48.221231;
            locAfterTurn.x =16.378046;
            turnPoint.add(locInWien);
            turnPoint.add(locAfterTurn);
         //   Location myLocation = mMap.getMyLocation();
          //  myl.setLatitude(myLocation.getLatitude());
          //  myl.setLongitude(myLocation.getLongitude());
          //  distanceFromPoint = new DistanceFromPoint();
         //   float distance = distanceFromPoint.getDistance(loc,myl);
           // Toast.makeText(getApplicationContext(), distance + "", Toast.LENGTH_LONG).show();
            ConvertingGpsCoordToXY convertingGpsCoordToXY = new ConvertingGpsCoordToXY();
            List<MyPointDouble> myLocHist;


            //simulates user skiing
        /*    MyPointDouble point1, point2, point3, point4, point5, point6, point7, point8, point9, point10, point11, point12, point13;
            point1 = new MyPointDouble();
            point1.y = 48.221051;
            point1.x = 16.378234;
            point2 = new MyPointDouble();
            point2.y = 48.221083;
            point2.x = 16.37820;
            point3 = new MyPointDouble();
            point3.y = 48.221105;
            point3.x = 16.378168;
            point4 = new MyPointDouble();
            point4.y = 48.221072;
            point4.x = 16.378140;
            point5 = new MyPointDouble();
            point5.y = 48.221030;
            point5.x = 16.378116;
            point6 = new MyPointDouble();
            point6.y =48.221048;
            point6.x =16.378092;
            point7 = new MyPointDouble();
            point7.y =48.221087;
            point7.x =16.378090;
            point8 = new MyPointDouble();
            point8.y = 48.221110;
            point8.x = 16.378060;
            point9 = new MyPointDouble();
            point9.y = 48.221077;
            point9.x = 16.378031;
            point10 = new MyPointDouble();
            point10.y = 48.221036;
            point10.x = 16.377999;
            point11 = new MyPointDouble();
            point11.y = 48.221067;
            point11.x = 16.377978;
            point12 = new MyPointDouble();
            point12.y =48.221115;
            point12.x =16.377959;
            point13 = new MyPointDouble();
            point13.y =48.221098;
            point13.x =16.37796;*/


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
            point12.x =  (16.377739);

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
       //     myLocations.add(point13);

            myLocHist = new ArrayList<>();


            for(int i = 0; i<myLocations.size(); i++){
                MyPointDouble converted = new MyPointDouble();
                converted.y = convertingGpsCoordToXY.convertLon(myLocations.get(i).y);
                converted.x = convertingGpsCoordToXY.convertLat(myLocations.get(i).x);
                myLocHist.add(converted);
            }

           // if(distance > 100) {
              //  double convX = convertingGpsCoordToXY.convertLat(myLocation.getLatitude());
              //  double convY = convertingGpsCoordToXY.convertLon(myLocation.getLongitude());
                AverageDirection averageDirection = new AverageDirection();
                double angle = averageDirection.AvgDirection(myLocHist, turnPoint);
             //   Toast.makeText(getApplicationContext(), angle + "", Toast.LENGTH_LONG).show();
                DisplayImage displayImage = new DisplayImage();
                Intent intent = new Intent(context, DisplayImage.class);
                intent.putExtra("EXTRA_ANGLE", angle);
                startActivity(intent);

        //--------------------------------------------------------------------------------------------------------------//
        //--------------------------------------------------------------------------------------------------------------//
        Location skiLocation, skiLocation1, skiLocation2, skiLocation3, skiLocation4;
        skiLocation = new Location("skiLocation");
        skiLocation.setLatitude(48.221194);      //skier location in Wien, for dynamic use we should use current location of skier
        skiLocation.setLongitude(16.377282);
        skiLocation1 = new Location("skiLocation1");
        skiLocation1.setLatitude(48.220822);
        skiLocation1.setLongitude(16.381717);
        skiLocation2 = new Location("skiLocation2");
        skiLocation2.setLatitude(48.221794);
        skiLocation2.setLongitude(16.382114);
        skiLocation3 = new Location("skiLocation3");
        //      skiLocation3.setLatitude(48.221194);
        //      skiLocation3.setLongitude(16.377282);
        skiLocation3.setLatitude(48.222537);
        skiLocation3.setLongitude(16.382457);
        skiLocation4 = new Location("skiLocation4");
        skiLocation4.setLatitude(48.223602);
        skiLocation4.setLongitude(16.382918);

        List<Location> skiLocations = new ArrayList<>();
        skiLocations.add(skiLocation);
        skiLocations.add(skiLocation1);
        skiLocations.add(skiLocation2);
        skiLocations.add(skiLocation3);
        skiLocations.add(skiLocation4);


        UserLocationStatus userLocationStatus = new UserLocationStatus();
        String mess = userLocationStatus.CalculatingIfUserLeftSlope(trackLocations, skiLocations);

        Toast.makeText(getApplicationContext(), mess + "", Toast.LENGTH_LONG).show();

        //------------------------------------------------------------------------------------------------------------------//

            }




    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();


    }

@Override
    protected void onStop(){
        super.onStop();
    finish();
    }



    @Override
    public void onLocationChanged(Location location) {

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
    //  }

    };
