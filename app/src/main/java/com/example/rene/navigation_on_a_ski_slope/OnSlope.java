package com.example.rene.navigation_on_a_ski_slope;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for determining if the skier is still on track or he left the track.
 * Created by Tomisav on 3.2.2016..
 */
public class OnSlope extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();               // removes title bar from main activity
        setContentView(R.layout.activity_on_route);
    }

    public int flag2 = 0;

    @Override
    protected void onStart() {
        super.onStart();

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


        DistanceFromPoint distanceFromPoint = new DistanceFromPoint();


        Location point1, point2, point3, point4, point5, point6, point7, point8, point9, point10, point11, point12;
        point1 = new Location("point1");
        point1.setLatitude(48.219962);   //64.598517
        point1.setLongitude(16.378555);
        point2 = new Location("point2");
        point2.setLatitude(48.220169);  //64.598853
        point2.setLongitude(16.378684);
        point3 = new Location("point3");
        point3.setLatitude(48.220441);   //64.599232   collec
        point3.setLongitude(16.378791);
        point4 = new Location("point4");
        point4.setLatitude(48.220412);   //64.598742
        point4.setLongitude(16.378330);
        point5 = new Location("point5");
        point5.setLatitude(48.220376);   //64.598449
        point5.setLongitude(16.378073);
        point6 = new Location("point6");
        point6.setLatitude(48.220383);   //64.598166  collect
        point6.setLongitude(16.377783);
        point7 = new Location("point7");
        point7.setLatitude(48.220590);   //64.598555
        point7.setLongitude(16.377965);
        point8 = new Location("point8");
        point8.setLatitude(48.220762);   //64.59886
        point8.setLongitude(16.378104);
        point9 = new Location("point9");
        point9.setLatitude(48.220948);   //64.599234 right point collect
        point9.setLongitude(16.378286);
        point10 = new Location("point10");
        point10.setLatitude(48.220898);   //64.598808
        point10.setLongitude(16.377910);
        point11 = new Location("point11");
        point11.setLatitude(48.220877);   //64.598519  collect
        point11.setLongitude(16.377642);
        point12 = new Location("point12");
        point12.setLatitude(48.221041);   //64.598780
        point12.setLongitude(16.377739);


        List<Location> trackLocations = new ArrayList<>();
        trackLocations.add(point1);
        trackLocations.add(point2);
        trackLocations.add(point3);
        trackLocations.add(point4);
        trackLocations.add(point5);
        trackLocations.add(point6);
        trackLocations.add(point7);
        trackLocations.add(point8);
        trackLocations.add(point9);
        trackLocations.add(point10);
        trackLocations.add(point11);
        trackLocations.add(point12);

        float distance = distanceFromPoint.getDistance(skiLocation, point1);

        int flag1 = 0;


        for (int i = 0; i < skiLocations.size(); i++) {
            // dodati dio koji simulira kretanje skijasa, dakle: for petlja koja ce ici kroz listu
            float min = 1000;
            for (int j = 0; j < trackLocations.size(); j++) {
                float distanceFromSlope = distanceFromPoint.getDistance(skiLocations.get(i), trackLocations.get(j));

                float currentDistance = distanceFromSlope;
                if (currentDistance < min) {
                    min = currentDistance;
                }

            }
            //dolje navedeni dio koda nesmije biti ukljucen u drugoj for petlji, ali mora biti u prvoj


            if (min <= 100) {
                flag1 = 0;
                Toast.makeText(getApplicationContext(), min + " metara",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                flag1++;
                Toast.makeText(getApplicationContext(), min + " metara",
                        Toast.LENGTH_SHORT).show();
                if (flag1 > 3) {
                    flag2++;
                    Toast.makeText(getApplicationContext(), " We can assume that user has left the slope",
                            Toast.LENGTH_LONG).show();
                    //napisat notice da se maknio sa staze
                }
            }



        }
    }
}
