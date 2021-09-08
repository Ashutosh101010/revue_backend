package org.aurd.user.modal.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;
import com.mongodb.client.MongoCursor;
import org.aurd.user.constant.Constants;
import org.aurd.user.modal.response.AddReviewResponse;
import org.aurd.user.utils.ObjectAdapter;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;

import static org.aurd.MongoService.compounds;
import static org.aurd.MongoService.reviews;


public class ReviewModal {


    @JsonAdapter(ObjectAdapter.class)
    String _id;
    String reviewerName;
    String compoundID;
    String review;
    String floorplan;
    String price;
    int status;
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
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCompoundName() {
        return compoundName;
    }

    public void setCompoundName(String compoundName) {
        this.compoundName = compoundName;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList getImages() {
        return images;
    }

    public void setImages(ArrayList images) {
        this.images = images;
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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public ArrayList getCons() {
        return cons;
    }

    public void setCons(ArrayList cons) {
        this.cons = cons;
    }

    public ArrayList getPros() {
        return pros;
    }

    public void setPros(ArrayList pros) {
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

    public String getCompoundID() {
        return compoundID;
    }

    public void setCompoundID(String compoundID) {
        this.compoundID = compoundID;
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



    public AddReviewResponse addReviewtoDB(){

        //insert review

        Document doc = Document.parse(new Gson().toJson(this));
        reviews.insertOne(doc);


        //fetch all review with respect to compoundID;
        Document reviewDoc = new Document();
        reviewDoc.append("compoundID",compoundID);
       MongoCursor reviewCursor = reviews.find(reviewDoc).iterator();
       int count = 0;  double totalFacility =0.0;double totalLocation =0.0;double totalManagement =0.0;
       double totalDesign =0.0;double totalValue =0.0;
        double fac=0.0;double loc=0.0;double val=0.0;double man=0.0;double des =0.0;
      while(reviewCursor.hasNext()){
          count++;
          Document d = (Document) reviewCursor.next();
        fac = fac+d.getDouble("facility");
        loc = loc+d.getDouble("location");
        des = des +d.getDouble("design");
        man = man+d.getDouble("management");
        val = val +d.getDouble("value");
      }
      totalFacility = fac/count;
      totalLocation = loc/count;
      totalDesign = des/count;
      totalManagement = man/count;
      totalValue = val/count;

      double totalRating = (totalFacility+totalDesign+totalLocation+totalValue+totalManagement)/5;
        //update compound document
        Document compoundDocument = new Document();
        compoundDocument.append("_id",new ObjectId(compoundID));
        Document document  = (Document) compounds.find(compoundDocument).cursor().next();

        Document updateDocument = new Document();
        updateDocument.put("facility",totalFacility);
        updateDocument.put("location",totalLocation);
        updateDocument.put("value",totalValue);
        updateDocument.put("design",totalDesign);
        updateDocument.put("management",totalManagement);
        updateDocument.put("rating",totalRating);


        compounds.findOneAndUpdate(new Document("_id",new ObjectId(compoundID)),
                new Document("$set",updateDocument));


        //send response
        AddReviewResponse addReviewResponse = new AddReviewResponse();
        addReviewResponse.setErrorcode(Constants.ERROR_CODE);
        addReviewResponse.setMessage(Constants.ADD_REVIEW_SUCCESS);
        addReviewResponse.setStatus(Constants.STATUS_SUCCESS);
        return  addReviewResponse;
    }


}
