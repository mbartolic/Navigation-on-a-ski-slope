package com.example.rene.navigation_on_a_ski_slope;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by Rene on 6.12.2015..
 */
public class NavigationAplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
