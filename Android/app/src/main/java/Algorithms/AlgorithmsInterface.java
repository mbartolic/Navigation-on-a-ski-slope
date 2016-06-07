package Algorithms;

import com.example.rene.navigation_on_a_ski_slope.MyTrackPoints;

import java.util.List;

/**
 * Created by Marko1 on 8.5.2016..
 */
public interface AlgorithmsInterface {

    List<MyTrackPoints> Smooth(List<MyTrackPoints> myLocHist);

    List<MyTrackPoints> MovAverage(List<MyTrackPoints> myLocHist);

}
