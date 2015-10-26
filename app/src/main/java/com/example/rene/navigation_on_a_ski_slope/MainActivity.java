package com.example.rene.navigation_on_a_ski_slope;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();               // removes title bar from main activity
        setContentView(R.layout.activity_main);     // connecting main activity with xml file
        addListenerToButton();                // calling function for listening button
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
}


