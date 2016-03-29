package com.example.rene.navigation_on_a_ski_slope;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.Coordinates;
import com.example.model.Instruction;
import com.example.mvp.presenter.CoordinatesPresenter;
import com.example.mvp.presenter.impl.CoordinatesPresenterImpl;
import com.example.mvp.view.CoordiantesView;
import com.example.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomisav on 29.3.2016..
 */
public class Simulation extends Activity implements CoordiantesView {

    CoordinatesPresenter coordinatesPresenter;
    private static final String LOG_KEY = "coordianes";
    int track;
    List<MyTrackPoints> myTrackPointsList = null;
    List<MyTrackPoints> mySkiPointsList = null;
    ArrayList<MyTrackPoints> myLocations = null;
    List<MyTrackPoints> turnPoint = new ArrayList<>();
    MyTrackPoints locTurn =  new MyTrackPoints();
    MyTrackPoints locAfterTurn = new MyTrackPoints();
    String turnLR;
    int trackID = 0;
    List<Location> myLocLeftSlopeTrack;
    List<Location> myLocLeftSlopeSkii;

    Location myLocation ;
    List<MyTrackPoints> myLocHist;
    MyTrackPoints point;

    DistanceFromPoint distanceFromPoint;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_con);
        addListenerToButton();
        myLocations = new ArrayList<>();
        myLocLeftSlopeSkii = new ArrayList<>();


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

        if (track == 4){
            MyTrackPoints point1, point2, point3, point4, point5, point6;
            point1 = new MyTrackPoints();
            point1.x = 46.309225;
            point1.y = 16.346991;
            point1.turn = 0;
            point2 = new MyTrackPoints();
            point2.x = 46.309436;
            point2.y = 16.346951;
            point2.turn = 1;
            point3 = new MyTrackPoints();
            point3.x = 46.309499;
            point3.y = 16.347198;
            point3.turn = 0;
            point4 = new MyTrackPoints();
            point4.x = 46.309566;
            point4.y = 16.347455;
            point4.turn = 0;
            point5 = new MyTrackPoints();
            point5.x = 46.309625;
            point5.y = 16.347761;
            point5.turn = 0;
            point6 = new MyTrackPoints();
            point6.x = 46.309940;
            point6.y = 16.349011;
            point6.turn = 0;
            myTrackPointsList = new ArrayList<>();
            myTrackPointsList.add(point1);
            myTrackPointsList.add(point2);
            myTrackPointsList.add(point3);
            myTrackPointsList.add(point4);
            myTrackPointsList.add(point5);
            myTrackPointsList.add(point6);
        }
        trackID = turningPoint(myTrackPointsList,trackID); //detect in which point is turning
        turnLR = turnLeftRight(myTrackPointsList, trackID);  //detect if turn is left or right


        locTurn.y = myTrackPointsList.get(trackID).y;
        locTurn.x = myTrackPointsList.get(trackID).x;
        locAfterTurn.y = myTrackPointsList.get(trackID + 1).y;
        locAfterTurn.x = myTrackPointsList.get(trackID + 1).x;
        turnPoint.add(locTurn);
        turnPoint.add(locAfterTurn);
    }



    public void addListenerToButton() {
        Button btnStart = (Button) findViewById(R.id.startBtn);
        Button btnStop = (Button) findViewById(R.id.stopBtn);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTrackPoints point1, point2, point3, point4, point5, point6;
                point1 = new MyTrackPoints();
                point1.x = 46.309225;
                point1.y = 16.346991;
                point2 = new MyTrackPoints();
                point2.x = 46.309436;
                point2.y = 16.346951;
                point3 = new MyTrackPoints();
                point3.x = 46.309499;
                point3.y = 16.347198;
                point4 = new MyTrackPoints();
                point4.x = 46.309566;
                point4.y = 16.347455;
                point5 = new MyTrackPoints();
                point5.x = 46.309625;
                point5.y = 16.347761;
                point6 = new MyTrackPoints();
                point6.x = 46.309940;
                point6.y = 16.349011;
                ArrayList<Object> skiPointsList = new ArrayList<>();
                skiPointsList.add(point1);
                skiPointsList.add(point2);
                skiPointsList.add(point3);
                skiPointsList.add(point4);
                skiPointsList.add(point5);
                skiPointsList.add(point6);

                for (int a = 0; a < skiPointsList.size(); a++) {

                    ConvertingGpsCoordToXY convertingGpsCoordToXY = new ConvertingGpsCoordToXY();

                    myLocation = skiPointsList.get(a);
                    myLocHist = new ArrayList<>();
                    distanceFromPoint = new DistanceFromPoint();
                    Location l1 = new Location(LocationManager.GPS_PROVIDER);
                    Location l2 = new Location(LocationManager.GPS_PROVIDER);

                    int size = myTrackPointsList.size() - 1;
                    l1.setLongitude(myTrackPointsList.get(size).y);    //track end y
                    l1.setLatitude(myTrackPointsList.get(size).x);     //track end x
                    l2.setLongitude(locTurn.y);                        //turn y
                    l2.setLatitude(locTurn.x);                         //turn x

                    float distanceToTrackEnd = distanceFromPoint.getDistance(l1, myLocation);
                    float distanceToTurn = distanceFromPoint.getDistance(l2, myLocation);
                    float distanceFromTurnToTrackEnd = distanceFromPoint.getDistance(l2, l1);

                    point = new MyTrackPoints();
                    //point.x =  location.getLongitude();
                    //point.y =  location.getLatitude();
                    myLocations.add(point);

                    String dist = String.format("%.0f", distanceToTrackEnd);
                    TextView distanceToFinishTxt;
                    distanceToFinishTxt = (TextView) findViewById(R.id.distanceTxt);
                    distanceToFinishTxt.setText("Distance to finish: " + dist + "m");

                    String dist2 = String.format("%.0f", distanceToTurn);
                    TextView distanceToTurnTxt;
                    distanceToTurnTxt = (TextView) findViewById(R.id.txtDistanceToTurn);
                    distanceToTurnTxt.setText("Distance to turn: " + dist2 + "m");/////////////////////
                    if (distanceToTurn < 10 && (distanceToTrackEnd > distanceFromTurnToTrackEnd)) {
                        for (int i = 0; i < myLocations.size(); i++) {
                            MyTrackPoints converted = new MyTrackPoints();
                            converted.x = convertingGpsCoordToXY.convertLon(myLocations.get(i).x);
                            converted.y = convertingGpsCoordToXY.convertLat(myLocations.get(i).y);
                            myLocHist.add(converted);
                        }
                        AverageDirection averageDirection = new AverageDirection();
                        double angle = averageDirection.AvgDirection(myLocHist, turnPoint);
                        //  distanceToFinishTxt.setText("");
                        distanceToTurnTxt.setText("Distance to turn: " + dist2 + "m");
                        if (turnLR != null) {
                            setImage(angle, turnLR);
                        }
                        trackID = turningPoint(myTrackPointsList, trackID); //detect in which point is turning
                        turnLR = turnLeftRight(myTrackPointsList, trackID);  //detect if turn is left or right

                        locTurn.y = myTrackPointsList.get(trackID).y;
                        locTurn.x = myTrackPointsList.get(trackID).x;
                        locAfterTurn.y = myTrackPointsList.get(trackID + 1).y;
                        locAfterTurn.x = myTrackPointsList.get(trackID + 1).x;
                        turnPoint.clear();
                        turnPoint.add(locTurn);
                        turnPoint.add(locAfterTurn);
                    } else {
                        ImageView imageView = (ImageView) findViewById(R.id.imgViewArrow);
                        imageView.setImageDrawable(null);
                        //distanceToTurnTxt.setText("");
                    }

                    myLocLeftSlopeTrack = new ArrayList<>();
                    myLocLeftSlopeSkii.add(location);
                    for (int i = 0; i < myTrackPointsList.size(); i++) {
                        Location loc = new Location(location);
                        loc.setLongitude(myTrackPointsList.get(i).y);
                        loc.setLatitude(myTrackPointsList.get(i).x);
                        myLocLeftSlopeTrack.add(loc);
                    }
                    int counter = 0;
                    String mess = null;
                    UserLocationStatus userLocationStatus = new UserLocationStatus();
                    counter = userLocationStatus.CalculatingIfUserLeftSlope(myLocLeftSlopeTrack, myLocation, counter);
                    if (counter > 4) {
                        mess = "User has left the slope!";
                    }

                    Animation anim = new AlphaAnimation(0.0f, 1.0f);
                    anim.setDuration(500);
                    anim.setStartOffset(20);
                    anim.setRepeatMode(Animation.REVERSE);
                    anim.setRepeatCount(Animation.INFINITE);

                    TextView leftSlope = (TextView) findViewById(R.id.editText);
                    if (mess != null) {
                        leftSlope.setText("" + mess);
                        leftSlope.startAnimation(anim);
                    } else {
                        leftSlope.setText("");
                    }

                }

            }
            }
        });

    public String turnLeftRight(List<MyTrackPoints> myTrPointList, int turnID) {
        String turn = null;

        if (myTrPointList.get(turnID).turn > 0) {
            turn =  "right";
        } else if (myTrPointList.get(turnID).turn < 0) {
            turn = "left";
        }

        return turn;
    }

    public int turningPoint(List<MyTrackPoints> myTrPointList, int turnID){
        int j = 0;
        for(int i = turnID + 1; i < myTrPointList.size(); i++) {
            if (myTrPointList.get(i).turn != 0) {
                j = i;
                break;
            }
        }
        return j;
    }


    List<MyTrackPoints> mypp = new ArrayList<>();


    public void setImage(double angle, String turnLR){
        ImageView imageView = (ImageView)findViewById(R.id.imgViewArrow);
        if(angle >= 0 && angle <= 60 && turnLR.equalsIgnoreCase("right")){
            imageView.setImageResource(R.drawable.southeast);
        }else if(angle > 60 && angle < 120 && turnLR.equalsIgnoreCase("right")){
            imageView.setImageResource(R.drawable.east);
        }else if(angle >= 120 && angle <= 180 && turnLR.equalsIgnoreCase("right")){
            imageView.setImageResource(R.drawable.northeast);
        }else if(angle >= 120 && angle <= 180 && turnLR.equalsIgnoreCase("left")){
            imageView.setImageResource(R.drawable.northwest);
        }else if(angle > 60 && angle < 120 && turnLR.equalsIgnoreCase("left")){
            imageView.setImageResource(R.drawable.west);
        }else if(angle >= 0 && angle <= 60 && turnLR.equalsIgnoreCase("left")){
            imageView.setImageResource(R.drawable.southwest);
        }else{
            imageView.setImageResource(R.drawable.north);
        }
    }


}
