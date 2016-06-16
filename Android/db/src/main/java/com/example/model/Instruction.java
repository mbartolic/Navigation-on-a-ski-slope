package com.example.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Marko on 02.02.2016..
 */

public class Instruction implements Serializable {

    private float distance;

    private String text;

    private int sign;

    private double time;

    private List<String> coordinate;

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public List<String> getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(List<String> coordinate) {
        this.coordinate = coordinate;
    }


    public class VoiceInstructions implements Serializable{

        private List<String> coordinate;

        private String text;

        public List<String> getCoordinate() {
            return coordinate;
        }

        public void setCoordinate(List<String> coordinate) {
            this.coordinate = coordinate;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
