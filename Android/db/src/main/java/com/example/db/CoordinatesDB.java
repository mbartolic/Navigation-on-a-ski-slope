package com.example.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.example.model.Coordinates;

import java.io.Serializable;

/**
 * Created by Rene on 6.12.2015..
 */

@Table(name = "Coordinates")
public class CoordinatesDB extends Model {

    public CoordinatesDB(){
        super();
    }

    @Column (name = "lon")
    private String lon;
    @Column (name = "lat")
    private String lat;
    @Column (name = "image")
    private String image;

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
