package hr.foi.air.evoski.wsplugin;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import hr.foi.air.evoski.core.PreferenceManagerHelper;
import hr.foi.air.evoski.wsplugin.model.Coordinates;
import hr.foi.air.evoski.wsplugin.model.Instruction;
import hr.foi.air.evoski.wsplugin.mvp.presenter.CoordinatesPresenter;
import hr.foi.air.evoski.wsplugin.mvp.presenter.impl.CoordinatesPresenterImpl;
import hr.foi.air.evoski.wsplugin.mvp.view.CoordiantesView;
import hr.foi.air.evoski.core.FetchTrackCoordinatesInterface;
import hr.foi.air.evoski.core.FetchTrackCoordinatesListener;
import hr.foi.air.evoski.core.MyTrackPoints;
import hr.foi.air.evoski.wsplugin.utils.Constants;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomisav on 20.6.2016..
 */
public class WebServerCoordinates implements FetchTrackCoordinatesInterface, CoordiantesView {
    CoordinatesPresenter coordinatesPresenter;
    FetchTrackCoordinatesListener l = null;

    @Override
    public void fetchTrackCoordinates(FetchTrackCoordinatesListener l, Context c) {
        List<MyTrackPoints> myTrackPointsList = null;
        this.l = l;

        // TODO - Dohvatiti iz shared preferences
        coordinatesPresenter = new CoordinatesPresenterImpl(this);

            String sorX = PreferenceManagerHelper.getStartLong(c);
            String sorY = PreferenceManagerHelper.getStartLat(c);
            String desX = PreferenceManagerHelper.getEndLong(c);
            String desY = PreferenceManagerHelper.getEndLat(c);
            String sourcePoints = sorY + "," + sorX;
            String destinationPoints = desY + "," + desX;
            coordinatesPresenter.getData(sourcePoints, destinationPoints, Constants.CAR_ROUTE_TYPE, Constants.VOICE_INSTRUCTIONS, Constants.LANGUAGE);
            Toast.makeText(c, "Coordinates fetched", Toast.LENGTH_SHORT).show();

    }

    public void storeFetchedCoordinates(Coordinates coordinates) {
        List<MyTrackPoints> myTrackPointsList = new ArrayList<>();

        for (Instruction instruction : coordinates.getPaths().get(0).getInstructions()) {
            MyTrackPoints coord = new MyTrackPoints();
            coord.y = (Double.parseDouble(instruction.getCoordinate().get(0)));
            coord.x = (Double.parseDouble(instruction.getCoordinate().get(1)));
            coord.turn = instruction.getSign();
            myTrackPointsList.add(coord);
        }

        l.OnCoordinatesFetched(myTrackPointsList);

    }
}
