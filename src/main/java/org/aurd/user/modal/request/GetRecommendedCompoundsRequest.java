package org.aurd.user.modal.request;

import org.aurd.user.constant.Constants;

import java.util.ArrayList;
import java.util.Arrays;

public class GetRecommendedCompoundsRequest {
    ArrayList<Double> coordinates= new ArrayList<>(Arrays.asList());


    public ArrayList<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Double> coordinates) {
        this.coordinates = coordinates;
    }
}
