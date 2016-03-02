package com.example.rene.navigation_on_a_ski_slope;

import android.location.Location;

import java.util.List;

/**
 * Created by Rene on 2.3.2016..
 */
public class UserLocationStatus {

    DistanceFromPoint distanceFromPoint = new DistanceFromPoint();
    String message;

    public UserLocationStatus() { }

    public String CalculatingIfUserLeftSlope(List<Location> trackLocations,List<Location> skiLocations) {
        int flag1 = 0;
        for (int i = 0; i < skiLocations.size(); i++) {

            float min = 1000;
            for (int j = 0; j < trackLocations.size(); j++) {
                float distanceFromSlope = distanceFromPoint.getDistance(skiLocations.get(i), trackLocations.get(j));

                float currentDistance = distanceFromSlope;
                if (currentDistance < min) {
                    min = currentDistance;
                }
            }

            if (min <= 100) {
                flag1 = 0;
            } else {
                flag1++;
                if (flag1 > 3) {

                  message = " We can assume that user has left the slope";

                }
            }


        }
        return message;
    }
}
