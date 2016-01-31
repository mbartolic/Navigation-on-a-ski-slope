package com.example.rene.navigation_on_a_ski_slope;

import android.graphics.Point;
import android.graphics.PointF;
import android.location.Location;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Rene on 22.12.2015..
 */
public class AverageDirection {
    public AverageDirection() {
    }

    public double AvgDirection(List<PointF> myLocalHistory) {

        float x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        PointF poin = new PointF();
        PointF futurePoint = new PointF();
        List<PointF> avgPoint = new ArrayList<>();
        boolean zastavica1 = false;
        boolean zastavica2 = false;
        double angle = 0;

        int i = myLocalHistory.size() - 1;
        if (myLocalHistory.size() > 3) {
            if ((myLocalHistory.get(i).x + myLocalHistory.get(i).y) < (myLocalHistory.get(i - 1).x + myLocalHistory.get(i - 1).y) && (myLocalHistory.get(i - 1).x + myLocalHistory.get(i - 1).y > (myLocalHistory.get(i - 2).x + myLocalHistory.get(i - 2).y))) {
                x1 = myLocalHistory.get(i - 1).x;
                y1 = myLocalHistory.get(i - 1).y;
                //left
                zastavica1 = true;
            }
            if ((myLocalHistory.get(i).x + myLocalHistory.get(i).y) > (myLocalHistory.get(i - 1).x + myLocalHistory.get(i - 1).y) && (myLocalHistory.get(i - 1).x + myLocalHistory.get(i - 1).y) < (myLocalHistory.get(i - 2).x + myLocalHistory.get(i - 2).y)) {
                x2 = myLocalHistory.get(i - 1).x;
                y2 = myLocalHistory.get(i - 1).y;
                //right
                zastavica2 = true;
            }
            if (zastavica1 && zastavica2) {
                poin.x = (x1 + x2) / 2;
                poin.y = (y1 + y2) / 2;
                zastavica1 = false;
                zastavica2 = false;
                avgPoint.add(poin);
                int numberOfPoints = avgPoint.size();

                if (numberOfPoints >= 2) {
                    float futurex = avgPoint.get(numberOfPoints - 1).x - avgPoint.get(numberOfPoints - 2).x;
                    float futurey = avgPoint.get(numberOfPoints - 1).y - avgPoint.get(numberOfPoints - 2).y;
                    futurePoint.x = avgPoint.get(numberOfPoints - 1).x + futurex;
                    futurePoint.y = avgPoint.get(numberOfPoints - 1).y + futurey;
                     angle = angleBetweenPoints(futurePoint);
                }
            }
        }
        return angle;
    }
    ConvertingGpsCoordToXY convertingGpsCoordToXY =  new ConvertingGpsCoordToXY();
    PointF VectorAfterTurn =  new PointF();
    PointF VectorBeforeTurn = new PointF();
    PointF turn = new PointF();
    PointF pathAfterTurn = new PointF();

    private float angleBetweenPoints(PointF PointAtTheMoment) {
        turn.x = convertingGpsCoordToXY.convertLat((float) 48.221094);      //location of turn in wien converted
        turn.y = convertingGpsCoordToXY.convertLat((float) 16.377882);     //location if turn in wien converted
        pathAfterTurn.x = convertingGpsCoordToXY.convertLat((float) 48.221231);
        pathAfterTurn.y = convertingGpsCoordToXY.convertLat((float) 16.378046);

        VectorBeforeTurn.x = PointAtTheMoment.x - turn.x;
        VectorBeforeTurn.y = PointAtTheMoment.y - turn.y;

        VectorAfterTurn.x = pathAfterTurn.x - turn.x;
        VectorAfterTurn.y = pathAfterTurn.y - turn.y;

        float VBTA = (float) Math.sqrt(Math.pow(VectorBeforeTurn.x, 2) + Math.pow(VectorBeforeTurn.y, 2));
        float VATA = (float)Math.sqrt(Math.pow(VectorAfterTurn.x, 2) + Math.pow(VectorAfterTurn.y, 2));

        float Product = VectorBeforeTurn.x * VectorAfterTurn.x + VectorBeforeTurn.y * VectorAfterTurn.y;

        float Angle = (float) ((180 / Math.PI) * Math.acos(Product / (VBTA * VATA)));

        return Angle;
    }
}

