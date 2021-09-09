package org.aurd.Admin.adminModal.request;

import org.aurd.user.modal.entity.Position;
import org.jboss.resteasy.annotations.jaxrs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


public class AddCompoundRequest {
    @FormParam("compoundname")
    @PartType(MediaType.TEXT_PLAIN)
    String compoundname;
    @FormParam("address")
    @PartType(MediaType.TEXT_PLAIN)
    String address;
    @FormParam("amenities")
    @PartType(MediaType.TEXT_PLAIN)
    ArrayList amenities = new ArrayList();
    @FormParam("images")
    @PartType(MediaType.TEXT_PLAIN)
    List<InputPart> images = new ArrayList();
    @FormParam("rating")
    @PartType(MediaType.TEXT_PLAIN)
    double rating;
    @FormParam("description")
    @PartType(MediaType.TEXT_PLAIN)
    String description;
    @FormParam("type")
    @PartType(MediaType.TEXT_PLAIN)
    String type;


// List Of Compound Model .. Needs to be checked and update it to the request modal
//    String compoundname;
//    String address;
//    String description;
//    ArrayList amenities = new ArrayList();
//    double rating;
//    ArrayList images = new ArrayList();
//    double facility;
//    double management;
//    double value;
//    double location;
//    double design;
//    Position position;
//    String category;
//    int reviewCount;
//    double excellent;
//    double veryGood;
//    double average;
//    double poor;
//    double veryPoor;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public List<InputPart> getImages() {
        return images;
    }

    public void setImages(List<InputPart> images) {
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
