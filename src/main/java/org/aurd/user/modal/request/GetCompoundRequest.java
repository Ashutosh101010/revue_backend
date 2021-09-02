package org.aurd.user.modal.request;

import java.util.ArrayList;

public class GetCompoundRequest
{
    String lastObjectID;
    String category;
    ArrayList amenities;
    Integer radius;
    ArrayList<Double> coordinates;
    String search;
    int page=0;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public ArrayList<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList getAmenities() {
        return amenities;
    }

    public void setAmenities(ArrayList amenities) {
        this.amenities = amenities;
    }

    public String getLastObjectID() {
        return lastObjectID;
    }

    public void setLastObjectID(String lastObjectID) {
        this.lastObjectID = lastObjectID;
    }
}
