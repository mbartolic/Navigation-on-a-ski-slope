package hr.foi.air.evoski.wsplugin.mvp.presenter;

/**
 * Interface for fetching coordinates.
 * Created by Rene on 6.12.2015..
 */
public interface CoordinatesPresenter {
    void getData(String sourcePoint, String destionationPoint, String routeType, String voiceInstructions, String language);
}
