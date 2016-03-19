package com.example.rene.navigation_on_a_ski_slope;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.example.model.Coordinates;
import com.example.model.Instruction;
import com.example.mvp.presenter.CoordinatesPresenter;
import com.example.mvp.presenter.impl.CoordinatesPresenterImpl;
import com.example.mvp.view.CoordiantesView;
import com.example.utils.Constants;
import java.util.ArrayList;
import java.util.List;

public class MyLocationGPS extends Activity implements LocationListener, CoordiantesView{

    private LocationManager mLocMgr;
    CoordinatesPresenter coordinatesPresenter;
    private static final String LOG_KEY = "coordianes";
    int track;
    List<MyTrackPoints> myTrackPointsList = null;
    ArrayList<MyPointDouble> myLocations = null;
    List<MyPointDouble> turnPoint = new ArrayList<>();
    MyPointDouble locTurn =  new MyPointDouble();
    MyPointDouble locAfterTurn = new MyPointDouble();
    String turnLR;

    List<Location> myLocLeftSlopeTrack;
    List<Location> myLocLeftSlopeSkii;

    Location myLocation ;
    List<MyPointDouble> myLocHist;
    MyPointDouble point;

    DistanceFromPoint distanceFromPoint;
      final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_con);
        myLocations = new ArrayList<>();
        myLocLeftSlopeSkii = new ArrayList<>();


            mLocMgr = (LocationManager) getSystemService(LOCATION_SERVICE);
            mLocMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);

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
            point1.x =  46.302318;
            point1.y =  16.337191;
            point1.turn = 0;
            point2 = new MyTrackPoints();
            point2.x = 46.302167;
            point2.y = 16.337246;
            point2.turn = 0;
            point3 = new MyTrackPoints();
            point3.x = 46.302071;
            point3.y = 16.337299;
            point3.turn = 0;
            point4 = new MyTrackPoints();
            point4.x = 46.301957;
            point4.y = 16.337345;
            point4.turn = 0;
            point5 = new MyTrackPoints();
            point5.x =  46.301817;
            point5.y =  16.337339;        //turning point
            point5.turn = 1;
            point6 = new MyTrackPoints();
            point6.x =  46.301776;
            point6.y =  16.337222;
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
            point1.x =  46.302318;
            point1.y =  16.337191;
            point1.turn = 0;
            point2 = new MyTrackPoints();
            point2.x = 46.302167;
            point2.y = 16.337246;
            point2.turn = 0;
            point3 = new MyTrackPoints();
            point3.x = 46.302071;
            point3.y = 16.337299;
            point3.turn = 0;
            point4 = new MyTrackPoints();
            point4.x = 46.301957;
            point4.y = 16.337345;
            point4.turn = 0;
            point5 = new MyTrackPoints();
            point5.x =  46.301817;
            point5.y =  16.337339;        //turning point
            point5.turn = 1;
            point6 = new MyTrackPoints();
            point6.x =  46.301776;
            point6.y =  16.337222;
            point6.turn = 0;

            myTrackPointsList = new ArrayList<>();
            myTrackPointsList.add(point1);
            myTrackPointsList.add(point2);
            myTrackPointsList.add(point3);
            myTrackPointsList.add(point4);
            myTrackPointsList.add(point5);
            myTrackPointsList.add(point6);


        }else if (track == 3) {
            MyTrackPoints point1, point2, point3, point4, point5, point6;
            point1 = new MyTrackPoints();
            point1.x = 46.309478;
            point1.y = 16.347105;
            point1.turn = 0;
            point2 = new MyTrackPoints();
            point2.x = 46.30938;
            point2.y = 16.34700;
            point2.turn = 1;
            point3 = new MyTrackPoints();
            point3.x = 46.30942;
            point3.y = 16.34713;
            point3.turn = 0;
            point4 = new MyTrackPoints();
            point4.x = 46.30858;
            point4.y = 16.378140;
            point4.turn = 1;
            point5 = new MyTrackPoints();
            point5.x = 46.30851;
            point5.y = 16.34702;
            point5.turn = 0;
            point6 = new MyTrackPoints();
            point6.x = 46.308527;
            point6.y = 16.347021;
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
        MyPointDouble coord;
        List<MyPointDouble> mypp = new ArrayList<>();
        for (Instruction instruction : coordinates.getPaths().get(0).getInstructions()) {
            coord=new MyPointDouble();
            coord.x=(Double.parseDouble(instruction.getCoordinate().get(0)));
            coord.y=(Double.parseDouble(instruction.getCoordinate().get(1)));
            mypp.add(coord);
            Toast.makeText(getApplicationContext(), coord.x + " druga kordinata"+ coord.y +"" ,
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



    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        if(mLocMgr !=null)
        mLocMgr.removeUpdates(this);
        finish();


    }

@Override
    protected void onStop(){
        super.onStop();
    if(mLocMgr !=null)
    mLocMgr.removeUpdates(this);
    finish();
    }

    @Override
    public void onLocationChanged(Location location) {
        ConvertingGpsCoordToXY convertingGpsCoordToXY = new ConvertingGpsCoordToXY();

        myLocation = new Location(location);
        myLocHist = new ArrayList<>();
        distanceFromPoint = new DistanceFromPoint();
        Location l1 = new Location(LocationManager.GPS_PROVIDER);

        l1.setLongitude(locTurn.y);
        l1.setLatitude(locTurn.x);
        float distance = distanceFromPoint.getDistance(l1,myLocation);

        point = new MyPointDouble();
        point.x =  location.getLongitude();
        point.y =  location.getLatitude();
        myLocations.add(point);

        String dist = String.format("%.2f", distance);
        TextView textView;
        textView = (TextView) findViewById(R.id.distanceTxt);
        textView.setText(dist + "m");

         if(distance < 10) {
             for(int i = 0; i< myLocations.size(); i++) {
                 MyPointDouble converted = new MyPointDouble();
                 converted.x = convertingGpsCoordToXY.convertLon(myLocations.get(i).x);
                 converted.y = convertingGpsCoordToXY.convertLat(myLocations.get(i).y);
                 myLocHist.add(converted);
             }
             AverageDirection averageDirection = new AverageDirection();
             double angle = averageDirection.AvgDirection(myLocHist, turnPoint);
             Toast.makeText(getApplicationContext(), angle + "", Toast.LENGTH_SHORT).show();
             Intent intent = new Intent(context, DisplayImage.class);
             intent.putExtra("EXTRA_ANGLE", angle);
             intent.putExtra("turnLR", turnLR);
             startActivity(intent);
         }

        myLocLeftSlopeTrack =  new ArrayList<>();
        myLocLeftSlopeSkii.add(location);
        for(int i = 0; i < myTrackPointsList.size(); i++){
            Location loc = new Location(location);
            loc.setLongitude(myTrackPointsList.get(i).y);
            loc.setLatitude(myTrackPointsList.get(i).x);
            myLocLeftSlopeTrack.add(loc);
        }
        UserLocationStatus userLocationStatus = new UserLocationStatus();
        String mess = userLocationStatus.CalculatingIfUserLeftSlope(myLocLeftSlopeTrack, myLocLeftSlopeSkii);

        TextView textView2;
        textView2 = (TextView) findViewById(R.id.editText);
        if(mess != null) {
            textView2.setText(mess);
            textView2.setTextColor(Color.RED);
        }else{
            textView2.setText("");
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}


    }
