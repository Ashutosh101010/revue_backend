package org.aurd.Admin.adminControllers;


import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;
import org.aurd.Admin.adminModal.entity.PropertyModal;
import org.aurd.Admin.adminModal.response.GetPropertiesResponse;
import org.aurd.user.modal.entity.ReviewModal;
import org.bson.Document;

import javax.print.Doc;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

import static org.aurd.MongoService.compounds;
import static org.aurd.MongoService.reviews;

@Path("/admin/properties")
public class GetPropertiesController {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GetPropertiesResponse getPropertiesResponse(){
        ArrayList<PropertyModal> arrayList =new ArrayList();
        Gson gson =new Gson();
        Document findDoc = new Document();
        MongoCursor cursor;
        cursor = compounds.find().sort(new Document("_id",1)).iterator();
        while(cursor.hasNext()){
            Document doc = (Document) cursor.next();
            PropertyModal propertyModal = gson.fromJson(doc.toJson(),PropertyModal.class);
            ArrayList reviewList = ReviewModal.getAllReviews(propertyModal.get_id());
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
}
