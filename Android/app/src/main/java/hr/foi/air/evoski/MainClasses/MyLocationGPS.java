package hr.foi.air.evoski.MainClasses;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import hr.foi.air.evoski.FetchCoordinates.FileCoordinates;
import hr.foi.air.evoski.FetchCoordinates.FixedCoordinates;
import hr.foi.air.evoski.R;
import hr.foi.air.evoski.SmoothingAlgorithms.AlgorithmsInterface;
import hr.foi.air.evoski.SmoothingAlgorithms.MovingAverage;
import hr.foi.air.evoski.SmoothingAlgorithms.RealPointsAlgorithm;
import hr.foi.air.evoski.SmoothingAlgorithms.SmoothingAlgorithm;
import hr.foi.air.evoski.core.FetchTrackCoordinatesInterface;
import hr.foi.air.evoski.core.FetchTrackCoordinatesListener;
import hr.foi.air.evoski.core.MyTrackPoints;
import hr.foi.air.evoski.wsplugin.WebServerCoordinates;

public class MyLocationGPS extends Activity implements LocationListener {
    private LocationManager mLocMgr;
    int track, algID;
    ArrayList<MyTrackPoints> myLocations = null;
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
    List<Integer> turnPassed = new ArrayList<>();
    int numbOfTurns = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.test_con);
        myLocations = new ArrayList<>();
        myLocLeftSlopeSkii = new ArrayList<>();
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

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
        //geting coordinates
        FetchTrackCoordinatesInterface plugin;
        if (track == 1) {
            //fixed
            plugin = new FixedCoordinates();
        } else if (track == 2) {
            plugin = new FileCoordinates();
        } else {
            plugin = new WebServerCoordinates();
        }
        plugin.fetchTrackCoordinates(new FetchTrackCoordinatesListener() {
            @Override
            public void OnCoordinatesFetched(List<MyTrackPoints> points) {
                myTrackPointsList = points;
               searchingForTurns(myTrackPointsList);
            }
        }, this);


    }//OnCreate

    public void searchingForTurns(List<MyTrackPoints> myTrackPnts){
        for (int i = 0; i < myTrackPnts.size(); i++){
            turnPassed.add(0);
            if(myTrackPnts.get(i).turn != 0){
                numbOfTurns++;
            }
        }
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
            }
        mLocMgr.removeUpdates(this);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, MyLocationGPS.this);
        TextView displayMessage;
        displayMessage = (TextView) findViewById(R.id.messageDesplaytxt);
        displayMessage.setText("Searching...");
        TextView metarDisplayTxt;
        metarDisplayTxt = (TextView) findViewById(R.id.metarDisplay);
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
            }
        mLocMgr.removeUpdates(this);
        finish();
    }

    List<MyTrackPoints> smoothAlgList;
    List<MyTrackPoints> movAlgList;
    UserLocationStatus userLocationStatus = null;
    int counter = 0;

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
        TextView metarDisplayTxt, metarTxt, displayMessage, numbTurns;
        numbTurns = (TextView) findViewById(R.id.txtTurnsLeft);
        numbTurns.setText("Turns left: " + numbOfTurns);

        if (distanceToTrackEnd < 10) {
            Toast.makeText(MyLocationGPS.this, "FINISH", Toast.LENGTH_SHORT).show();
            metarDisplayTxt = (TextView) findViewById(R.id.metarDisplay);
            metarDisplayTxt.setGravity(Gravity.CENTER);
            metarDisplayTxt.setText("0m");

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
                }
            mLocMgr.removeUpdates(this);
        }else {
            String dist = String.format("%.0f", distanceToTrackEnd);
            progressBar.setVisibility(View.INVISIBLE);
            displayMessage = (TextView) findViewById(R.id.messageDesplaytxt);
            displayMessage.setText("Distance to finish");
            metarDisplayTxt = (TextView) findViewById(R.id.metarDisplay);
            metarDisplayTxt.setGravity(Gravity.CENTER);
            metarDisplayTxt.setText(dist+"m");

            numbTurns = (TextView) findViewById(R.id.txtTurnsLeft);
            numbTurns.setText("Turns left: " + numbOfTurns);
            String dist2 = String.format("%.0f", distanceToTurn);

            if (trackTurns.get(index) != 0 && distanceToTurn < 50 && (distanceToTrackEnd > distanceFromTurnToTrackEnd)) {
                displayMessage.setText("Distance to turn");
                metarDisplayTxt.setGravity(Gravity.RIGHT);
                metarDisplayTxt.setText(dist2+"m");
                if(turnPassed.get(index) != 1){
                    turnPassed.set(index, Integer.valueOf(1));
                    numbOfTurns--;
                }
                numbTurns = (TextView) findViewById(R.id.txtTurnsLeft);
                numbTurns.setText("Turns left: " + numbOfTurns);
                for (int i = 0; i < myLocations.size(); i++) {
                    MyTrackPoints converted = new MyTrackPoints();
                    converted.x = convertingGpsCoordToXY.convertLon(myLocations.get(i).x);
                    converted.y = convertingGpsCoordToXY.convertLat(myLocations.get(i).y);
                    myLocHist.add(converted);
                }
                AverageDirection averageDirection = new AverageDirection();
                double angle = 0;

                AlgorithmsInterface smoothingAlgorithm;
                List<MyTrackPoints> smoothedPoints;
                if (myLocHist.size() > 3) {
                    if (algID == 2) {
                        smoothingAlgorithm = new SmoothingAlgorithm();
                    } else if (algID == 3) {
                        smoothingAlgorithm = new MovingAverage();
                    } else { //algID bi trebao biti 1
                        smoothingAlgorithm = new RealPointsAlgorithm();
                    }
                    smoothedPoints = smoothingAlgorithm.getSmoothedPoints(myLocHist);
                    angle = averageDirection.AvgDirection(smoothedPoints, myTrackPointsList, index);
                }
                    setImage(angle, myTrackPointsList.get(index).turn);

            } else {
                ImageView imageView = (ImageView) findViewById(R.id.imgViewArrow);
                imageView.setImageDrawable(null);
            }
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

    public void setImage(double angle, int turnLR) {
        ImageView imageView = (ImageView) findViewById(R.id.imgViewArrow);
        if (angle >= 0 && angle <= 60 && turnLR > 0) {
            imageView.setImageResource(R.drawable.southeast);
        } else if (angle > 60 && angle < 120 && turnLR > 0) {
            imageView.setImageResource(R.drawable.east);
        } else if (angle >= 120 && angle <= 180 && turnLR > 0) {
            imageView.setImageResource(R.drawable.northeast);
        } else if (angle >= 120 && angle <= 180 && turnLR < 0) {
            imageView.setImageResource(R.drawable.northwest);
        } else if (angle > 60 && angle < 120 && turnLR < 0) {
            imageView.setImageResource(R.drawable.west);
        } else if (angle >= 0 && angle <= 60 && turnLR < 0) {
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