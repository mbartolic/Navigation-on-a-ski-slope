package com.example.rene.navigation_on_a_ski_slope;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Rene on 22.12.2015..
 */

public class AverageDirection {
    public AverageDirection() {}


    public double AvgDirection(List<MyPointDouble> myLocalHistory, List<MyPointDouble> pathAfterTurn) {
        List<MyPointDouble> myLocHistX = new ArrayList<>();
        List<MyPointDouble> myLocHistY = new ArrayList<>();
        double angle, angleOfDirect;
        angle = AvgDirectionXY(myLocalHistory, pathAfterTurn);

        if (angle == 0.0) {
            for (int i = 0; i < myLocalHistory.size(); i++) {
                MyPointDouble pointX = new MyPointDouble();
                pointX.x = myLocalHistory.get(i).x;
                pointX.y = 0;
                myLocHistX.add(pointX);
            }
            angle = AvgDirectionXY(myLocHistX, pathAfterTurn);
            if(angle == 0.0){
                for (int i = 0; i < myLocalHistory.size(); i++) {
                    MyPointDouble pointY = new MyPointDouble();
                    pointY.x = 0;
                    pointY.y = myLocalHistory.get(i).y;
                    myLocHistY.add(pointY);
                }
                angle = AvgDirectionXY(myLocHistY, pathAfterTurn);
                angleOfDirect = angle;
            }else angleOfDirect = angle;
        }else angleOfDirect = angle;

        return angleOfDirect;
    }


    /**
     * Calculates average direction of the skier. The method needs at least three endpoints to work properly.
     * @param myLocalHistory
     * @return
     */
    public double AvgDirectionXY(List<MyPointDouble> myLocalHistory, List<MyPointDouble> pathAfterTurn) {
        double x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        List<MyPointDouble> avgPoint = new ArrayList<>();
        boolean zastavica1 = false;
        boolean zastavica2 = false;
        double angle = 0;

        int size = myLocalHistory.size();
        for(int i = 2; i<size; i++){
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
                MyPointDouble poin = new MyPointDouble();
                poin.x = (x1 + x2) / 2;
                poin.y = (y1 + y2) / 2;
                zastavica1 = false;
                zastavica2 = false;
                avgPoint.add(poin);
                int numberOfPoints = avgPoint.size();
                angle = futurePoints(numberOfPoints, avgPoint, pathAfterTurn);
            }
        }
        }
        return angle;
    }

    /**
     * Calculates approximate future position of the skier.
     * @param numberOfPoints
     * @param avgPoint
     * @return
     */
    private double futurePoints (int numberOfPoints, List<MyPointDouble> avgPoint, List<MyPointDouble> pathAfterTurn) {
        double angle = 0;
        MyPointDouble futurePoint = new MyPointDouble();
        if (numberOfPoints >= 2) {
            double futurex = avgPoint.get(numberOfPoints - 1).x - avgPoint.get(numberOfPoints - 2).x;
            double futurey = avgPoint.get(numberOfPoints - 1).y - avgPoint.get(numberOfPoints - 2).y;
            futurePoint.x = avgPoint.get(numberOfPoints - 1).x + futurex;
            futurePoint.y = avgPoint.get(numberOfPoints - 1).y + futurey;
            angle = angleBetweenPoints(futurePoint, pathAfterTurn);
        }
        return angle;
    }
    MyPointDouble VectorAfterTurn =  new MyPointDouble();
    MyPointDouble VectorBeforeTurn = new MyPointDouble();
    MyPointDouble turn = new MyPointDouble();
    MyPointDouble pathAfterTur = new MyPointDouble();

    /**
     * Calculates angle between points on junction.
     * @param PointAtTheMoment
     * @return
     */
    private double angleBetweenPoints(MyPointDouble PointAtTheMoment, List<MyPointDouble> pathAfterTurn) {
        turn.x = pathAfterTurn.get(0).x;      //location of turn in wien converted
        turn.y = pathAfterTurn.get(0).y;     //location if turn in wien converted
        pathAfterTur.x = pathAfterTurn.get(1).x;
        pathAfterTur.y =pathAfterTurn.get(1).y;

        VectorBeforeTurn.x = PointAtTheMoment.x - turn.x;
        VectorBeforeTurn.y = PointAtTheMoment.y - turn.y;

        VectorAfterTurn.x = pathAfterTur.x - turn.x;
        VectorAfterTurn.y = pathAfterTur.y - turn.y;

        double VBTA = Math.sqrt(Math.pow(VectorBeforeTurn.x, 2) + Math.pow(VectorBeforeTurn.y, 2));
        double VATA = Math.sqrt(Math.pow(VectorAfterTurn.x, 2) + Math.pow(VectorAfterTurn.y, 2));

        double Product = VectorBeforeTurn.x * VectorAfterTurn.x + VectorBeforeTurn.y * VectorAfterTurn.y;

        double Angle = ((180 / Math.PI) * Math.acos(Product / (VBTA * VATA)));

        return Angle;
    }
}

