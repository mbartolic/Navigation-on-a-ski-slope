package com.example.rene.navigation_on_a_ski_slope;

import android.location.Location;

import java.util.List;

/**
 * Created by Rene on 2.3.2016..
 */
public class UserLocationStatus {

    DistanceFromPoint distanceFromPoint = new DistanceFromPoint();
    public UserLocationStatus() { }


    public float slope_distance;   // length of whole trock, later it becomes distance from track end to nearest track point
    public float user_distance;    // distace from user to nearest track point
    float points_distance;  // distance between two nearest track points
    int nearest =0;  // list index, has value of nearest track point index
    float distance;




    public int CalculatingTrackPointIndex(List<Location> mypp, Location
            skiLocation, int index){
        if(index == mypp.size()-1){
            float distanceIJ = 0;
            float distanceXJ = distanceFromPoint.getDistance(skiLocation, mypp.get(index));
        }else {
            float distanceIJ = distanceFromPoint.getDistance(mypp.get(index),
                    mypp.get(index + 1));
            float distanceXJ = distanceFromPoint.getDistance(skiLocation,
                    mypp.get(index + 1));
            if (distanceIJ > distanceXJ) {
                index++;
            }
        }


        return index;
    }

    public float DistanceToTrackEnd(List<Location> mypp, int near){
        slope_distance =0;
        if (near == mypp.size()-1){
            return  slope_distance;
        }else {
            for (int i = near; i < mypp.size() - 1; i++) {
                float a = distanceFromPoint.getDistance(mypp.get(i),
                        mypp.get(i + 1));
                slope_distance = +a;     //
            }
            return slope_distance;
        }

    }

    public float DistanceToNearestTrackPoint(List<Location> mypp, Location
            skiLocation, int near){
        user_distance = distanceFromPoint.getDistance(skiLocation,
                mypp.get(near));
        return user_distance;
    }


    /*
    //trackLocations
    public int CalculatingIfUserLeftSlope(List<Location> mypp, Location
skiLocation, int flag1) {


        for (int i=nearest; i<mypp.size()-1;i++){
            float a= distanceFromPoint.getDistance(mypp.get(i),
mypp.get(i+1));
            slope_distance =+ a;     //
        }

       //svaki put kad promjeni lokaciju
              //index i tocke = 0
              //Uii udaljenost od i do i+1   ( 0 do 1)
              //Uxi udaljenost od x(skijas) do i+1 (x do 1)
              if(Uii > Uxi)
              {
              i++;
              }





        user_distance = distanceFromPoint.getDistance(skiLocation,
mypp.get(nearest));
        distance = slope_distance + user_distance;
        points_distance = distanceFromPoint.getDistance(mypp.get(nearest),
mypp.get(nearest + 1));

        if (user_distance > points_distance){
            flag1++;
        }else {
            flag1 =0;
        }


        float p1 = user_distance + points_distance ;
        float p2 = distanceFromPoint.getDistance(skiLocation,
mypp.get(nearest + 1));
        if (p1 < p2){
            slope_distance = slope_distance - points_distance;
            nearest++;
        }
        /*
        float min = 1000;
        for (int j = 0; j < trackLocations.size(); j++) {
            float distanceFromSlope =
distanceFromPoint.getDistance(skiLocation,
trackLocations.get(j));

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
    } */
}
