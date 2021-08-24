package org.aurd.user.modal.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;
import org.aurd.Admin.adminModal.AddCompoundResponse;
import org.aurd.user.constant.Constants;
import org.aurd.user.utils.ObjectAdapter;
import org.bson.Document;

import java.util.ArrayList;

import static org.aurd.MongoService.compounds;

public class CompoundModal {
    @JsonAdapter(ObjectAdapter.class)
    String _id;
    String compoundname;
    String address;
    String description;
    ArrayList amenities = new ArrayList();
    double rating;
    ArrayList images = new ArrayList();
    double facility;
    double management;
    double value;
    double location;
    double design;
    Position position;
    String category;
    int reviewCount;
    double excellent;
    double veryGood;
    double average;
    double poor;
    double veryPoor;

    public double getExcellent() {
        return excellent;
    }

    public void setExcellent(double excellent) {
        this.excellent = excellent;
    }

    public double getVeryGood() {
        return veryGood;
    }

    public void setVeryGood(double veryGood) {
        this.veryGood = veryGood;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getPoor() {
        return poor;
    }

    public void setPoor(double poor) {
        this.poor = poor;
    }

    public double getVeryPoor() {
        return veryPoor;
    }

    public void setVeryPoor(double veryPoor) {
        this.veryPoor = veryPoor;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public double getDesign() {
        return design;
    }

    public void setDesign(double design) {
        this.design = design;
    }

    public double getFacility() {
        return facility;
    }

    public void setFacility(double facility) {
        this.facility = facility;
    }

    public double getManagement() {
        return management;
    }

    public void setManagement(double management) {
        this.management = management;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getLocation() {
        return location;
    }

    public void setLocation(double location) {
        this.location = location;
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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public ArrayList getAmenities() {
        return amenities;
    }

    public void setAmenities(ArrayList amenities) {
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



    public  static AddCompoundResponse addCompoundToDB(){
        Document doc = Document.parse(new Gson().toJson(CompoundModal.class));
        compounds.insertOne(doc);

        AddCompoundResponse addCompoundResponse = new AddCompoundResponse();
        addCompoundResponse.setMessage(Constants.ADD_COMPOUND_SUCCESS);
        addCompoundResponse.setStatus(Constants.STATUS_SUCCESS);
        addCompoundResponse.setErrorcode(Constants.ERROR_CODE);
        return  addCompoundResponse;

    }

}


