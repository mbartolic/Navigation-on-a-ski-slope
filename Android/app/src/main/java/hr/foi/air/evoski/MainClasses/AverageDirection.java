package hr.foi.air.evoski.MainClasses;

import hr.foi.air.evoski.core.MyTrackPoints;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Rene on 22.12.2015..
 */

public class AverageDirection {

    public AverageDirection() {}

    /**
     * Calculates average direction of the skier. The method needs at least three endpoints to work properly.
     * @param myLocalHistory
     * @return
     */
    public double AvgDirection(List<MyTrackPoints> myLocalHistory,List<MyTrackPoints> pathAfterTurn, int index) {

        List<MyTrackPoints> avgPoint = new ArrayList<>();
        List<MyTrackPoints> extremePoints = new ArrayList<>();
        double angle = 0;
        Boolean flagx1 = false;
        Boolean flagx2 = false;
        Boolean flagy1 = false;
        Boolean flagY2 = false;
        MyTrackPoints extPoint;
        MyTrackPoints p;

        for(int i = 5; i<myLocalHistory.size(); i++) {
            if (((myLocalHistory.get(i - 2).x < myLocalHistory.get(i - 1).x) && (myLocalHistory.get(i - 2).x < myLocalHistory.get(i).x) && ((myLocalHistory.get(i - 2).x) < myLocalHistory.get(i - 3).x) && (myLocalHistory.get(i - 2).x < myLocalHistory.get(i - 4).x) && (myLocalHistory.get(i - 2).x < myLocalHistory.get(i - 5).x))) {
                extPoint= new MyTrackPoints();
                extPoint.x = myLocalHistory.get(i - 2).x;
                extPoint.y = myLocalHistory.get(i - 2).y;
                //left
                flagx1 = true;
                extremePoints.add(extPoint);
            }
            if (((myLocalHistory.get(i - 2).x > myLocalHistory.get(i - 1).x) && (myLocalHistory.get(i - 2).x > myLocalHistory.get(i).x) && ((myLocalHistory.get(i - 2).x) > myLocalHistory.get(i - 3).x) && (myLocalHistory.get(i - 2).x > myLocalHistory.get(i - 4).x) && (myLocalHistory.get(i - 2).x > myLocalHistory.get(i - 5).x))) {
                extPoint= new MyTrackPoints();
                extPoint.x = myLocalHistory.get(i - 2).x;
                extPoint.y = myLocalHistory.get(i - 2).y;
                //right
                flagx2 = true;
                extremePoints.add(extPoint);
            }
            if (((myLocalHistory.get(i - 2).y < myLocalHistory.get(i - 1).y) && (myLocalHistory.get(i - 2).y < myLocalHistory.get(i).y) && ((myLocalHistory.get(i - 2).y) < myLocalHistory.get(i - 3).y) && (myLocalHistory.get(i - 2).y < myLocalHistory.get(i - 4).y) && (myLocalHistory.get(i - 2).y < myLocalHistory.get(i - 5).y))) {
                extPoint= new MyTrackPoints();
                extPoint.x = myLocalHistory.get(i - 2).x;
                extPoint.y = myLocalHistory.get(i - 2).y;
                //left
                flagy1 = true;
                extremePoints.add(extPoint);
            }
            if (((myLocalHistory.get(i - 2).y > myLocalHistory.get(i - 1).y) && (myLocalHistory.get(i - 2).y > myLocalHistory.get(i).y) && ((myLocalHistory.get(i - 2).y) > myLocalHistory.get(i - 3).y) && (myLocalHistory.get(i - 2).y > myLocalHistory.get(i - 4).y) && (myLocalHistory.get(i - 2).y > myLocalHistory.get(i - 5).y))) {
                extPoint= new MyTrackPoints();
                extPoint.x = myLocalHistory.get(i - 2).x;
                extPoint.y = myLocalHistory.get(i - 2).y;
                //right
                flagY2 = true;
                extremePoints.add(extPoint);
            }
            int j = myLocalHistory.size();

            if (flagx1 && flagx2 || flagy1 && flagY2) {
                flagx1 = false;
                flagx2 = false;
                flagy1 = false;
                flagY2 = false;
                int ii = extremePoints.size() - 1;
                p = new MyTrackPoints();
                p.x = (extremePoints.get(ii).x + extremePoints.get(ii - 1).x) / 2;
                p.y = (extremePoints.get(ii).y + extremePoints.get(ii - 1).y) / 2;
                avgPoint.add(p);
            }
                int numberOfPoints = avgPoint.size();
                angle = futurePoints(numberOfPoints, avgPoint, pathAfterTurn, index);
        }
        return angle;
    }

    /**
     * Calculates approximate future position of the skier.
     * @param numberOfPoints
     * @param avgPoint
     * @return
     */
    private double futurePoints (int numberOfPoints, List<MyTrackPoints> avgPoint, List<MyTrackPoints> pathAfterTurn, int index) {
        double angle = 0;
        MyTrackPoints futurePoint = new MyTrackPoints();
        List<MyTrackPoints> PointAtMom = new ArrayList<>();
        if (numberOfPoints >= 2) {
            double futurex = avgPoint.get(numberOfPoints - 1).x - avgPoint.get(numberOfPoints - 2).x;
            double futurey = avgPoint.get(numberOfPoints - 1).y - avgPoint.get(numberOfPoints - 2).y;
            futurePoint.x = avgPoint.get(numberOfPoints - 1).x + futurex;
            futurePoint.y = avgPoint.get(numberOfPoints - 1).y + futurey;
            PointAtMom.add(avgPoint.get(avgPoint.size()-1));
            PointAtMom.add(futurePoint);
            angle = angleBetweenPoints(PointAtMom, pathAfterTurn, index);
        }
        return angle;
    }
    MyTrackPoints VectorAfterTurn =  new MyTrackPoints();
    MyTrackPoints VectorBeforeTurn = new MyTrackPoints();
    MyTrackPoints turn = new MyTrackPoints();
    MyTrackPoints pathAfterTur = new MyTrackPoints();

    /**
     * Calculates angle between points on junction.
     * @param PointAtTheMoment
     * @return
     */
    private double angleBetweenPoints(List<MyTrackPoints> PointAtTheMoment, List<MyTrackPoints> pathAfterTurn, int index) {
        turn.x = pathAfterTurn.get(index).x;
        turn.y = pathAfterTurn.get(index).y;
        pathAfterTur.x = pathAfterTurn.get(index + 1).x;
        pathAfterTur.y =pathAfterTurn.get(index + 1).y;
        VectorBeforeTurn.x = PointAtTheMoment.get(PointAtTheMoment.size() - 1).x - PointAtTheMoment.get(PointAtTheMoment.size() - 2).x;
        VectorBeforeTurn.y = PointAtTheMoment.get(PointAtTheMoment.size() - 1).y - PointAtTheMoment.get(PointAtTheMoment.size() - 2).y;
        VectorAfterTurn.x = pathAfterTur.x - turn.x;
        VectorAfterTurn.y = pathAfterTur.y - turn.y;
        double VBTA = Math.sqrt(Math.pow(VectorBeforeTurn.x, 2) + Math.pow(VectorBeforeTurn.y, 2));
        double VATA = Math.sqrt(Math.pow(VectorAfterTurn.x, 2) + Math.pow(VectorAfterTurn.y, 2));
        double Product = VectorBeforeTurn.x * VectorAfterTurn.x + VectorBeforeTurn.y * VectorAfterTurn.y;
        double Angle = ((180 / Math.PI) * Math.acos(Product / (VBTA * VATA)));
        return Angle;
    }
}

