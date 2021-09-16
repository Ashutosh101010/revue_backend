package org.aurd.Admin.adminControllers;


import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;
import org.aurd.Admin.adminModal.entity.PropertyModal;
import org.aurd.Admin.adminModal.request.GetPropertiesRequest;
import org.aurd.Admin.adminModal.response.GetPropertiesResponse;
import org.aurd.user.constant.Constants;
import org.aurd.user.modal.entity.ReviewModal;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static org.aurd.MongoService.compounds;
import static org.aurd.MongoService.reviews;

@Path("/admin/properties")
    public class GetPropertiesController {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GetPropertiesResponse getPropertiesResponse(GetPropertiesRequest request){
        ArrayList<PropertyModal> arrayList =new ArrayList();
        Gson gson =new Gson();
        Document findDoc = new Document();
        MongoCursor cursor;

        int compoundCount=Math.toIntExact(compounds.countDocuments(findDoc));
        if(request.getPropertyId() !=null && !request.getPropertyId().isEmpty()){
            findDoc.append("_id",new Document("$gt",new ObjectId(request.getPropertyId())));
            if(request.getPage()>0)
            {
                cursor =  compounds.find(findDoc).sort(new Document("_id",1)).skip(Constants.DOCUMENT_NUMBER_PAGE * request.getPage()).limit(Constants.DOCUMENT_NUMBER_PAGE).cursor();

            }
            else{
                cursor =  compounds.find(findDoc).sort(new Document("_id",1)).limit(Constants.DOCUMENT_NUMBER_PAGE).cursor();

            }
        }else{
            if(findDoc.isEmpty())
            {
                if(request.getPage()>0)
                {
                    cursor=compounds.find().sort(new Document("_id",1)).skip(Constants.DOCUMENT_NUMBER_PAGE * request.getPage()).limit(Constants.DOCUMENT_NUMBER_PAGE).iterator();
                }
                else{
                    cursor = compounds.find().sort(new Document("_id",1)).limit(Constants.DOCUMENT_NUMBER_PAGE).iterator();

                }
            }else {

                if(request.getPage()>0)
                {
                    cursor = compounds.find(findDoc).sort(new Document("_id",1)).skip(Constants.DOCUMENT_NUMBER_PAGE * request.getPage()).limit(Constants.DOCUMENT_NUMBER_PAGE).iterator();

                }
                else{
                    cursor = compounds.find(findDoc).sort(new Document("_id",1)).limit(Constants.DOCUMENT_NUMBER_PAGE).iterator();

                }

            }

        }



//        cursor = compounds.find().sort(new Document("_id",1)).iterator();
        while(cursor.hasNext()){
            Document doc = (Document) cursor.next();
            PropertyModal propertyModal = gson.fromJson(doc.toJson(),PropertyModal.class);
            ArrayList reviewList = ReviewModal.getAllReviews(propertyModal.get_id());
            int count = Math.toIntExact(reviews.countDocuments(new Document("compoundID", propertyModal.get_id())));
            propertyModal.setReviewCount(count);
            propertyModal.setReviews(reviewList);
            arrayList.add(propertyModal);
        }

        GetPropertiesResponse getPropertiesResponse = new GetPropertiesResponse();
        getPropertiesResponse.setErrorCode(0);
        getPropertiesResponse.setMessage("Properties are fetched successfully");
        getPropertiesResponse.setPropertyModals(arrayList);
        getPropertiesResponse.setStatus(true);
        getPropertiesResponse.setCount(compoundCount);
        return getPropertiesResponse;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{search}")
    public GetPropertiesResponse getPropertiesResponse(@PathParam("search") String search){
        ArrayList<PropertyModal> arrayList =new ArrayList();
        Gson gson =new Gson();
        Document findDoc = new Document();
        MongoCursor cursor;
        if(search!=null){
            ArrayList orList = new ArrayList();
            orList.add(new Document("compoundname", new Document("$regex", Pattern.compile(search,Pattern.CASE_INSENSITIVE))));
            orList.add(new Document("address", new Document("$regex", Pattern.compile(search,Pattern.CASE_INSENSITIVE))));
            orList.add(new Document("description", new Document("$regex", Pattern.compile(search,Pattern.CASE_INSENSITIVE))));
            findDoc.append("$or",orList);
        }
        cursor = compounds.find(findDoc).sort(new Document("_id",1)).iterator();
        while(cursor.hasNext()){
            Document doc = (Document) cursor.next();
            PropertyModal propertyModal = gson.fromJson(doc.toJson(),PropertyModal.class);
            ArrayList reviewList = ReviewModal.getAllReviews(propertyModal.get_id());
            int count = Math.toIntExact(reviews.countDocuments(new Document("compoundID", propertyModal.get_id())));
            propertyModal.setReviewCount(count);
            propertyModal.setReviews(reviewList);
            arrayList.add(propertyModal);
        }

        GetPropertiesResponse getPropertiesResponse = new GetPropertiesResponse();
        getPropertiesResponse.setErrorCode(0);
        getPropertiesResponse.setMessage("Properties are fetched successfully");
        getPropertiesResponse.setPropertyModals(arrayList);
        getPropertiesResponse.setStatus(true);
        return getPropertiesResponse;

    }





//    @DELETE
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/{propertyId}")
//    public GetPropertiesResponse getDeletePropertyResponse(@PathParam("propertyId") String propertyId){
//        GetPropertiesResponse propertiesResponse = new GetPropertiesResponse();
//        Document document = new Document();
//        document.append("_id",new ObjectId(propertyId));
//        Document findReviewsOfParticularProperty = new Document();
//        findReviewsOfParticularProperty.append("compoundID", propertyId);
//
//        boolean doesPropertyExist = compounds.find(document).cursor().hasNext();
//        if(doesPropertyExist){
//            compounds.findOneAndDelete(document);
//            reviews.deleteMany(findReviewsOfParticularProperty);
//            propertiesResponse.setStatus(true);
//            propertiesResponse.setErrorCode(0);
//            propertiesResponse.setMessage("Property Deleted");
//        }else{
//            propertiesResponse.setStatus(false);
//            propertiesResponse.setErrorCode(1);
//        }
//
//        return  propertiesResponse;
//    }
    @POST
    @Consumes
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteProperty")
    public GetPropertiesResponse getDeletePropertyResponse(GetPropertiesRequest getPropertiesRequest){
        GetPropertiesResponse propertiesResponse = new GetPropertiesResponse();
        Document updateDoc = new Document();
        updateDoc.append("activeStatus", false);
        Document document = new Document();
        document.append("_id",new ObjectId(getPropertiesRequest.getPropertyId()));
        boolean doesPropertyExist = compounds.find(document).cursor().hasNext();
        if(doesPropertyExist){
            compounds.findOneAndUpdate(document,new Document("$set",updateDoc));
            propertiesResponse.setStatus(true);
            propertiesResponse.setErrorCode(0);
            propertiesResponse.setMessage("Property Deleted");
        }else{
            propertiesResponse.setStatus(false);
            propertiesResponse.setErrorCode(1);
        }
        return  propertiesResponse;
    }



}
