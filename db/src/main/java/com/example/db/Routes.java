package com.example.db;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Routes")
public class Routes extends Model{

    @Column (name = "routName", index = true)
    private String routName;
    @Column (name = "routLength")
    private int routLength;
    @Column (name = "routHeight")
    private int routHeight;
    @Column (name = "routVerticalDrop")
    private int routVerticalDrop;

    public Routes() {
        super();
    }

    public Routes(String rName, int rLein, int rHeight, int rVertDrp){
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
