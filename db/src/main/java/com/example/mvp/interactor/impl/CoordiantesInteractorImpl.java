package com.example.mvp.interactor.impl;

import android.util.Log;

import com.example.model.Coordinates;
import com.example.mvp.interactor.CoordiantesInteractor;
import com.example.mvp.listener.OnCoordinatesFetched;
import com.example.services.RequestAPI;
import com.example.utils.Constants;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 *
 * Created by Rene on 6.12.2015..
 */
public class CoordiantesInteractorImpl implements CoordiantesInteractor, Callback<Coordinates> {

    OnCoordinatesFetched listener;
    private static final String LOG_KEY = "coordianes";

    /**
     * Method uses Retrofit to fetch information from the web service.
     * @param sourcePoint
     * @param destionationPoint
     * @param routeType
     * @param voiceInstructions
     * @param language
     * @param listener
     */
    @Override
    public void fetchCoordinates(String sourcePoint, String destionationPoint, String routeType, String voiceInstructions, String language, OnCoordinatesFetched listener) {
        this.listener = listener;


        //intitialize Retrofit adapter and request api interface
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(Constants.ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        RequestAPI api = adapter.create(RequestAPI.class);


        //call web service using retrofit api
        api.getCoordinates(sourcePoint, destionationPoint, routeType, voiceInstructions, language, this);
    }

    /**
     * Successful response from web service using Retrofit.
     * @param coordinates
     * @param response
     */
    @Override
    public void success(Coordinates coordinates, Response response) {

        listener.fetchedCoordinatesData(coordinates);
    }

    /**
     * Failed response from web service using Retrofit.
     * @param error
     */
    @Override
    public void failure(RetrofitError error) {
        Log.e(LOG_KEY, error.toString());
    }
}
