package com.example.mvp.interactor.impl;

import android.util.Log;

import com.example.model.Coordinates;
import com.example.mvp.interactor.CoordiantesInteractor;
import com.example.mvp.listener.OnCoordinatesFetched;
import com.example.services.RequestAPI;
import com.example.utils.Constants;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Rene on 6.12.2015..
 */
public class CoordiantesInteractorImpl implements CoordiantesInteractor, Callback<List<Coordinates>> {

    OnCoordinatesFetched listener;
    private static final String LOG_KEY = "coordianes";

    @Override
    public void fetchCoordinates(OnCoordinatesFetched listener) {
        this.listener = listener;


        //retrofit
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(Constants.ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        RequestAPI api = adapter.create(RequestAPI.class);

        //slanje requesta na server
        api.getCoordinates(this);
    }

    @Override
    public void success(List<Coordinates> coordinates, Response response) {
        Log.e(LOG_KEY, "success");
        listener.fetchedCoordinatesData(coordinates);
    }

    @Override
    public void failure(RetrofitError error) {
        Log.e(LOG_KEY, error.toString());
    }
}
