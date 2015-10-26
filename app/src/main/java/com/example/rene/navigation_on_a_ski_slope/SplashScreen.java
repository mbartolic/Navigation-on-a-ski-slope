package com.example.rene.navigation_on_a_ski_slope;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;



public class SplashScreen extends ActionBarActivity{

    int slpWait = 3000;         //waiting, time when splashscreen is shown just definition

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();                           //hiding title bar from spalh screen
        setContentView(R.layout.activity_splash_screen);        //connecting with xml file

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {                       //waiting for slpWait
                Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);  //changing intent between splash sccreen and main act
                SplashScreen.this.startActivity(mainIntent);        // starting main activity
                overridePendingTransition(R.anim.anim_to_down, R.anim.anim_to_up);  //animation when intent starts
                SplashScreen.this.finish();                     //closing intent

            }
        },slpWait);     //
    }

}
