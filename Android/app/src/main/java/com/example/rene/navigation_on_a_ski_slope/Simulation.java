package com.example.rene.navigation_on_a_ski_slope;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.model.Coordinates;
import com.example.mvp.view.CoordiantesView;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Rene on 29.3.2016..
 */
public class Simulation extends Activity implements LocationListener, CoordiantesView {

    List<MyTrackPoints> myTrackPointsList = null;
    ArrayList<MyTrackPoints> myLocations = null;
    List<MyTrackPoints> turnPoint = new ArrayList<>();
    MyTrackPoints locTurn =  new MyTrackPoints();
    MyTrackPoints locAfterTurn = new MyTrackPoints();
    String turnLR;
    int trackID = 0;
    List<Location> myLocLeftSlopeTrack;
    List<Location> myLocLeftSlopeSkii;

    static List<MyTrackPoints> myLocation ;
    List<MyTrackPoints> myLocHist;
    DistanceFromPoint distanceFromPoint;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_con);
        addListenerToButton();
        myLocations = new ArrayList<>();
        myLocLeftSlopeSkii = new ArrayList<>();

        MyTrackPoints point1, point2, point3, point4, point5, point6, point7, point8,point9,point10,point11;
        point1 = new MyTrackPoints();
        point1.x =  46.302627;
        point1.y =  16.337065;
        point1.turn = 0;
        point2 = new MyTrackPoints();
        point2.x = 46.302576;
        point2.y = 16.336854;
        point2.turn = 0;
        point3 = new MyTrackPoints();
        point3.x = 46.302517;
        point3.y = 16.336727;
        point3.turn = -1;
        point4 = new MyTrackPoints();
        point4.x = 46.302316;
        point4.y = 16.336794;
        point4.turn = 0;
        point5 = new MyTrackPoints();
        point5.x =  46.302054;
        point5.y =  16.336893;
        point5.turn = 0;
        point6 = new MyTrackPoints();
        point6.x =  46.301974;
        point6.y =  16.336925;
        point6.turn = 1;
        point7 = new MyTrackPoints();
        point7.x = 46.301944;
        point7.y = 16.336783;
        point7.turn= 0;
        point8 = new MyTrackPoints();
        point8.x = 46.301887;
        point8.y = 16.336464;
        point8.turn= 0;
        point9 = new MyTrackPoints();
        point9.x = 46.301838;
        point9.y = 16.336236;
        point9.turn= -1;
        point10 = new MyTrackPoints();
        point10.x = 46.301735;
        point10.y = 16.336204;
        point10.turn= 0;
        point11 = new MyTrackPoints();
        point11.x = 46.301662;
        point11.y = 16.336151;
        point11.turn= 0;
        myTrackPointsList = new ArrayList<>();
        myTrackPointsList.add(point1);
        myTrackPointsList.add(point2);
        myTrackPointsList.add(point3);
        myTrackPointsList.add(point4);
        myTrackPointsList.add(point5);
        myTrackPointsList.add(point6);
        myTrackPointsList.add(point7);
        myTrackPointsList.add(point8);
        myTrackPointsList.add(point9);
        myTrackPointsList.add(point10);
        myTrackPointsList.add(point11);

        trackID = turningPoint(myTrackPointsList,trackID); //detect in which point is turning
        turnLR = turnLeftRight(myTrackPointsList, trackID);  //detect if turn is left or right


        locTurn.y = myTrackPointsList.get(trackID).y;
        locTurn.x = myTrackPointsList.get(trackID).x;
        locAfterTurn.y = myTrackPointsList.get(trackID + 1).y;
        locAfterTurn.x = myTrackPointsList.get(trackID + 1).x;
        turnPoint.add(locTurn);
        turnPoint.add(locAfterTurn);
    }


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

    public void addListenerToButton() {
        Button btnStart = (Button) findViewById(R.id.startBtn);
        Button btnStop = (Button) findViewById(R.id.stopBtn);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    simulator();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }


    private void simulator() {

        MyTrackPoints points1, points2, points3, points4, points5, points6, points7, points8, points9, points10, points11, points12, points13, points14, points15, points16, points17, points18, points19,
                points20, points21, points22, points23, points24, points25, points26, points27, points28, points29, points30, points31, points32, points33, points34, points35, points36, points37;
        points1 = new MyTrackPoints();
        points1.x = 46.302628;
        points1.y = 16.337324;
        points2 = new MyTrackPoints();
        points2.x = 46.302656;
        points2.y = 16.337249;
        points3 = new MyTrackPoints();
        points3.x = 46.302691;
        points3.y = 16.337157;
        points4 = new MyTrackPoints();
        points4.x = 46.302647;
        points4.y = 16.337125;
        points5 = new MyTrackPoints();
        points5.x = 46.302586;
        points5.y = 16.337103;
        points6 = new MyTrackPoints();
        points6.x = 46.302614;
        points6.y = 16.337022;
        points7 = new MyTrackPoints();
        points7.x = 46.302663;
        points7.y = 16.336931;
        points8 = new MyTrackPoints();
        points8.x = 46.302591;
        points8.y = 16.336882;
        points9 = new MyTrackPoints();
        points9.x = 46.302536;
        points9.y = 16.336846;
        points10 = new MyTrackPoints();
        points10.x = 46.302536;
        points10.y = 16.336755;
        points11 = new MyTrackPoints();
        points11.x = 46.302508;
        points11.y = 16.336672;
        points12 = new MyTrackPoints();
        points12.x = 46.302458;
        points12.y = 16.336735;
        points13 = new MyTrackPoints();
        points13.x = 46.302416;
        points13.y = 16.336821;
        points14 = new MyTrackPoints();
        points14.x = 46.302363;
        points14.y = 16.336778;
        points15 = new MyTrackPoints();
        points15.x = 46.30230;
        points15.y = 16.336719;
        points16 = new MyTrackPoints();
        points16.x = 46.302296;
        points16.y = 16.336801;
        points17 = new MyTrackPoints();
        points17.x = 46.302268;
        points17.y = 16.336891;
        points18 = new MyTrackPoints();
        points18.x = 46.302222;
        points18.y = 16.336839;
        points19 = new MyTrackPoints();
        points19.x = 46.302167;
        points19.y = 16.336789;
        points20 = new MyTrackPoints();
        points20.x = 46.302127;
        points20.y = 16.336873;
        points21 = new MyTrackPoints();
        points21.x = 46.302097;
        points21.y = 16.336940;
        points22 = new MyTrackPoints();
        points22.x = 46.302021;
        points22.y = 16.336943;
        points23 = new MyTrackPoints();
        points23.x = 46.301952;
        points23.y = 16.336909;
        points24 = new MyTrackPoints();
        points24.x = 46.301971;
        points24.y = 16.336846;
        points25 = new MyTrackPoints();
        points25.x = 46.301991;
        points25.y = 16.336785;
        points26 = new MyTrackPoints();
        points26.x = 46.301952;
        points26.y = 16.336771;
        points27 = new MyTrackPoints();
        points27.x = 46.301904;
        points27.y = 16.336735;
        points28 = new MyTrackPoints();
        points28.x = 46.301926;
        points28.y = 16.336677;
        points29 = new MyTrackPoints();
        points29.x = 46.301963;
        points29.y = 16.336607;
        points30 = new MyTrackPoints();
        points30.x = 46.301913;
        points30.y = 16.336562;
        points31 = new MyTrackPoints();
        points31.x = 46.301854;
        points31.y = 16.336548;
        points32 = new MyTrackPoints();
        points32.x = 46.301882;
        points32.y = 16.336478;
        points33 = new MyTrackPoints();
        points33.x = 46.301912;
        points33.y = 16.336374;
        points34 = new MyTrackPoints();
        points34.x = 46.301854;
        points34.y = 16.336311;
        points35 = new MyTrackPoints();
        points35.x = 46.301810;
        points35.y = 16.336264;
        points36 = new MyTrackPoints();
        points36.x = 46.301735;
        points36.y = 16.336178;
        points37 = new MyTrackPoints();
        points37.x = 46.301662;
        points37.y = 16.336151;

        myLocation = new ArrayList<>();
        myLocation.add(points1);
        myLocation.add(points2);
        myLocation.add(points3);
        myLocation.add(points4);
        myLocation.add(points5);
        myLocation.add(points6);
        myLocation.add(points7);
        myLocation.add(points8);
        myLocation.add(points9);
        myLocation.add(points10);
        myLocation.add(points11);
        myLocation.add(points12);
        myLocation.add(points13);
        myLocation.add(points14);
        myLocation.add(points15);
        myLocation.add(points16);
        myLocation.add(points17);
        myLocation.add(points18);
        myLocation.add(points19);
        myLocation.add(points20);
        myLocation.add(points21);
        myLocation.add(points22);
        myLocation.add(points23);
        myLocation.add(points24);
        myLocation.add(points25);
        myLocation.add(points26);
        myLocation.add(points27);
        myLocation.add(points28);
        myLocation.add(points29);
        myLocation.add(points30);
        myLocation.add(points31);
        myLocation.add(points32);
        myLocation.add(points33);
        myLocation.add(points34);
        myLocation.add(points35);
        myLocation.add(points36);
        myLocation.add(points37);

                        for (int pocetak = 0; pocetak < myLocation.size(); pocetak++) {
                            final Handler handler = new Handler();
                            final int finalPocetak = pocetak;
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                            try {
                                Thread.sleep(1000);
                                myLocHist = new ArrayList<>();
                                distanceFromPoint = new DistanceFromPoint();
                                Location l1 = new Location(LocationManager.GPS_PROVIDER);
                                Location l2 = new Location(LocationManager.GPS_PROVIDER);

                                int size = myTrackPointsList.size() - 1;
                                l1.setLongitude(myTrackPointsList.get(size).y);    //track end y
                                l1.setLatitude(myTrackPointsList.get(size).x);     //track end x
                                l2.setLongitude(locTurn.y);                        //turn y
                                l2.setLatitude(locTurn.x);                         //turn x

                                Location myL = new Location(LocationManager.GPS_PROVIDER);
                                myL.setLatitude(myLocation.get(finalPocetak).x);
                                myL.setLongitude(myLocation.get(finalPocetak).y);
                                float distanceToTrackEnd = distanceFromPoint.getDistance(l1, myL);
                                float distanceToTurn = distanceFromPoint.getDistance(l2, myL);
                                float distanceFromTurnToTrackEnd = distanceFromPoint.getDistance(l2, l1);


                                String dist = String.format("%.0f", distanceToTrackEnd);
                                TextView distanceToFinishTxt;
                                distanceToFinishTxt = (TextView) findViewById(R.id.distanceTxt);
                                distanceToFinishTxt.setText("Distance to finish: " + dist + "m");

                                String dist2 = String.format("%.0f", distanceToTurn);
                                TextView distanceToTurnTxt;
                                distanceToTurnTxt = (TextView) findViewById(R.id.txtDistanceToTurn);
                                distanceToTurnTxt.setText("Distance to turn: " + dist2 + "m");/////////////////////
                                ConvertingGpsCoordToXY convertingGpsCoordToXY = new ConvertingGpsCoordToXY();
                                if (distanceToTurn < 10 && (distanceToTrackEnd > distanceFromTurnToTrackEnd)) {
                                    for (int i = 0; i < myLocation.size(); i++) {
                                        MyTrackPoints converted = new MyTrackPoints();
                                        converted.x = convertingGpsCoordToXY.convertLon(myLocation.get(i).x);
                                        converted.y = convertingGpsCoordToXY.convertLat(myLocation.get(i).y);
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
                                myLocLeftSlopeSkii.add(myL);
                                for (int i = 0; i < myTrackPointsList.size(); i++) {
                                    Location loc =  new Location(LocationManager.GPS_PROVIDER);
                                    loc.setLongitude(myTrackPointsList.get(i).y);
                                    loc.setLatitude(myTrackPointsList.get(i).x);
                                    myLocLeftSlopeTrack.add(loc);
                                }

                                String mess = null;
                                int counter = 0;
                                UserLocationStatus userLocationStatus = new UserLocationStatus();
                             /*   counter = userLocationStatus.CalculatingIfUserLeftSlope(myLocLeftSlopeTrack, myL, counter);
                                if (counter > 4) {
                                    mess = "User has left the slope!";
                                }
*/
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
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                                   handler.postDelayed(this, 1000);
                                }
                            },1000);
                        }
    }




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

    @Override
    public void storeFetchedCoordinates(Coordinates coordinates) {

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
}
