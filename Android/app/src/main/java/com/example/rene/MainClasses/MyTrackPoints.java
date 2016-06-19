package com.example.rene.MainClasses;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Rene on 8.3.2016..
 */
public class MyTrackPoints implements Parcelable {

    public MyTrackPoints() {}

    public double x;
    public double y;
    public int turn;

    public MyTrackPoints(double x, double y, int turn){
        this.x=x;
        this.y=y;
        this.turn=turn;
    }

    public MyTrackPoints(Parcel in){
        String[] data= new String[3];
        in.readStringArray(data);
        this.x= Double.parseDouble(data[0]);
        this.y= Double.parseDouble(data[1]);
        this.turn= Integer.parseInt((data[2]));
    }

    public MyTrackPoints(List<MyTrackPoints> myTrackPointsList) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{String.valueOf(this.x), String.valueOf(this.y), String.valueOf(this.turn)});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MyTrackPoints createFromParcel(Parcel in) {
            return new MyTrackPoints(in);
        }
        public MyTrackPoints[] newArray(int size) {
            return new MyTrackPoints[size];
        }
    };
}
