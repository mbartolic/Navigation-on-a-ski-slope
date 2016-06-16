package com.example.utils;

/**
 * Created by Rene on 6.12.2015..
 */
public class Constants {

    private  void Constants(){}

    public static final String ENDPOINT = "http://routing2.maptoolkit.net";

    public static final String SOURCE_LATITUDE = "46.308826";
    public static final String SOURCE_LONGITUDE = "16.347091";
    public static final String SOURCE_POINTS = SOURCE_LATITUDE + "," + SOURCE_LONGITUDE;

    public static final String DESTINATION_LATITUDE = "46.308181";
    public static final String DESTIONATION_LONGITUDE = "16.343111";
    public static final String DESTINATION_POINTS = DESTINATION_LATITUDE + "," + DESTIONATION_LONGITUDE;

    public static final String CAR_ROUTE_TYPE = "car";
    public static final String VOICE_INSTRUCTIONS = "0";
    public static final String LANGUAGE = "en";

    //Example: point=48.202596,16.369801&point=48.208 373,16.370401&routeType=car&voice_instructions=1&language=de

}
