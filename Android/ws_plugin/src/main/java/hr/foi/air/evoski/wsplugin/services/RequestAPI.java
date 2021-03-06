package hr.foi.air.evoski.wsplugin.services;

import hr.foi.air.evoski.wsplugin.model.Coordinates;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Request to the web service.
 * Created by Rene on 6.12.2015..
 */
public interface RequestAPI {

    @GET("/route")
    void getCoordinates(@Query("point") String sourcePoint, @Query("point") String destionationPoint, @Query("routeType") String routeType, @Query("voice_instructions") String voiceInstructions, @Query("language") String language, Callback<Coordinates> allCoordinates);

}
