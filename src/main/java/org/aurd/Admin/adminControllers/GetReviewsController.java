package org.aurd.Admin.adminControllers;

import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;
import org.aurd.Admin.adminModal.entity.AdminReviewModal;
import org.aurd.Admin.adminModal.request.GetReviewRequest;
import org.aurd.Admin.adminModal.response.GetReviewResponse;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

import static org.aurd.MongoService.reviews;

@Path("admin/getReviews")
public class GetReviewsController {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GetReviewResponse getReviews(GetReviewRequest request){
        ArrayList<AdminReviewModal> arrayList = new ArrayList();
        Gson gson = new Gson();
        Document findDoc = new Document();
        MongoCursor cursor;
        if(request.getReviewStatus()!=null && !request.getReviewStatus().isEmpty()){
            findDoc.append("_id",new Document("$gt",new ObjectId(request.getReviewStatus())));
            cursor = reviews.find(findDoc).sort(new Document("_id",1)).limit(8).cursor();

        }else{
            if(findDoc.isEmpty()){
                cursor = reviews.find().sort(new Document("_id",1)).limit(8).iterator();

            }else{
                cursor = reviews.find(findDoc).sort(new Document("_id",1)).limit(8).iterator();
            }
        }

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
}
