package org.aurd.Admin.adminControllers;

import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;
import org.aurd.Admin.adminModal.entity.AdminReviewModal;
import org.aurd.Admin.adminModal.request.GetReviewRequest;
import org.aurd.Admin.adminModal.response.GetReviewResponse;
import org.aurd.Admin.adminModal.response.UpdateReviewStatusResponse;
import org.aurd.user.constant.Constants;
import org.aurd.user.modal.entity.ReviewModal;
import org.aurd.user.modal.response.GetAllReviewsResponse;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

import static org.aurd.MongoService.compounds;
import static org.aurd.MongoService.reviews;

@Path("admin/reviews")
public class GetReviewsController {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GetReviewResponse getReviews(GetReviewRequest request){
        ArrayList<AdminReviewModal> arrayList = new ArrayList();
        Gson gson = new Gson();
        Document findDoc = new Document();
        MongoCursor cursor;
        if(request.getLastObjectID()!=null && !request.getLastObjectID().isEmpty()){
            findDoc.append("_id",new Document("$gt",new ObjectId(request.getLastObjectID())));
            if(request.getPageCount()>0)
            {
                cursor =  compounds.find(findDoc).sort(new Document("_id",1)).
                        skip(Constants.DOCUMENT_NUMBER_PAGE * request.getPageCount()).
                        limit(Constants.DOCUMENT_NUMBER_PAGE).cursor();

            }
            else{
                cursor =  compounds.find(findDoc).sort(new Document("_id",1)).
                        limit(Constants.DOCUMENT_NUMBER_PAGE).cursor();

            }
        }else {
            if(request.getReviewStatus()!=null && !request.getReviewStatus().isEmpty()){
//            findDoc.append("_id",new Document("$gt",new ObjectId(request.getReviewStatus())));
                findDoc.append("status", request.getReviewStatus());
                cursor = reviews.find(findDoc).sort(new Document("_id",1)).cursor();
            }
            if(findDoc.isEmpty())
            {
                if(request.getPageCount()>0)
                {
                    cursor=compounds.find().sort(new Document("_id",1)).
                            skip(Constants.DOCUMENT_NUMBER_PAGE * request.getPageCount()).limit(Constants.DOCUMENT_NUMBER_PAGE).iterator();
                }
                else{
                    cursor = compounds.find().sort(new Document("_id",1)).
                            limit(Constants.DOCUMENT_NUMBER_PAGE).iterator();

                }
            }else {
                if(request.getPageCount()>0)
                {
                    cursor = compounds.find(findDoc).sort(new Document("_id",1)).
                            skip(Constants.DOCUMENT_NUMBER_PAGE * request.getPageCount()).
                            limit(Constants.DOCUMENT_NUMBER_PAGE).iterator();

                }
                else{
                    cursor = compounds.find(findDoc).sort(new Document("_id",1)).
                            limit(Constants.DOCUMENT_NUMBER_PAGE).iterator();

                }

            }

        }


//        if(request.getReviewStatus()!=null && !request.getReviewStatus().isEmpty()){
////            findDoc.append("_id",new Document("$gt",new ObjectId(request.getReviewStatus())));
//            findDoc.append("status", request.getReviewStatus());
//            cursor = reviews.find(findDoc).sort(new Document("_id",1)).cursor();
//        }else{
//            if(findDoc.isEmpty()){
//                cursor = reviews.find().sort(new Document("_id",1)).iterator();
//
//            }else{
//                cursor = reviews.find(findDoc).sort(new Document("_id",1)).iterator();
//            }
//        }
//

        while(cursor.hasNext()){
            Document doc = (Document) cursor.next();
            AdminReviewModal reviewModal = gson.fromJson(doc.toJson(),AdminReviewModal.class);
            arrayList.add(reviewModal);
        }






        GetReviewResponse getReviewResponse = new GetReviewResponse();
        getReviewResponse.setMessage("Get User Successfully");
        getReviewResponse.setErrorCode(0);
        getReviewResponse.setAdminReviewModals(arrayList);
        getReviewResponse.setStatus(true);



        return  getReviewResponse;

    }



    @GET
//    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GetAllReviewsResponse getAllReviewsResponse(){
        ArrayList<ReviewModal> arrayList =new ArrayList();
        Gson gson =new Gson();
        Document findDoc = new Document();
        MongoCursor cursor;
        if(findDoc.isEmpty()){
            cursor = reviews.find().sort(new Document("_id",1)).iterator();

        }else{
            cursor = reviews.find(findDoc).sort(new Document("_id",1)).iterator();
        }
        while(cursor.hasNext()){
            Document doc = (Document) cursor.next();
            ReviewModal reviewModal = gson.fromJson(doc.toJson(),ReviewModal.class);
            arrayList.add(reviewModal);
        }
        GetAllReviewsResponse getAllReviewsResponse = new GetAllReviewsResponse();
        getAllReviewsResponse.setMessage("Get Reviews Successfully");
        getAllReviewsResponse.setErrorcode(0);
        getAllReviewsResponse.setReviewList(arrayList);
        getAllReviewsResponse.setStatus(true);
        return  getAllReviewsResponse;

    }


    @POST
    @Path("/{reviewId}/{status}")
    @Produces(MediaType.APPLICATION_JSON)
    public UpdateReviewStatusResponse updateReviewStatusResponse(@PathParam("status") int status, @PathParam("reviewId") String reviewId){
        UpdateReviewStatusResponse updateReviewStatusResponse = new UpdateReviewStatusResponse();
        Document doc = new Document("status", status);
        Document findDoc = new Document("_id", new ObjectId(reviewId));
        reviews.findOneAndUpdate(findDoc,new Document("$set",doc));
        updateReviewStatusResponse.setStatus(true);
        updateReviewStatusResponse.setErrorCode(0);
        updateReviewStatusResponse.setMessage("Review updated to " + status + " successfully");
        return updateReviewStatusResponse;
    }
}
