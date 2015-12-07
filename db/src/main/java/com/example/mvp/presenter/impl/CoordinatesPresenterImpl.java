package com.example.mvp.presenter.impl;

import com.example.model.Coordinates;
import com.example.mvp.interactor.CoordiantesInteractor;
import com.example.mvp.interactor.impl.CoordiantesInteractorImpl;
import com.example.mvp.listener.OnCoordinatesFetched;
import com.example.mvp.presenter.CoordinatesPresenter;
import com.example.mvp.view.CoordiantesView;

import java.util.List;

/**
 * Created by Rene on 6.12.2015..
 */
public class CoordinatesPresenterImpl implements CoordinatesPresenter, OnCoordinatesFetched {

    private CoordiantesInteractor coordiantesInteractor;
    private CoordiantesView view;

    public CoordinatesPresenterImpl(CoordiantesView view){
        coordiantesInteractor = new CoordiantesInteractorImpl();
        this.view = view;
    }

    @Override
    public void getData() {
        coordiantesInteractor.fetchCoordinates(this);
    }

    @Override
    public void fetchedCoordinatesData(List<Coordinates> coordinates) {
        //TODO retrieve data and store in db
        view.storeFetchedCoordinates(coordinates);
    }
}
