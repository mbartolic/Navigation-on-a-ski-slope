package hr.foi.air.evoski.core;

import android.content.Context;

/**
 * Created by Tomisav on 20.6.2016..
 */
public interface FetchTrackCoordinatesInterface {
    /**
     * Method for fetching track coordinates.
     * @return List of track coordinates casted as MyTrackPoints.
     */
    void fetchTrackCoordinates(FetchTrackCoordinatesListener listener, Context c);
}
