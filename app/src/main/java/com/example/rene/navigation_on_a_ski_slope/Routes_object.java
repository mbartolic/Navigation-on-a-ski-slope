package com.example.rene.navigation_on_a_ski_slope;


public class Routes_object {

    private String routName;
    private int routLength;
    private int routHeight;
    private int routVerticalDrop;

    public Routes_object() {
    }

    public Routes_object(String rName, int rLein, int rHeight, int rVertDrp){
        this.routName = rName;
        this.routLength = rLein;
        this.routHeight = rHeight;
        this.routVerticalDrop = rVertDrp;
    }

    public String getRoutName(){
        return routName;
    }

    public int getRoutLeinght(){
        return routLength;
    }

    public int getRoutHeight(){
        return routHeight;
    }

    public int getRoutVerticalDrop(){
        return routVerticalDrop;
    }

}
