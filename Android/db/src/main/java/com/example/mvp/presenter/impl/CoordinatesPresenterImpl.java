package com.example.mvp.presenter.impl;

import android.widget.Toast;

import com.example.model.Coordinates;
import com.example.model.Instruction;
import com.example.mvp.interactor.CoordiantesInteractor;
import com.example.mvp.interactor.impl.CoordiantesInteractorImpl;
import com.example.mvp.listener.OnCoordinatesFetched;
import com.example.mvp.presenter.CoordinatesPresenter;
import com.example.mvp.view.CoordiantesView;

import java.util.ArrayList;
import java.util.List;

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
