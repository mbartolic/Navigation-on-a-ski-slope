package com.example.rene.navigation_on_a_ski_slope;

import android.location.Location;

public class DistanceFromPicture {

    private Location loc, myloc;

    public DistanceFromPicture() {
    }

    public float getDistance(Location loc, Location myl) {
        this.loc = loc;
        this.myloc = myl;
       return  myloc.distanceTo(loc);
        }
    }
