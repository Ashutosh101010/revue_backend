package org.aurd.Admin.adminControllers;


import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;
import org.aurd.Admin.adminModal.request.GetReportedReviewsRequest;
import org.aurd.Admin.adminModal.response.GetReportedReviewsResponse;
import org.aurd.user.constant.Constants;
import org.aurd.user.modal.entity.ReviewModal;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;

import static org.aurd.MongoService.reportCollection;
import static org.aurd.MongoService.reviews;

@Path("admin/reportedReviews")
public class GetReportedReviewsController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GetReportedReviewsResponse getReportedReviewsResponse(GetReportedReviewsRequest getReportedReviewsRequest){
        Document document = new Document();
        Gson gson = new Gson();
//        MongoCursor cursor = reportCollection.find().cursor();
        MongoCursor cursor;

        ArrayList<ObjectId> reportIds=new ArrayList<>();
        if(getReportedReviewsRequest.getPageCount()>0)
        {
            MongoCursor reportCursor = reportCollection.distinct("reviewID",String.class).cursor();

            reportCursor.forEachRemaining(o -> {
           reportIds.add(new ObjectId((String) o));
            });
        }
        else{
            MongoCursor reportCursor  = reportCollection.distinct("reviewID",String.class).cursor();

            reportCursor.forEachRemaining(o -> {
                reportIds.add(new ObjectId((String) o));
            });
        }
        ArrayList<ReviewModal> reviewModals = new ArrayList<>();
//        ArrayList<String> reviewIds = new ArrayList<>();

        Document findDoc=new Document();
        findDoc.append("_id",new Document("$in",reportIds));
//        cursor = reviews.find(findDoc).cursor();

        if(getReportedReviewsRequest.getPageCount()>0)
        {
            cursor = reviews.find(findDoc).sort(new Document("_id",1)).
                    skip(Constants.DOCUMENT_NUMBER_PAGE_REVIEWS * getReportedReviewsRequest.getPageCount()).
                    limit(Constants.DOCUMENT_NUMBER_PAGE_REVIEWS).iterator();

        }
        else{
            cursor = reviews.find(findDoc).sort(new Document("_id",1)).
                    limit(Constants.DOCUMENT_NUMBER_PAGE_REVIEWS).iterator();

        }
        while(cursor.hasNext()){
                    Document reviewDoc = (Document) cursor.next();
                    ReviewModal reviewModal = gson.fromJson(reviewDoc.toJson(), ReviewModal.class);
                    reviewModals.add(reviewModal);
        }
        int totalReviews = Math.toIntExact(reviews.countDocuments(findDoc));
        GetReportedReviewsResponse getReportedReviewsResponse = new GetReportedReviewsResponse();
        getReportedReviewsResponse.setReviews(reviewModals);
        getReportedReviewsResponse.setErrorCode(0);
        getReportedReviewsResponse.setStatus(true);
        getReportedReviewsResponse.setTotalReviews(totalReviews);
        return getReportedReviewsResponse;
    }
}
