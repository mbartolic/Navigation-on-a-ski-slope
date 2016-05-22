package com.example.rene.navigation_on_a_ski_slope;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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
    private LocationListener locationListener;
    CoordinatesPresenter coordinatesPresenter;
    private static final String LOG_KEY = "coordianes";
    int track;
    List<MyTrackPoints> myTrackPointsList = null;
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
    TextView txtServerCoord;
    DistanceFromPoint distanceFromPoint;
      final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_con);
        addListenerToButton();
        myLocations = new ArrayList<>();
        myLocLeftSlopeSkii = new ArrayList<>();
        txtServerCoord = (TextView) findViewById(R.id.listViewServerCoord);


        //Inicijalizacija klase koja implementira sucelje CoordinatesPresenter
        coordinatesPresenter = new CoordinatesPresenterImpl(this);
        //Dohvacanje koordinata preko sucelja CoordinatesPresenter
        coordinatesPresenter.getData(Constants.SOURCE_POINTS, Constants.DESTINATION_POINTS, Constants.CAR_ROUTE_TYPE, Constants.VOICE_INSTRUCTIONS, Constants.LANGUAGE);
        mLocMgr = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            if(bundle == null) {
                track = 0;
            } else {
                track = getIntent().getExtras().getInt("track");
            }
        }

        if(track == 1){
            MyTrackPoints point1, point2, point3, point4, point5, point6, point7, point8, point9;
            point1 = new MyTrackPoints();
            point1.x =  46.306509;
            point1.y =  16.344788;
            point1.turn = 0;
            point2 = new MyTrackPoints();
            point2.x = 46.307028;
            point2.y = 16.344563;
            point2.turn = 2;
            point3 = new MyTrackPoints();
            point3.x = 46.307339;
            point3.y = 16.345904;
            point3.turn = 0;
            point4 = new MyTrackPoints();
            point4.x = 46.307791;
            point4.y = 16.347299;
            point4.turn = -2;
            point5 = new MyTrackPoints();
            point5.x =  46.308525;
            point5.y =  16.347181;        //turning point
            point5.turn = -2;
            point6 = new MyTrackPoints();
            point6.x =  46.307680;
            point6.y =  16.343619;
            point6.turn = 2;
            point7 = new MyTrackPoints();
            point7.x =  46.308391;
            point7.y =  16.343029;
            point7.turn = 2;
            point8 = new MyTrackPoints();
            point8.x =  46.308628;
            point8.y =  16.343737;
            point8.turn = -2;
            point9 = new MyTrackPoints();
            point9.x =  46.309584;
            point9.y =  16.343329;
            point9.turn = 0;

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


        }else if (track == 2){
            MyTrackPoints point1, point2, point3, point4, point5, point6, point7;
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
            point5.y =  16.336893;        //turning point
            point5.turn = 0;
            point6 = new MyTrackPoints();
            point6.x =  46.301974;
            point6.y =  16.336925;
            point6.turn = 1;
            point7 = new MyTrackPoints();
            point7.x = 46.301944;
            point7.y = 16.336783;
            point7.turn= 0;

            myTrackPointsList = new ArrayList<>();
            myTrackPointsList.add(point1);
            myTrackPointsList.add(point2);
            myTrackPointsList.add(point3);
            myTrackPointsList.add(point4);
            myTrackPointsList.add(point5);
            myTrackPointsList.add(point6);
            myTrackPointsList.add(point7);


        }else if (track == 3) {
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

    public void storeFetchedCoordinates(Coordinates coordinates) {
        MyTrackPoints coord;


        for (Instruction instruction : coordinates.getPaths().get(0).getInstructions()) {
            coord=new MyTrackPoints();
            coord.x=(Double.parseDouble(instruction.getCoordinate().get(0)));
            coord.y=(Double.parseDouble(instruction.getCoordinate().get(1)));
            coord.turn = instruction.getSign();
            mypp.add(coord);
        }
        ispisKoordinata(mypp);
    }


    String var1, var2, var3;
    StringBuilder sb = new StringBuilder();
    public void ispisKoordinata(List<MyTrackPoints> myp) {
        txtServerCoord = (TextView) findViewById(R.id.listViewServerCoord);
         for (int i = 0; i < myp.size(); i++) {
             var1 = String.valueOf(myp.get(i).x);
             var2 = String.valueOf(myp.get(i).y);
             var3 = String.valueOf(myp.get(i).turn);
             sb.append("X: ");      sb.append(var1);    sb.append(", ");
             sb.append(" Y: ");     sb.append(var2);    sb.append(", ");
             sb.append(" Sign: ");  sb.append(var3);    sb.append("\n");
         }
        txtServerCoord.setText(sb.toString());
    }

    public void addListenerToButton() {
        Button btnStart = (Button) findViewById(R.id.startBtn);
        Button btnStop = (Button) findViewById(R.id.stopBtn);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mLocMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0,MyLocationGPS.this);
                txtServerCoord.setText("");

            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mLocMgr !=null)
                    mLocMgr.removeUpdates(MyLocationGPS.this);
            }
        });
    }


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
    int counter=0;


    @Override
    public void onLocationChanged(Location location) {
        ConvertingGpsCoordToXY convertingGpsCoordToXY = new ConvertingGpsCoordToXY();

        myLocation = new Location(location);
        myLocHist = new ArrayList<>();
        distanceFromPoint = new DistanceFromPoint();
        Location l1 = new Location(LocationManager.GPS_PROVIDER);
        Location l2 = new Location(LocationManager.GPS_PROVIDER);

        int size = myTrackPointsList.size() - 1;
        l1.setLongitude(myTrackPointsList.get(size).y);    //track end y
        l1.setLatitude(myTrackPointsList.get(size).x);     //track end x
        l2.setLongitude(locTurn.y);                        //turn y
        l2.setLatitude(locTurn.x);                         //turn x

        float distanceToTrackEnd = distanceFromPoint.getDistance(l1,myLocation);
        float distanceToTurn = distanceFromPoint.getDistance(l2, myLocation);
        float distanceFromTurnToTrackEnd = distanceFromPoint.getDistance(l2, l1);

        point = new MyTrackPoints();
        point.x =  location.getLongitude();
        point.y =  location.getLatitude();
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
                if(turnLR != null) {
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
            }else{
                ImageView imageView = (ImageView) findViewById(R.id.imgViewArrow);
                imageView.setImageDrawable(null);
                //distanceToTurnTxt.setText("");
            }

        myLocLeftSlopeTrack =  new ArrayList<>();
        myLocLeftSlopeSkii.add(location);
        for(int i = 0; i < myTrackPointsList.size(); i++){
            Location loc = new Location(location);
            loc.setLongitude(myTrackPointsList.get(i).y);
            loc.setLatitude(myTrackPointsList.get(i).x);
            myLocLeftSlopeTrack.add(loc);
        }

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
        if(mess != null) {
            leftSlope.setText("" + mess);
            leftSlope.startAnimation(anim);
        }else{
            leftSlope.setText("");
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
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}


    }
