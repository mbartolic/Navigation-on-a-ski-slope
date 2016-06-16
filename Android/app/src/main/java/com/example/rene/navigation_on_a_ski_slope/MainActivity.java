package com.example.rene.navigation_on_a_ski_slope;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.model.Coordinates;
import com.example.model.Instruction;
import com.example.mvp.presenter.CoordinatesPresenter;
import com.example.mvp.presenter.impl.CoordinatesPresenterImpl;
import com.example.mvp.view.CoordiantesView;
import com.example.utils.Constants;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    int algID = 1;
    String sorX,sorY,desX,desY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();               // removes title bar from main activity
        setContentView(R.layout.activity_main);     // connecting main activity with xml file
        addListenerToButton();                // calling function for listening button
        Bundle bundle = getIntent().getExtras();

        if (bundle == null) {
            algID = 1;
            sorX = null;
            sorY = null;
            desX = null;
            desY = null;
        } else {
            algID = getIntent().getExtras().getInt("alg");
            sorX = getIntent().getExtras().getString("sX");
            sorY = getIntent().getExtras().getString("sY");
            desX = getIntent().getExtras().getString("dX");
            desY = getIntent().getExtras().getString("dY");
        }
    }

    public void addListenerToButton() {
        final Context context = getApplicationContext();        //this activity probaj sad
        Button btnR1 = (Button) findViewById(R.id.route1);  // connecting other button with xml
        Button btnR2 = (Button) findViewById(R.id.localFile);  // connecting button with xml
        Button btnR3 = (Button) findViewById(R.id.webTrack);
        Button btnOpt = (Button) findViewById(R.id.optionsBtn);

        btnR1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyLocationGPS.class);    //creating new intent and replacing the old one
                intent.putExtra("track",1);
                intent.putExtra("algID", algID);
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
                intent.putExtra("algID", algID);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_to_right, R.anim.anim_to_left); //animation when inteint starts
            }
        });

        btnR3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyLocationGPS.class);    //creating new intent and replacing the old one
                intent.putExtra("track",3);
                intent.putExtra("algID", algID);

//                intent.putExtra("MyTrackPoints", a);

                overridePendingTransition(R.anim.anim_to_right, R.anim.anim_to_left); //animation when inteint starts
                intent.putExtra("sX",sorX);
                intent.putExtra("sY",sorY);
                intent.putExtra("dX",desX);
                intent.putExtra("dY",desY);
                startActivity(intent);
            }
        });

        btnOpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Options.class);    //creating new intent and replacing the old one
                finish();
                startActivity(intent);
                overridePendingTransition(R.anim.anim_to_right, R.anim.anim_to_left); //animation when inteint starts
            }
        });
    }
}

