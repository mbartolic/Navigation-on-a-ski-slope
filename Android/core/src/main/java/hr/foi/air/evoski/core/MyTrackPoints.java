package hr.foi.air.evoski.core;

import android.os.Parcel;

/**
 * Created by Rene on 8.3.2016..
 */
public class MyTrackPoints {

    public MyTrackPoints() {}

    public double x;
    public double y;
    public int turn;

    public MyTrackPoints(double x, double y, int turn){
        this.x=x;
        this.y=y;
        this.turn=turn;
    }

    public MyTrackPoints(Parcel in) {
        String[] data = new String[3];
        in.readStringArray(data);
        this.x = Double.parseDouble(data[0]);
        this.y = Double.parseDouble(data[1]);
        this.turn = Integer.parseInt((data[2]));
    }
}
