package com.example.rene.MainClasses;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import Algorithms.AlgorithmsInterface;
import Algorithms.MovingAverage;
import Algorithms.SmoothingAlgorithm;

public class MyLocationGPS extends Activity implements LocationListener, CoordiantesView {
    private LocationManager mLocMgr;
    CoordinatesPresenter coordinatesPresenter;
    int track, algID;
    ArrayList<MyTrackPoints> myLocations = null;
    List<MyTrackPoints> turnPoint = new ArrayList<>();
    MyTrackPoints locTurn = new MyTrackPoints();
    MyTrackPoints locAfterTurn = new MyTrackPoints();
    String turnLR;
    int trackID = 0;
    List<Location> myLocLeftSlopeTrack;
    List<Location> myLocLeftSlopeSkii;
    List<Integer> trackTurns;
    ProgressBar progressBar;
    Location myLocation;
    List<MyTrackPoints> myLocHist;
    MyTrackPoints point;
    DistanceFromPoint distanceFromPoint;
    int index = 0;
    List<MyTrackPoints> myTrackPointsList = null;
    public MyTrackPoints coord;

    /**
     * Stores fetched coordinates and signs from server.
     * @param coordinates
     */
    public void storeFetchedCoordinates(Coordinates coordinates) {
        myTrackPointsList = new ArrayList<>();
        if (track == 3) {
            for (Instruction instruction : coordinates.getPaths().get(0).getInstructions()) {

                coord = new MyTrackPoints();
                coord.y = (Double.parseDouble(instruction.getCoordinate().get(0)));
                coord.x = (Double.parseDouble(instruction.getCoordinate().get(1)));
                coord.turn = instruction.getSign();
                myTrackPointsList.add(coord);
            }
            trackID = turningPoint(myTrackPointsList, trackID); //detect in which point is turning
            turnLR = turnLeftRight(myTrackPointsList, trackID);  //detect if turn is left or right
            locTurn.y = myTrackPointsList.get(trackID).y;
            locTurn.x = myTrackPointsList.get(trackID).x;
            if(trackID + 1 < myTrackPointsList.size()) {
                locAfterTurn.y = myTrackPointsList.get(trackID + 1).y;
                locAfterTurn.x = myTrackPointsList.get(trackID + 1).x;
                turnPoint.add(locTurn);
                turnPoint.add(locAfterTurn);
            }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_con);
        myLocations = new ArrayList<>();
        myLocLeftSlopeSkii = new ArrayList<>();
        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
        coordinatesPresenter = new CoordinatesPresenterImpl(this);
        mLocMgr = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle == null) {
                track = 0;
                algID = 1;
            } else {
                track = getIntent().getExtras().getInt("track");
                algID = getIntent().getExtras().getInt("alg");
            }
        }

        if (track == 1) {
            MyTrackPoints point1, point2, point3, point4;
            point1 = new MyTrackPoints();
            point1.y =  46.308044;
            point1.x =  16.345183;
            point1.turn = 0;
            point2 = new MyTrackPoints();
            point2.y =  46.307642;
            point2.x =  16.343479;
            point2.turn = 2;
            point3 = new MyTrackPoints();
            point3.y =  46.308439;
            point3.x =  16.342981;
            point3.turn = -2;
            point4 = new MyTrackPoints();
            point4.y = 46.307944;
            point4.x =  16.340941 ;
            point4.turn = 0;

            myTrackPointsList = new ArrayList<>();
            myTrackPointsList.add(point1);
            myTrackPointsList.add(point2);
            myTrackPointsList.add(point3);
            myTrackPointsList.add(point4);

        } else if (track == 2) {
                LocalFileRead localFileRead = new LocalFileRead();
                myTrackPointsList = localFileRead.readFile();
        } else if (track == 3) {
            Bundle bundle = getIntent().getExtras();
            if (bundle == null) {} else {
                String sorX = getIntent().getExtras().getString("sX");
                String sorY = getIntent().getExtras().getString("sY");
                String desX = getIntent().getExtras().getString("dX");
                String desY = getIntent().getExtras().getString("dY");
                String sourcePoints = sorY + "," + sorX;
                String destinationPoints = desY + "," + desX;
                coordinatesPresenter.getData(sourcePoints, destinationPoints, Constants.CAR_ROUTE_TYPE, Constants.VOICE_INSTRUCTIONS, Constants.LANGUAGE);
                Toast.makeText(MyLocationGPS.this, "Coordinates fetched",Toast.LENGTH_SHORT).show();
                }
            }
        if(track == 1 || track == 2){
            trackID = turningPoint(myTrackPointsList, trackID); //detect in which point is turning
            turnLR = turnLeftRight(myTrackPointsList, trackID);  //detect if turn is left or right
            locTurn.y = myTrackPointsList.get(trackID).y;
            locTurn.x = myTrackPointsList.get(trackID).x;
            locAfterTurn.y = myTrackPointsList.get(trackID+1).y;
            locAfterTurn.x = myTrackPointsList.get(trackID+1).x;
            turnPoint.add(locTurn);
            turnPoint.add(locAfterTurn);
        }
    }

    /**
     * Detects whether turn is left or right.
     * @param myTrPointList
     * @param turnID
     * @return
     */
    public String turnLeftRight(List<MyTrackPoints> myTrPointList, int turnID) {
        String turn = null;
        if (myTrPointList.get(turnID).turn > 0) {
            turn = "right";
        } else if (myTrPointList.get(turnID).turn < 0) {
            turn = "left";
        }
        return turn;
    }

    public int turningPoint(List<MyTrackPoints> myTrPointList, int turnID) {
        int j = 0;
        for (int i = turnID + 1; i < myTrPointList.size(); i++) {
            if (myTrPointList.get(i).turn != 0) {
                j = i;
                break;
            }
        }
        return j;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (mLocMgr != null)
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }mLocMgr.removeUpdates(this);
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLocMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, MyLocationGPS.this);
        TextView displayMessage;
        displayMessage=(TextView) findViewById(R.id.messageDesplaytxt);
        displayMessage.setText("Searching...");
        TextView metarDisplayTxt;
        metarDisplayTxt=(TextView) findViewById(R.id.metarDisplay);
        metarDisplayTxt.setText("GPS");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mLocMgr != null)
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }mLocMgr.removeUpdates(this);
        finish();
    }

    List<MyTrackPoints> smoothAlgList;
    List<MyTrackPoints> movAlgList;
    UserLocationStatus userLocationStatus = null;
    int counter=0;

    @Override
    public void onLocationChanged(Location location) {
        ConvertingGpsCoordToXY convertingGpsCoordToXY = new ConvertingGpsCoordToXY();
        smoothAlgList = new ArrayList<>();
        movAlgList = new ArrayList<>();
        myLocation = new Location(location);
        myLocHist = new ArrayList<>();
        distanceFromPoint = new DistanceFromPoint();
        point = new MyTrackPoints();
        point.x = location.getLongitude();
        point.y = location.getLatitude();
        myLocations.add(point);
        myLocLeftSlopeTrack = new ArrayList<>();
        trackTurns = new ArrayList<>();
        myLocLeftSlopeSkii.add(location);
        for (int i = 0; i < myTrackPointsList.size(); i++) {
            Location loc = new Location(location);
            loc.setLongitude(myTrackPointsList.get(i).x);
            loc.setLatitude(myTrackPointsList.get(i).y);
            trackTurns.add(myTrackPointsList.get(i).turn);
            myLocLeftSlopeTrack.add(loc);
        }

        userLocationStatus = new UserLocationStatus();
        index = userLocationStatus.CalculatingTrackPointIndex(myLocLeftSlopeTrack, myLocation, index);
        float distanceToTurn = userLocationStatus.DistanceToNearestTrackPoint(myLocLeftSlopeTrack, myLocation, index);
        float distanceFromTurnToTrackEnd = userLocationStatus.DistanceToTrackEnd(myLocLeftSlopeTrack, index);
        float distanceToTrackEnd = distanceToTurn + distanceFromTurnToTrackEnd;

        if (distanceToTrackEnd < 10) {
            Toast.makeText(MyLocationGPS.this, "FINISH",Toast.LENGTH_SHORT).show();

            if (mLocMgr != null)
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }mLocMgr.removeUpdates(this);
        }

        String dist = String.format("%.0f", distanceToTrackEnd);
        TextView metarDisplayTxt, metarTxt, displayMessage;
        progressBar.setVisibility(View.INVISIBLE);
        displayMessage=(TextView) findViewById(R.id.messageDesplaytxt);
        displayMessage.setText("Distance to finish: ");
        metarDisplayTxt=(TextView) findViewById(R.id.metarDisplay);
        metarDisplayTxt.setText(dist);
        metarTxt=(TextView) findViewById(R.id.metric);
        metarTxt.setText("m");
        String dist2 = String.format("%.0f", distanceToTurn);
        if (trackTurns.get(index) != 0) {
            displayMessage.setText("Distance to turn: ");
            metarDisplayTxt.setText(dist2);
            if (distanceToTurn < 30&& (distanceToTrackEnd > distanceFromTurnToTrackEnd)) {
                for (int i = 0; i < myLocations.size(); i++) {
                    MyTrackPoints converted = new MyTrackPoints();
                    converted.x = convertingGpsCoordToXY.convertLon(myLocations.get(i).x);
                    converted.y = convertingGpsCoordToXY.convertLat(myLocations.get(i).y);
                    myLocHist.add(converted);
                }

                AverageDirection averageDirection = new AverageDirection();
                double angle = 0;

                AlgorithmsInterface smoothingAlgorithm = new SmoothingAlgorithm();
                AlgorithmsInterface getSmoothingAlgorithm = new MovingAverage();
                List<MyTrackPoints> smoothRealList;
                if (myLocHist.size() > 3) {
                    if (algID == 2) {
                        smoothRealList = smoothingAlgorithm.Smooth(myLocHist);
                        angle = averageDirection.AvgDirection(smoothRealList, turnPoint);
                    } else if (algID == 3) {
                        smoothRealList = getSmoothingAlgorithm.MovAverage(myLocHist);
                        angle = averageDirection.AvgDirection(smoothRealList, turnPoint);
                    } else if (algID == 1) {
                        angle = averageDirection.AvgDirection(myLocHist, turnPoint);
                    }
                }
                if (turnLR != null && myLocHist.size() > 2) {
                    setImage(angle, turnLR);
                }
            }else{
                ImageView imageView = (ImageView) findViewById(R.id.imgViewArrow);
                imageView.setImageDrawable(null);
            }
        }else{
            ImageView imageView = (ImageView) findViewById(R.id.imgViewArrow);
            imageView.setImageDrawable(null);
        }

            String mess = null;
            UserLocationStatus userLocationStatus = new UserLocationStatus();
            counter = userLocationStatus.CalculatingIfUserLeftSlope(myLocLeftSlopeTrack, myLocation, counter, index);
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

    /**
     * Arrow display on screen.
     * @param angle
     * @param turnLR
     */
    public void setImage(double angle, String turnLR) {
        ImageView imageView = (ImageView) findViewById(R.id.imgViewArrow);
        if (angle >= 0 && angle <= 60 && turnLR.equalsIgnoreCase("right")) {
            imageView.setImageResource(R.drawable.southeast);
        } else if (angle > 60 && angle < 120 && turnLR.equalsIgnoreCase("right")) {
            imageView.setImageResource(R.drawable.east);
        } else if (angle >= 120 && angle <= 180 && turnLR.equalsIgnoreCase("right")) {
            imageView.setImageResource(R.drawable.northeast);
        } else if (angle >= 120 && angle <= 180 && turnLR.equalsIgnoreCase("left")) {
            imageView.setImageResource(R.drawable.northwest);
        } else if (angle > 60 && angle < 120 && turnLR.equalsIgnoreCase("left")) {
            imageView.setImageResource(R.drawable.west);
        } else if (angle >= 0 && angle <= 60 && turnLR.equalsIgnoreCase("left")) {
            imageView.setImageResource(R.drawable.southwest);
        } else {
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