package com.example.rene.navigation_on_a_ski_slope;

import android.graphics.Point;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
    public double AvgDirection(List<MyTrackPoints> myLocalHistory,List<MyTrackPoints> pathAfterTurn) {
        List<MyTrackPoints> avgPoint = new ArrayList<>();

        List<MyTrackPoints> extremePoints = new ArrayList<>();
        double angle = 0;
        Boolean flagx1 = false;
        Boolean flagx2 = false;
        Boolean flagy1 = false;
        Boolean flagY2 = false;
        MyTrackPoints extPoint;
        MyTrackPoints futurePoint=new MyTrackPoints();
        MyTrackPoints p;
        List<MyTrackPoints> point=new List<MyTrackPoints>() {
            @Override
            public void add(int location, MyTrackPoints object) {

            }

            @Override
            public boolean add(MyTrackPoints object) {
                return false;
            }

            @Override
            public boolean addAll(int location, Collection<? extends MyTrackPoints> collection) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends MyTrackPoints> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean contains(Object object) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> collection) {
                return false;
            }

            @Override
            public MyTrackPoints get(int location) {
                return null;
            }

            @Override
            public int indexOf(Object object) {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @NonNull
            @Override
            public Iterator<MyTrackPoints> iterator() {
                return null;
            }

            @Override
            public int lastIndexOf(Object object) {
                return 0;
            }

            @Override
            public ListIterator<MyTrackPoints> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<MyTrackPoints> listIterator(int location) {
                return null;
            }

            @Override
            public MyTrackPoints remove(int location) {
                return null;
            }

            @Override
            public boolean remove(Object object) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> collection) {
                return false;
            }

            @Override
            public MyTrackPoints set(int location, MyTrackPoints object) {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @NonNull
            @Override
            public List<MyTrackPoints> subList(int start, int end) {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(T[] array) {
                return null;
            }
        };


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
                angle = futurePoints(numberOfPoints, avgPoint, pathAfterTurn);
        }

        return angle;
    }

    /**
     * Calculates approximate future position of the skier.
     * @param numberOfPoints
     * @param avgPoint
     * @return
     */
    private double futurePoints (int numberOfPoints, List<MyTrackPoints> avgPoint, List<MyTrackPoints> pathAfterTurn) {
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
            angle = angleBetweenPoints(PointAtMom, pathAfterTurn);
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
    private double angleBetweenPoints(List<MyTrackPoints> PointAtTheMoment, List<MyTrackPoints> pathAfterTurn) {
        turn.x = pathAfterTurn.get(0).x;
        turn.y = pathAfterTurn.get(0).y;
        pathAfterTur.x = pathAfterTurn.get(1).x;
        pathAfterTur.y =pathAfterTurn.get(1).y;

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

