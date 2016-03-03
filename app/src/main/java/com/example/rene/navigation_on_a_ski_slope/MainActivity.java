package com.example.rene.navigation_on_a_ski_slope;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Location> trackLocations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();               // removes title bar from main activity
        setContentView(R.layout.activity_main);     // connecting main activity with xml file
        addListenerToButton();                // calling function for listening button

        //-------- UNESI STAZU OVDJE -------//
        Location ppoint1, ppoint2, ppoint3, ppoint4, ppoint5, ppoint6, ppoint7, ppoint8, ppoint9, ppoint10, ppoint11, ppoint12;
        ppoint1 = new Location("point1");
        ppoint1.setLatitude(48.219962);   //64.598517
        ppoint1.setLongitude(16.378555);
        ppoint2 = new Location("point2");
        ppoint2.setLatitude(48.220169);  //64.598853
        ppoint2.setLongitude(16.378684);
        ppoint3 = new Location("point3");
        ppoint3.setLatitude(48.220441);   //64.599232   collec
        ppoint3.setLongitude(16.378791);
        ppoint4 = new Location("point4");
        ppoint4.setLatitude(48.220412);   //64.598742
        ppoint4.setLongitude(16.378330);
        ppoint5 = new Location("point5");
        ppoint5.setLatitude(48.220376);   //64.598449
        ppoint5.setLongitude(16.378073);
        ppoint6 = new Location("point6");
        ppoint6.setLatitude(48.220383);   //64.598166  collect
        ppoint6.setLongitude(16.377783);
        ppoint7 = new Location("point7");
        ppoint7.setLatitude(48.220590);   //64.598555
        ppoint7.setLongitude(16.377965);
        ppoint8 = new Location("point8");
        ppoint8.setLatitude(48.220762);   //64.59886
        ppoint8.setLongitude(16.378104);
        ppoint9 = new Location("point9");
        ppoint9.setLatitude(48.220948);   //64.599234 right point collect
        ppoint9.setLongitude(16.378286);
        ppoint10 = new Location("point10");
        ppoint10.setLatitude(48.220898);   //64.598808
        ppoint10.setLongitude(16.377910);
        ppoint11 = new Location("point11");
        ppoint11.setLatitude(48.220877);   //64.598519  collect
        ppoint11.setLongitude(16.377642);
        ppoint12 = new Location("point12");
        ppoint12.setLatitude(48.221041);   //64.598780
        ppoint12.setLongitude(16.377739);


        trackLocations = new ArrayList<>();
        trackLocations.add(ppoint1);
        trackLocations.add(ppoint2);
        trackLocations.add(ppoint3);
        trackLocations.add(ppoint4);
        trackLocations.add(ppoint5);
        trackLocations.add(ppoint6);
        trackLocations.add(ppoint7);
        trackLocations.add(ppoint8);
        trackLocations.add(ppoint9);
        trackLocations.add(ppoint10);
        trackLocations.add(ppoint11);
        trackLocations.add(ppoint12);


        //----------------------------------//
    }


    public void addListenerToButton() {
        final Context context = getApplicationContext();        //this activity probaj sad
        Button btnR1 = (Button) findViewById(R.id.route1);  // connecting other button with xml
        Button btnR2 = (Button) findViewById(R.id.route2);  // connecting button with xml
        Button btnR3 = (Button) findViewById(R.id.route3);

        btnR1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MyLocationGPS.class);    //creating new intent and replacing the old one
                intent.putParcelableArrayListExtra("trackLoc", (ArrayList<? extends Parcelable>) trackLocations);
                startActivity(intent);
                // overridePendingTransition(R.anim.anim_to_right, R.anim.anim_to_left); //animation when inteint starts
                //ovaj dio
            }
        });

        btnR2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyLocationGPS.class);    //creating new intent and replacing the old one
                startActivity(intent);
                overridePendingTransition(R.anim.anim_to_right, R.anim.anim_to_left); //animation when inteint starts

            }
        });

        btnR3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyLocationGPS.class);    //creating new intent and replacing the old one
                startActivity(intent);
                overridePendingTransition(R.anim.anim_to_right, R.anim.anim_to_left); //animation when inteint starts

            }
        });
    }
}

