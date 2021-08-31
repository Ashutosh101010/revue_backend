package org.aurd.Admin.adminModal.request;

import java.util.ArrayList;

public class AddCompoundRequest {
    String compoundname;
    String address;
    ArrayList amenities = new ArrayList();
    ArrayList images = new ArrayList();
    double rating;
    String description;

    public String getCompoundname() {
        return compoundname;
    }

    public void setCompoundname(String compoundname) {
        this.compoundname = compoundname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList getAmenities() {
        return amenities;
    }

    public void setAmenities(ArrayList amenities) {
        this.amenities = amenities;
    }

    public ArrayList getImages() {
        return images;
    }

    public void setImages(ArrayList images) {
        this.images = images;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
