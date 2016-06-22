package hr.foi.air.evoski.SmoothingAlgorithms;



import hr.foi.air.evoski.core.MyTrackPoints;

import java.util.List;

/**
 * Created by Marko1 on 8.5.2016..
 */
public interface AlgorithmsInterface {

    /**Method for smoothing Algorithm
     * @param myRealPoints Reals points obtained by GPS or by other method.
     * @return List of smoothed points.
     */
    List<MyTrackPoints> getSmoothedPoints(List<MyTrackPoints> myRealPoints);

}
