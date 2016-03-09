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

public class MyLocationGPS extends AppCompatActivity implements LocationListener, CoordiantesView{

    //Objekt klase koja implementira sucelje CoordinatesPresenter
    CoordinatesPresenter coordinatesPresenter;
    private static final String LOG_KEY = "coordianes";
    int track;
    List<MyTrackPoints> myTrackPointsList = null;
    ArrayList<MyPointDouble> myLocations = null;
    List<MyPointDouble> turnPoint = new ArrayList<>();
    MyPointDouble locTurn =  new MyPointDouble();
    MyPointDouble locAfterTurn = new MyPointDouble();
    String turnLR;

    DistanceFromPoint distanceFromPoint;
  //  Location loc =  new Location("Loc");
  //  Location myl = new Location("Myloc");
      final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location_gps);

        //Inicijalizacija klase koja implementira sucelje CoordinatesPresenter
        coordinatesPresenter = new CoordinatesPresenterImpl(this);
        //Dohvacanje koordinata preko sucelja CoordinatesPresenter
        coordinatesPresenter.getData(Constants.SOURCE_POINTS, Constants.DESTINATION_POINTS, Constants.CAR_ROUTE_TYPE, Constants.VOICE_INSTRUCTIONS, Constants.LANGUAGE);

        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            if(bundle == null) {
                track = 0;
            } else {
                track = getIntent().getExtras().getInt("track");
            }
        }

        if(track == 1){
            MyTrackPoints point1, point2, point3, point4, point5, point6;
            point1 = new MyTrackPoints();
            point1.y =  48.221551;
            point1.x =  16.377165;
            point1.turn = 0;
            point2 = new MyTrackPoints();
            point2.y = 48.221552;
            point2.x = 16.377251;
            point2.turn = 0;
            point3 = new MyTrackPoints();
            point3.y = 48.221537;
            point3.x = 16.377366;
            point3.turn = 0;
            point4 = new MyTrackPoints();
            point4.y = 48.221482;
            point4.x = 16.377351;
            point4.turn = 0;
            point5 = new MyTrackPoints();
            point5.y =  48.221421;
            point5.x =  16.377342;        //turning point
            point5.turn = 1;
            point6 = new MyTrackPoints();
            point6.y =  48.221388;
            point6.x =  16.377484;
            point6.turn = 0;

            myTrackPointsList = new ArrayList<>();
            myTrackPointsList.add(point1);
            myTrackPointsList.add(point2);
            myTrackPointsList.add(point3);
            myTrackPointsList.add(point4);
            myTrackPointsList.add(point5);
            myTrackPointsList.add(point6);


        }else if (track == 2){
            MyTrackPoints point1, point2, point3, point4, point5, point6;
            point1 = new MyTrackPoints();
            point1.y =  46.306511;
            point1.x =  16.344795;
            point1.turn = 0;
            point2 = new MyTrackPoints();
            point2.y = 46.306828;
            point2.x = 16.344650;
            point2.turn = 0;
            point3 = new MyTrackPoints();
            point3.y = 46.307087;
            point3.x = 16.344553;
            point3.turn = 1;                //turning point
            point4 = new MyTrackPoints();
            point4.y = 46.307154;
            point4.x = 16.344864;
            point4.turn = 0;
            point5 = new MyTrackPoints();
            point5.y =  46.307213;
            point5.x =  16.345132;
            point5.turn = 0;
            point6 = new MyTrackPoints();
            point6.y =  46.307289;
            point6.x =  16.345642;
            point6.turn = 0;

            myTrackPointsList = new ArrayList<>();
            myTrackPointsList.add(point1);
            myTrackPointsList.add(point2);
            myTrackPointsList.add(point3);
            myTrackPointsList.add(point4);
            myTrackPointsList.add(point5);
            myTrackPointsList.add(point6);


        }else if (track == 3) {
            //Treba unjeti podatke za trecu stazu,      edit: netreba uneseni su
            MyTrackPoints point1, point2, point3, point4, point5, point6;
            point1 = new MyTrackPoints();
            point1.y = 46.309478;
            point1.x = 16.347105;
            point1.turn = 0;
            point2 = new MyTrackPoints();
            point2.y = 46.30938;
            point2.x = 16.34700;
            point2.turn = 1;
            point3 = new MyTrackPoints();
            point3.y = 46.30942;
            point3.x = 16.34713;
            point3.turn = 0;
            point4 = new MyTrackPoints();
            point4.y = 46.30858;
            point4.x = 16.378140;
            point4.turn = 1;
            point5 = new MyTrackPoints();
            point5.y = 46.30851;
            point5.x = 16.34702;
            point5.turn = 0;
            point6 = new MyTrackPoints();
            point6.y = 46.308527;
            point6.x = 16.347021;
            point6.turn = 0;
            myTrackPointsList = new ArrayList<>();
            myTrackPointsList.add(point1);
            myTrackPointsList.add(point2);
            myTrackPointsList.add(point3);
            myTrackPointsList.add(point4);
            myTrackPointsList.add(point5);
            myTrackPointsList.add(point6);
        }
        turnLR = turnLeftRight(myTrackPointsList);  //detect if turn is left or right
        int trackID = turningPoint(myTrackPointsList); //detect in which point is turning

        locTurn.y = myTrackPointsList.get(trackID).y;
        locTurn.x = myTrackPointsList.get(trackID).x;
        locAfterTurn.y = myTrackPointsList.get(trackID + 1).y;
        locAfterTurn.x = myTrackPointsList.get(trackID + 1).x;
        turnPoint.add(locTurn);
        turnPoint.add(locAfterTurn);
    }

    public String turnLeftRight(List<MyTrackPoints> myTrPointList) {
        String turn = null;
        for (int i = 0; i < myTrPointList.size(); i++) {
                if (myTrPointList.get(i).turn > 0) {
                    turn =  "right";
                } else if (myTrPointList.get(i).turn < 0) {
                    turn = "left";
                }
        }
        return turn;
    }

    public int turningPoint(List<MyTrackPoints> myTrPointList){
        int j = 0;
        for(int i = 0; i < myTrPointList.size(); i++) {
            if (myTrPointList.get(i).turn != 0) {
                j = i;
            }
        }
        return j;
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
/*
//OVAJ DIO NE RADI
    public void storeFetchedSign(Coordinates coordinates) {
        int i=0;
        for (Instruction instruction : coordinates.getPaths().get(0).getInstructions()) {
            i = (instruction.getSign());

            Toast.makeText(getApplicationContext(), i +"" ,
                    Toast.LENGTH_SHORT).show();
        }
    }*/

    /**
     * Shows the angle on long click.
     */
    @Override
    public void onStart(){
        super.onStart();
//------------------------------- Rene algoritam pocetak----------------------------------------------------------------------------------------//

        MyPointDouble point1, point2, point3, point4, point5, point6, point7, point8, point9, point10, point11, point12, point13, point14, point15, point16, point17;
        point1 = new MyPointDouble();
        point1.y =  46.302580;
        point1.x =  16.337077;
        point2 = new MyPointDouble();
        point2.y = 46.302571;
        point2.x = 16.337106;
        point3 = new MyPointDouble();
        point3.y = 46.302545;
        point3.x = 16.337145;
        point4 = new MyPointDouble();
        point4.y = 46.302511;
        point4.x = 16.337123;
        point5 = new MyPointDouble();
        point5.y =  46.302473;
        point5.x =  16.337096;        //turning point
        point6 = new MyPointDouble();
        point6.y =  46.302427;
        point6.x =  16.337153;
        point7 = new MyPointDouble();
        point7.y =  46.302403;
        point7.x =  16.337206;
        point8 = new MyPointDouble();
        point8.y = 46.302351;
        point8.x = 16.337229;
        point9 = new MyPointDouble();
        point9.y = 46.302276;
        point9.x = 16.337227;
        point10 = new MyPointDouble();
        point10.y = 46.302192;
        point10.x = 16.337217;
        point11 = new MyPointDouble();
        point11.y =  46.302129;
        point11.x =  16.337271;        //turning point
        point12 = new MyPointDouble();
        point12.y =  46.302081;
        point12.x =  16.337337;
        point13 = new MyPointDouble();
        point13.y = 46.302017;
        point13.x = 16.337333;
        point14 = new MyPointDouble();
        point14.y =  46.301974;
        point14.x =  16.337286;        //turning point
        point15 = new MyPointDouble();
        point15.y =  46.301908;
        point15.x =  16.337359;
        point16 = new MyPointDouble();
        point16.y = 46.301860;
        point16.x = 16.337426;
        point17 = new MyPointDouble();
        point17.y =  46.301816;
        point17.x =  16.337391;

        myLocations = new ArrayList<>();
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
        myLocations.add(point14);
        myLocations.add(point15);
        myLocations.add(point16);
        myLocations.add(point17);
         //   Location myLocation = mMap.getMyLocation();
          //  myl.setLatitude(myLocation.getLatitude());
          //  myl.setLongitude(myLocation.getLongitude());
          //  distanceFromPoint = new DistanceFromPoint();
         //   float distance = distanceFromPoint.getDistance(loc,myl);
           // Toast.makeText(getApplicationContext(), distance + "", Toast.LENGTH_LONG).show();
            ConvertingGpsCoordToXY convertingGpsCoordToXY = new ConvertingGpsCoordToXY();
            List<MyPointDouble> myLocHist;
            myLocHist = new ArrayList<>();

            for(int i = 0; i< myLocations.size(); i++) {
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
        Toast.makeText(getApplicationContext(), angle + "", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context, DisplayImage.class);
        intent.putExtra("EXTRA_ANGLE", angle);
        intent.putExtra("turnLR", turnLR);
        startActivity(intent);


        //-------------------------------- Rene algoritam kraj------------------------------------------------------------------------------//
        //------------------------------ Vunak Vunce algoritam pocetak-----------------------------------------------------------------------------//
    /*    Location skiLocation, skiLocation1, skiLocation2, skiLocation3, skiLocation4;
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

        Toast.makeText(getApplicationContext(), mess + "", Toast.LENGTH_LONG).show();*/

        //------------------------------------- Vunak Vunce algoritam kraj -----------------------------------------------------------------------------//

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
