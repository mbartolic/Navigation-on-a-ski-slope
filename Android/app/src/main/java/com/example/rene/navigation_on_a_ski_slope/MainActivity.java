package com.example.rene.navigation_on_a_ski_slope;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.model.Coordinates;
import com.example.model.Instruction;
import com.example.mvp.presenter.CoordinatesPresenter;
import com.example.mvp.presenter.impl.CoordinatesPresenterImpl;
import com.example.mvp.view.CoordiantesView;
import com.example.utils.Constants;

import java.io.File;
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
        final Context context = getApplicationContext();
        Button btnR1 = (Button) findViewById(R.id.route1);
        Button btnR2 = (Button) findViewById(R.id.localFile);
        Button btnR3 = (Button) findViewById(R.id.webTrack);
        Button btnOpt = (Button) findViewById(R.id.optionsBtn);

        btnR1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyLocationGPS.class);
                intent.putExtra("track",1);
                intent.putExtra("algID", algID);
                finish();
                startActivity(intent);
                overridePendingTransition(R.anim.anim_to_right, R.anim.anim_to_left);

            }
        });

        btnR2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyLocationGPS.class);
                String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + File.separator + "TrackRoute.txt";
                File file = new File(path);
                if(file.exists()){
                intent.putExtra("track",2);
                intent.putExtra("algID", algID);
                finish();
                startActivity(intent);
                overridePendingTransition(R.anim.anim_to_right, R.anim.anim_to_left);
                }else{
                    Toast.makeText(MainActivity.this, "TrackRoute.txt cannot be found!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnR3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyLocationGPS.class);
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                if (networkInfo == null) {
                    Toast.makeText(MainActivity.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                } else if (sorX == null || sorY == null || desX == null || desY == null){
                    Toast.makeText(MainActivity.this, "Type coordinates in Options",Toast.LENGTH_SHORT).show();
                }else{
                    intent.putExtra("track", 3);
                    intent.putExtra("algID", algID);
                    intent.putExtra("sX", sorX);
                    intent.putExtra("sY", sorY);
                    intent.putExtra("dX", desX);
                    intent.putExtra("dY", desY);
                    finish();
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_to_right, R.anim.anim_to_left);
                }
            }
        });

        btnOpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Options.class);
                finish();
                startActivity(intent);
                overridePendingTransition(R.anim.anim_to_right, R.anim.anim_to_left);
            }
        });
    }
}

