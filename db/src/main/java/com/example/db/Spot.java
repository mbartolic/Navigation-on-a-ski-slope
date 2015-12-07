package com.example.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.example.model.Coordinates;

@Table(name = "Spot")
public class Spot extends Model {

    @Column(name = "idRoutes")
    private Routes idRoutes;
    @Column(name = "idCoordinates")
    private Coordinates idCoordinates;

    public Spot() {
        super();
    }

    public Spot(Routes idRout, Coordinates idCoord){
        this.idRoutes = idRout;
        this.idCoordinates = idCoord;
    }

    public Routes getIdRoutes (){return idRoutes;}
    public Coordinates getIdCoordinates(){return idCoordinates;}

}
