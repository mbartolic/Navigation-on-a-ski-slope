package com.example.rene.navigation_on_a_ski_slope;


public class ConvertingGpsCoordToXY {

    private double lon, lat;

    public ConvertingGpsCoordToXY() {}

    public double convertLon(double longitude) {  //pretvara long u nas sustav
        lon = longitude;
        if(lon < 0){
            lon = lon * (-1);
            lon = lon/180;
            return lon;
        }else {
            lon = lon/180;
            return lon;
        }
    }

    public double convertLat(double latitude) { //pretvara lat u nas sustav
        lat = latitude;
        if(lat < 0){
            lat = lat * (-1);
            lat = lat/90;
            return lat;
        }else {
            lat = lat/90;
            return lat;
        }
    }
}

