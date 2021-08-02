package org.aurd.user.modal.entity;


import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;
import com.mongodb.client.MongoCursor;
import org.aurd.user.utils.ObjectAdapter;
import org.bson.Document;

import java.util.ArrayList;

import static org.aurd.MongoService.reviews;


public class MyReviewModal {


    @JsonAdapter(ObjectAdapter.class)
    String _id;
    String reviewerName;
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
    String reviewDate;
    String compoundName;
//    @JsonAdapter(LongAdapter.class)
    Long timestamp;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getFloorplan() {
        return floorplan;
    }

    public void setFloorplan(String floorplan) {
        this.floorplan = floorplan;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public ArrayList<String> getCons() {
        return cons;
    }

    public void setCons(ArrayList<String> cons) {
        this.cons = cons;
    }

    public ArrayList<String> getPros() {
        return pros;
    }

    public void setPros(ArrayList<String> pros) {
        this.pros = pros;
    }

    public int getBedRooms() {
        return bedRooms;
    }

    public void setBedRooms(int bedRooms) {
        this.bedRooms = bedRooms;
    }

    public int getBathRooms() {
        return bathRooms;
    }

    public void setBathRooms(int bathRooms) {
        this.bathRooms = bathRooms;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public double getFacility() {
        return facility;
    }

    public void setFacility(double facility) {
        this.facility = facility;
    }

    public double getLocation() {
        return location;
    }

    public void setLocation(double location) {
        this.location = location;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getManagement() {
        return management;
    }

    public void setManagement(double management) {
        this.management = management;
    }

    public double getDesign() {
        return design;
    }

    public void setDesign(double design) {
        this.design = design;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getCompoundName() {
        return compoundName;
    }

    public void setCompoundName(String compoundName) {
        this.compoundName = compoundName;
    }



    public static ArrayList getParticularUserReview(String userid){
        ArrayList arrayList = new ArrayList();
        Document userDoc = new Document();
        userDoc.append("userID",userid);
        MongoCursor cursor =  reviews.find(userDoc).iterator();
        while(cursor.hasNext()){
            Document doc = (Document) cursor.next();
            MyReviewModal myReviewModal = new Gson().fromJson(doc.toJson(),MyReviewModal.class);
            arrayList.add(myReviewModal);
        }
        return  arrayList;

    }
}
