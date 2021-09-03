package org.aurd.Admin.adminModal.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;
import com.mongodb.client.MongoCursor;
import org.aurd.user.modal.entity.ReviewModal;
import org.aurd.user.utils.ObjectAdapter;
import org.bson.Document;

import java.util.ArrayList;

import static org.aurd.MongoService.reviews;

public class AdminReviewModal {
    @JsonAdapter(ObjectAdapter.class)
    String _id;
    String reviewerName;
    String compoundID;
    String review;
    String floorplan;
    String price;
    double rating;
    ArrayList<String> cons;
    ArrayList<String> pros;
    int bedRooms;
    int bathRooms;
    String userID;
    double facility;
    double location;
    double value;
    double management;
    double design;
    ArrayList<String> images;
    //    @JsonAdapter(LongAdapter.class)
    Long timestamp;
    String compoundName;

    public String get_id() {
        return _id;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public String getCompoundID() {
        return compoundID;
    }

    public String getReview() {
        return review;
    }

    public String getFloorplan() {
        return floorplan;
    }

    public String getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public ArrayList<String> getCons() {
        return cons;
    }

    public ArrayList<String> getPros() {
        return pros;
    }

    public int getBedRooms() {
        return bedRooms;
    }

    public int getBathRooms() {
        return bathRooms;
    }

    public String getUserID() {
        return userID;
    }

    public double getFacility() {
        return facility;
    }

    public double getLocation() {
        return location;
    }

    public double getValue() {
        return value;
    }

    public double getManagement() {
        return management;
    }

    public double getDesign() {
        return design;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getCompoundName() {
        return compoundName;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public void setCompoundID(String compoundID) {
        this.compoundID = compoundID;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setFloorplan(String floorplan) {
        this.floorplan = floorplan;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setCons(ArrayList<String> cons) {
        this.cons = cons;
    }

    public void setPros(ArrayList<String> pros) {
        this.pros = pros;
    }

    public void setBedRooms(int bedRooms) {
        this.bedRooms = bedRooms;
    }

    public void setBathRooms(int bathRooms) {
        this.bathRooms = bathRooms;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setFacility(double facility) {
        this.facility = facility;
    }

    public void setLocation(double location) {
        this.location = location;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setManagement(double management) {
        this.management = management;
    }

    public void setDesign(double design) {
        this.design = design;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setCompoundName(String compoundName) {
        this.compoundName = compoundName;
    }


    public static  ArrayList getAllReviews(String cID){
        Document document = new Document();
        document.append("compoundID",cID);
        ArrayList arrayList = new ArrayList();
        MongoCursor mongoCursor = reviews.find(document).iterator();
        while (mongoCursor.hasNext()){
            Document reviewDoc = (Document) mongoCursor.next();
            ReviewModal reviewModal = new Gson().fromJson(reviewDoc.toJson(),ReviewModal.class);
            arrayList.add(reviewModal);
        }
        return arrayList;
    }
}
