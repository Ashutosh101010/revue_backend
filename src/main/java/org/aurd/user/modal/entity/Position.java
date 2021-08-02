package org.aurd.user.modal.entity;

import java.util.ArrayList;

public class Position {
    ArrayList<Double> coordinates;
    String type;

    public ArrayList<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
