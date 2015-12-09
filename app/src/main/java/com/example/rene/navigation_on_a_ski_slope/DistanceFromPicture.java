package com.example.rene.navigation_on_a_ski_slope;


import android.location.Location;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

public class DistanceFromPicture {

    private  Location loc, myloc;
    float distance;
    String vani, unutra;

    public DistanceFromPicture() {

    }

    public String getDistance(Location loc, Location myl) {
        this.loc = loc;
        this.myloc = myl;
        distance = myloc.distanceTo(loc);
        if(distance < 100000){
             return unutra = "SLIKA!!!";
        }else{
            return vani = "Nema slike!";
        }
    }
}
