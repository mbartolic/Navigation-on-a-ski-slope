package hr.foi.air.evoski.SmoothingAlgorithms;

import hr.foi.air.evoski.core.MyTrackPoints;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marko1 on 5.5.2016..
 */
public class MovingAverage implements AlgorithmsInterface{

    List<MyTrackPoints> pointMoveAvg = new ArrayList<>();

    public List<MyTrackPoints> getSmoothedPoints(List<MyTrackPoints> pointHistory) {

        MyTrackPoints pMovAvg;

        if (pointHistory.size() > 2) {
            for (int i = 1; i < pointHistory.size() - 1; i++) {
                pMovAvg = new MyTrackPoints();
                double x0 = pointHistory.get(i - 1).x;
                double x1 = pointHistory.get(i).x;
                double x2 = pointHistory.get(i + 1).x;
                double x = ((x0 + 2 * x1 + x2) / 4);

                double y0 = pointHistory.get(i - 1).y;
                double y1 = pointHistory.get(i).y;
                double y2 = pointHistory.get(i + 1).y;
                double y = ((y0 + 2 * y1 + y2) / 4);
                pMovAvg.x = x;
                pMovAvg.y = y;
                pointMoveAvg.add(pMovAvg);
            }
        }
        return pointMoveAvg;
    }
}