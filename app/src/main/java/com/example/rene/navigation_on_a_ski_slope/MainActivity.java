package com.example.rene.navigation_on_a_ski_slope;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.model.Coordinates;
import com.example.mvp.presenter.CoordinatesPresenter;
import com.example.mvp.presenter.impl.CoordinatesPresenterImpl;
import com.example.mvp.view.CoordiantesView;
import com.example.utils.Constants;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();               // removes title bar from main activity
        setContentView(R.layout.activity_main);     // connecting main activity with xml file
        addListenerToButton();                // calling function for listening button
    }


    public void addListenerToButton() {
        final Context context = getApplicationContext();        //this activity probaj sad
        Button btnlo = (Button) findViewById(R.id.onrouteID);  // connecting other button with xml
        Button btnloc = (Button) findViewById(R.id.MyLocationBtnID);  // connecting button with xml


        btnlo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, OnSlope.class);    //creating new intent and replacing the old one
                startActivity(intent);
                // overridePendingTransition(R.anim.anim_to_right, R.anim.anim_to_left); //animation when inteint starts
                //ovaj dio
            }
        });

        btnloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyLocationGPS.class);    //creating new intent and replacing the old one
                startActivity(intent);
                 overridePendingTransition(R.anim.anim_to_right, R.anim.anim_to_left); //animation when inteint starts

            }
        });
    }

}

