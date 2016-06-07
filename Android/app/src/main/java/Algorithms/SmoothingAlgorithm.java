package Algorithms;

import com.example.rene.navigation_on_a_ski_slope.MyTrackPoints;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marko1 on 5.5.2016..
 */
public class SmoothingAlgorithm implements AlgorithmsInterface{

    List<MyTrackPoints> smoothRealList = new ArrayList<MyTrackPoints>();
    MyTrackPoints line1=null;
    MyTrackPoints line2=null;
    MyTrackPoints toProject=null;

    public List<MyTrackPoints> Smooth(List<MyTrackPoints> pointHistory) {

        MyTrackPoints realSmoothPointToAvg;
        for (int i = 0; i < pointHistory.size(); i++) {
            realSmoothPointToAvg = new MyTrackPoints();
            if (i > 2) {
                line1 = pointHistory.get(i - 2);
                line2 = pointHistory.get(i);
                toProject = pointHistory.get(i - 1);
                double m = (double) (line2.y - line1.y) / (line2.x - line1.x);
                double b = (double) line1.y - (m * line1.x);

                realSmoothPointToAvg.x = (m * toProject.y + toProject.y - m * b) / (m * m + 1);
                realSmoothPointToAvg.y = (m * m * toProject.y + m * toProject.x + b) / (m * m + 1);
                smoothRealList.add(realSmoothPointToAvg);
            }
        }
        return smoothRealList;
    }

    @Override
    public List<MyTrackPoints> MovAverage(List<MyTrackPoints> myLocHist) {
        return null;
    }
}
