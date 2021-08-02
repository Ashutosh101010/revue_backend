package org.aurd.user.modal.request;

import org.jboss.resteasy.annotations.jaxrs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;

import javax.ws.rs.core.MediaType;

import java.util.List;

public class AddReviewRequest {
//
//    @FormParam("imageData")
//    @PartType(MediaType.APPLICATION_OCTET_STREAM)
//    public List<InputPart> list;

    @FormParam("review")
    @PartType(MediaType.TEXT_PLAIN)
    public String review;

 @FormParam("userId")
    @PartType(MediaType.TEXT_PLAIN)
    public String userId;

@FormParam("rent")
    @PartType(MediaType.TEXT_PLAIN)
    public String rent;
@FormParam("pros")
    @PartType(MediaType.TEXT_PLAIN)
    public String pros;

@FormParam("cons")
    @PartType(MediaType.TEXT_PLAIN)
    public String cons;
@FormParam("floorplan")
    @PartType(MediaType.TEXT_PLAIN)
    public String floorplan;
@FormParam("reviewername")
    @PartType(MediaType.TEXT_PLAIN)
    public String reviewername;
@FormParam("compoundID")
    @PartType(MediaType.TEXT_PLAIN)
    public String compoundID;

@FormParam("facility")
    @PartType(MediaType.TEXT_PLAIN)
    public String facility;
@FormParam("value")
    @PartType(MediaType.TEXT_PLAIN)
    public String value;

    @FormParam("location")
    @PartType(MediaType.TEXT_PLAIN)
    public String location;

     @FormParam("management")
    @PartType(MediaType.TEXT_PLAIN)
    public String management;
     @FormParam("design")
    @PartType(MediaType.TEXT_PLAIN)
    public String design;
     @FormParam("rating")
    @PartType(MediaType.TEXT_PLAIN)
    public String rating;
    @FormParam("timestamp")
    @PartType(MediaType.TEXT_PLAIN)
    public String timestamp;
    @FormParam("compoundName")
    @PartType(MediaType.TEXT_PLAIN)
    public String compoundName;
    @FormParam("bedRooms")
    @PartType(MediaType.TEXT_PLAIN)
    public String bedRooms;
    @FormParam("bathRooms")
    @PartType(MediaType.TEXT_PLAIN)
    public String bathRooms;

//    public List<InputPart> getList() {
//        return list;
//    }
//
//    public void setList(List<InputPart> list) {
//        this.list = list;
//    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getPros() {
        return pros;
    }

    public void setPros(String pros) {
        this.pros = pros;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }

    public String getFloorplan() {
        return floorplan;
    }

    public void setFloorplan(String floorplan) {
        this.floorplan = floorplan;
    }

    public String getReviewername() {
        return reviewername;
    }

    public void setReviewername(String reviewername) {
        this.reviewername = reviewername;
    }

    public String getCompoundID() {
        return compoundID;
    }

    public void setCompoundID(String compoundID) {
        this.compoundID = compoundID;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCompoundName() {
        return compoundName;
    }

    public void setCompoundName(String compoundName) {
        this.compoundName = compoundName;
    }

    public String getBedRooms() {
        return bedRooms;
    }

    public void setBedRooms(String bedRooms) {
        this.bedRooms = bedRooms;
    }

    public String getBathRooms() {
        return bathRooms;
    }

    public void setBathRooms(String bathRooms) {
        this.bathRooms = bathRooms;
    }
}
