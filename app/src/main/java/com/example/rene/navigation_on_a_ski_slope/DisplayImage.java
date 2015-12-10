package com.example.rene.navigation_on_a_ski_slope;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class DisplayImage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.picture);
    }



}
