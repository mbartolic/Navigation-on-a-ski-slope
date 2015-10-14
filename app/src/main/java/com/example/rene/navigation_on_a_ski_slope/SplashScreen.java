package com.example.rene.navigation_on_a_ski_slope;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;



public class SplashScreen extends ActionBarActivity{

    int slpWait = 3000;         //cekanje

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);        //poziv xml

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                SplashScreen.this.startActivity(mainIntent);
                overridePendingTransition(R.anim.anim_to_down, R.anim.anim_to_up);
                SplashScreen.this.finish();     //zatvaranje ekrana

            }
        },slpWait);
    }

}
