package com.example.rene.navigation_on_a_ski_slope;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.activeandroid.ActiveAndroid;
import com.example.db.CoordinatesDB;
import com.example.model.Coordinates;
import com.example.mvp.presenter.CoordinatesPresenter;
import com.example.mvp.presenter.impl.CoordinatesPresenterImpl;
import com.example.mvp.view.CoordiantesView;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CoordiantesView{

    //Objekt klase koja implementira sucelje CoordinatesPresenter
    CoordinatesPresenter coordinatesPresenter;
    private static final String LOG_KEY = "coordianes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();               // removes title bar from main activity
        setContentView(R.layout.activity_main);     // connecting main activity with xml file
        addListenerToButton();                // calling function for listening button

        //Inicijalizacija klase koja implementira sucelje CoordinatesPresenter
        coordinatesPresenter = new CoordinatesPresenterImpl(this);
        //Dohvacanje koordinata preko sucelja CoordinatesPresenter
        coordinatesPresenter.getData();
    }


   public void addListenerToButton() {
       final Context context = this;        //this activity
       Button btnrout = (Button)findViewById(R.id.routsBttnID);  // connecting button with xml
       Button btnloc = (Button)findViewById(R.id.MyLocationBtnID);  // connecting other button with xml

       btnrout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(context, Routs.class);    //creating new intent and replacing the old one
               startActivity(intent);                               // starting intent
               overridePendingTransition(R.anim.anim_to_right, R.anim.anim_to_left); //animation when inteint starts
           }
       });

       btnloc.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v){
                Intent intent =  new Intent(context, MyLocationGPS.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_to_right, R.anim.anim_to_left);
       }
       });
   }

    @Override
    public void storeFetchedCoordinates(List<Coordinates> coordinates) {
        /*try{
            for(Coordinates coordinate : coordinates)    {
                // Log.e(LOG_KEY, "Image: "  + coordinate.getImage() + " lon: " + coordinate.getLon() + " lat: " + coordinate.getLat());
                CoordinatesDB coordDB = new CoordinatesDB();
                coordDB.setImage(coordinate.getImage());
                coordDB.setLat(coordinate.getLat());
                coordDB.setLon(coordinate.getLon());
                coordDB.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        }finally {
            ActiveAndroid.endTransaction();
        }*/


    }
}


