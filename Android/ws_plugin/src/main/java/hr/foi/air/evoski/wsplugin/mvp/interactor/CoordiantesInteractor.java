package hr.foi.air.evoski.wsplugin.mvp.interactor;

import hr.foi.air.evoski.wsplugin.mvp.listener.OnCoordinatesFetched;

/**
 * Intarface for interaction with web service coordinates.
 * Created by Rene on 6.12.2015..
 */
public interface CoordiantesInteractor {
    void fetchCoordinates(String sourcePoint, String destionationPoint, String routeType, String voiceInstructions, String language, OnCoordinatesFetched listener);
}