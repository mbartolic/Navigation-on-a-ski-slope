package hr.foi.air.evoski.FetchCoordinates;

import android.content.Context;

import hr.foi.air.evoski.MainClasses.LocalFileRead;
import hr.foi.air.evoski.core.FetchTrackCoordinatesInterface;
import hr.foi.air.evoski.core.FetchTrackCoordinatesListener;
import hr.foi.air.evoski.core.MyTrackPoints;

import java.util.List;

/**
 * Created by Tomisav on 20.6.2016..
 */
public class FileCoordinates implements FetchTrackCoordinatesInterface {
    @Override
    public void fetchTrackCoordinates(FetchTrackCoordinatesListener l, Context c) {
        List<MyTrackPoints> myTrackPointsList = null;

        LocalFileRead localFileRead = new LocalFileRead();
        myTrackPointsList = localFileRead.readFile();

        l.OnCoordinatesFetched(myTrackPointsList);
    }
}
