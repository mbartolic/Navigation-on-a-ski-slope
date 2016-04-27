package com.example.rene.navigation_on_a_ski_slope;

import android.location.Location;

import java.util.List;

/**
 * Created by Rene on 2.3.2016..
 */
public class UserLocationStatus {

    DistanceFromPoint distanceFromPoint = new DistanceFromPoint();


    public UserLocationStatus() { }

    public int CalculatingIfUserLeftSlope(List<Location> trackLocations, Location skiLocation, int flag1) {



        float min = 1000;
        for (int j = 0; j < trackLocations.size(); j++) {
            float distanceFromSlope = distanceFromPoint.getDistance(skiLocation, trackLocations.get(j));

            float currentDistance = distanceFromSlope;
            if (currentDistance < min) {
                min = currentDistance;
            }
        }

        if (min <= 100) {
            flag1 = 0;
        } else {
            flag1++;
        }



        return flag1;
    }
}
