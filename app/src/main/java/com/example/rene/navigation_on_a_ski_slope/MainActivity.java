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
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        addListenerToButtonRoutes();
    }


   public void addListenerToButtonRoutes() {
       final Context context = this;        //this activity
       Button btnrout = (Button)findViewById(R.id.routsBttnID);
       Button btnloc = (Button)findViewById(R.id.MyLocationBtnID);

       btnrout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(context, Routs.class);
               startActivity(intent);
               overridePendingTransition(R.anim.anim_to_right, R.anim.anim_to_left); //animation
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


