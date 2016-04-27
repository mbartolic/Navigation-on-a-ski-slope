package com.example.rene.navigation_on_a_ski_slope;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Location> trackLocations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();               // removes title bar from main activity
        setContentView(R.layout.activity_main);     // connecting main activity with xml file
        addListenerToButton();                // calling function for listening button
        }


    public void addListenerToButton() {
        final Context context = getApplicationContext();        //this activity probaj sad
        Button btnR1 = (Button) findViewById(R.id.route1);  // connecting other button with xml
        Button btnR2 = (Button) findViewById(R.id.route2);  // connecting button with xml
        Button btnR3 = (Button) findViewById(R.id.route3);
        Button btnR4 = (Button) findViewById(R.id.simulateBtn);

        btnR1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MyLocationGPS.class);    //creating new intent and replacing the old one
                intent.putExtra("track",1);
                startActivity(intent);
                // overridePendingTransition(R.anim.anim_to_right, R.anim.anim_to_left); //animation when inteint starts
                //ovaj dio
            }
        });

        btnR2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyLocationGPS.class);    //creating new intent and replacing the old one
                intent.putExtra("track",2);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_to_right, R.anim.anim_to_left); //animation when inteint starts

            }
        });

        btnR3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyLocationGPS.class);    //creating new intent and replacing the old one
                intent.putExtra("track",3);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_to_right, R.anim.anim_to_left); //animation when inteint starts
            }
        });
        btnR4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Simulation.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_to_right, R.anim.anim_to_left);
            }
        });
    }
}

