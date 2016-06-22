package hr.foi.air.evoski.wsplugin.mvp.presenter.impl;

import hr.foi.air.evoski.wsplugin.model.Coordinates;
import hr.foi.air.evoski.wsplugin.mvp.interactor.CoordiantesInteractor;
import hr.foi.air.evoski.wsplugin.mvp.interactor.impl.CoordiantesInteractorImpl;
import hr.foi.air.evoski.wsplugin.mvp.listener.OnCoordinatesFetched;
import hr.foi.air.evoski.wsplugin.mvp.presenter.CoordinatesPresenter;
import hr.foi.air.evoski.wsplugin.mvp.view.CoordiantesView;

/**
 * Created by Rene on 6.12.2015..
 */
public class CoordinatesPresenterImpl implements CoordinatesPresenter, OnCoordinatesFetched {

    private CoordiantesInteractor coordiantesInteractor;
    private CoordiantesView view;

    public CoordinatesPresenterImpl(CoordiantesView view) {
        coordiantesInteractor = new CoordiantesInteractorImpl();
        this.view = view;
    }

    /**
     * @param sourcePoint
     * @param destionationPoint
     * @param routeType
     * @param voiceInstructions
     * @param language
     */
    @Override
    public void getData(String sourcePoint, String destionationPoint, String routeType, String voiceInstructions, String language) {
        coordiantesInteractor.fetchCoordinates(sourcePoint, destionationPoint, routeType, voiceInstructions, language, this);
    }

    /**
     * @param coordinates
     */
    @Override
    public void fetchedCoordinatesData(Coordinates coordinates) {
        //TODO retrieve data and store in db
        view.storeFetchedCoordinates(coordinates);
    }
}
