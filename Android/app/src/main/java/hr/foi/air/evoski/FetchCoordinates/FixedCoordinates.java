package hr.foi.air.evoski.FetchCoordinates;


import android.content.Context;

import hr.foi.air.evoski.core.FetchTrackCoordinatesInterface;
import hr.foi.air.evoski.core.FetchTrackCoordinatesListener;
import hr.foi.air.evoski.core.MyTrackPoints;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomisav on 20.6.2016..
 */
public class FixedCoordinates implements FetchTrackCoordinatesInterface {
    @Override
    public void fetchTrackCoordinates(FetchTrackCoordinatesListener l, Context c) {

        MyTrackPoints point1, point2, point3, point4;
        point1 = new MyTrackPoints();
        point1.y =  46.308044;
        point1.x =  16.345183;
        point1.turn = 0;
        point2 = new MyTrackPoints();
        point2.y =  46.307642;
        point2.x =  16.343479;
        point2.turn = 2;
        point3 = new MyTrackPoints();
        point3.y =  46.308439;
        point3.x =  16.342981;
        point3.turn = -2;
        point4 = new MyTrackPoints();
        point4.y = 46.307944;
        point4.x =  16.340941 ;
        point4.turn = 0;

        List<MyTrackPoints> myTrackPointsList = null;
        myTrackPointsList = new ArrayList<>();
        myTrackPointsList.add(point1);
        myTrackPointsList.add(point2);
        myTrackPointsList.add(point3);
        myTrackPointsList.add(point4);

        l.OnCoordinatesFetched(myTrackPointsList);
    }
}
