package org.aurd.Admin.adminModal.request;

import org.aurd.user.modal.entity.Position;
import org.jboss.resteasy.annotations.jaxrs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.json.JSONArray;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


public class AddCompoundRequest {

    @FormParam("amenities")
    @PartType(MediaType.TEXT_PLAIN)
    String amenities;

    @FormParam("compoundname")
    @PartType(MediaType.TEXT_PLAIN)
    String compoundname;
    @FormParam("address")
    @PartType(MediaType.TEXT_PLAIN)
    String address;
    @FormParam("description")
    @PartType(MediaType.TEXT_PLAIN)
    String description;
    @FormParam("images")
    @PartType(MediaType.TEXT_PLAIN)
    List<InputPart> images = new ArrayList();
    @FormParam("rating")
    @PartType(MediaType.TEXT_PLAIN)
    double rating;
    @FormParam("design")
    @PartType(MediaType.TEXT_PLAIN)
    int design;
    @FormParam("facility")
    @PartType(MediaType.TEXT_PLAIN)
    int facility;
    @FormParam("location")
    @PartType(MediaType.TEXT_PLAIN)
    int location;
    @FormParam("management")
    @PartType(MediaType.TEXT_PLAIN)
    int management;
    @FormParam("value")
    @PartType(MediaType.TEXT_PLAIN)
    int value;
    @FormParam("category")
    @PartType(MediaType.TEXT_PLAIN)
    String category;
    @FormParam("position")
    @PartType(MediaType.TEXT_PLAIN)
    JSONArray position;



    public JSONArray getPosition() {
        return position;
    }

    public void setPosition(JSONArray position) {
        this.position = position;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getDesign() {
        return design;
    }

    public void setDesign(int design) {
        this.design = design;
    }

    public int getFacility() {
        return facility;
    }

    public void setFacility(int facility) {
        this.facility = facility;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getManagement() {
        return management;
    }

    public void setManagement(int management) {
        this.management = management;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
//    @FormParam("type")
//    @PartType(MediaType.TEXT_PLAIN)
//    String type;


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


}
