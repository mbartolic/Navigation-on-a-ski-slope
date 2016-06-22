package hr.foi.air.evoski.MainClasses;
import android.location.Location;
import java.util.List;

/**
 * Created by Rene on 2.3.2016..
 */
public class UserLocationStatus {

    DistanceFromPoint distanceFromPoint = new DistanceFromPoint();
    public UserLocationStatus() { }

    public float slope_distance;
    public float user_distance;
    float distanceIJ, distanceXJ;

    public int CalculatingTrackPointIndex(List<Location> mypp, Location skiLocation, int index){
        if(index == mypp.size()-1){
            distanceIJ = 0;
            distanceXJ = distanceFromPoint.getDistance(skiLocation, mypp.get(index));
        }else {
            distanceIJ = distanceFromPoint.getDistance(mypp.get(index), mypp.get(index + 1));
            distanceXJ = distanceFromPoint.getDistance(skiLocation, mypp.get(index + 1));
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
                slope_distance = slope_distance+a;
            }
            return slope_distance;
        }
    }

    public float DistanceToNearestTrackPoint(List<Location> mypp, Location skiLocation, int near){
        user_distance = distanceFromPoint.getDistance(skiLocation, mypp.get(near));
        return user_distance;
    }



    public int CalculatingIfUserLeftSlope(List<Location> mypp, Location skiLocation, int flag1, int near) {
        double pointDistance;
        double min = distanceFromPoint.getDistance(skiLocation, mypp.get(near));

        if (mypp.size()-1== near){
            pointDistance = distanceFromPoint.getDistance(mypp.get(near), mypp.get(near - 1));
        }else {
            pointDistance = distanceFromPoint.getDistance(mypp.get(near), mypp.get(near + 1));
        }
        if (min <= pointDistance) {
            flag1 = 0;
        } else {
            flag1++;
        }

        return flag1;
    }
}
