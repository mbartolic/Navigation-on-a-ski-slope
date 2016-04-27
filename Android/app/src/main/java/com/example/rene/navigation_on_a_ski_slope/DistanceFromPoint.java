package com.example.rene.navigation_on_a_ski_slope;

import android.location.Location;

public class DistanceFromPoint {

    private Location loc, myloc;

    public DistanceFromPoint() {
    }

    /**
     * Calculates distance between two GPS coordinates.
     * @param loc
     * @param myl
     * @return
     */
    public float getDistance(Location loc, Location myl) {
        this.loc = loc;
        this.myloc = myl;
       return  myloc.distanceTo(loc);
        }
    }
