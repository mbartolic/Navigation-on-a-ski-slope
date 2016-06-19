package com.example.rene.MainClasses;


public class ConvertingGpsCoordToXY {

    private double lon, lat;
    public ConvertingGpsCoordToXY() {}

    /**
     * Converts GPS coordinates to values [0..1].
     * @param longitude
     * @return
     */
    public double convertLon(double longitude) {
        lon = longitude;
        if(lon < 0){
            lon = lon * (-1);
            return lon;
        }else return lon;
    }

    /**
     * Converts GPS coordinates to values [0..1].
     * @param latitude
     * @return
     */
    public double convertLat(double latitude) {
        lat = latitude;
        if(lat < 0){
            lat = lat * (-1);
            return lat;
        }else return lat;
        }
    }


