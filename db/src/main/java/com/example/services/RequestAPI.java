package com.example.services;

import com.example.model.Coordinates;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Rene on 6.12.2015..
 */
public interface RequestAPI {

    @GET("/evo-ski")
    void getCoordinates(Callback<List<Coordinates>> allCoordinates);


}
