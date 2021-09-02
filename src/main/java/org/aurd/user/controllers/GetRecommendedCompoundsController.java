package org.aurd.user.controllers;

import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;
import org.aurd.user.constant.Constants;
import org.aurd.user.modal.entity.CompoundModal;
import org.aurd.user.modal.request.GetRecommendedCompoundsRequest;
import org.aurd.user.modal.response.GetCompoundResponse;
import org.bson.Document;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;

import static org.aurd.MongoService.compounds;
import static org.aurd.MongoService.reviews;

@Path("/getRecommendedCompounds")
public class GetRecommendedCompoundsController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GetCompoundResponse getRecommendedCompoundResponse(GetRecommendedCompoundsRequest request)
    {

        System.out.println(request.getCoordinates());
        Document comDocument=new Document();
        comDocument.append("position",
                new Document("$near",new Document("$geometry",new Document("type","Point").
                        append("coordinates",request.getCoordinates()))
                        .append("$maxDistance", Constants.RECOMMENDED_COMPOUNDS_RADIUS*1000)));

       MongoCursor cursor= compounds.find(comDocument).limit(5).cursor();

        ArrayList arrayList = new ArrayList();
        GetCompoundResponse getCompoundResponse = new GetCompoundResponse();

        if(cursor.hasNext())
        {
            cursor.forEachRemaining(o -> {
                Document document= ((Document) o);
                Long count = reviews.countDocuments(new Document("compoundID",document.get("_id").toString()));

                CompoundModal compoundModal = new Gson().fromJson(document.toJson(),CompoundModal.class);
                compoundModal.setReviewCount(count.intValue());
                arrayList.add(compoundModal);
            });
        }
        else {
            Document find=new Document();
            find.append("position",
                    new Document("$near",new Document("$geometry",new Document("type","Point").
                            append("coordinates",new ArrayList<>(Arrays.asList(Constants.RECOMMENDED_COMPOUNDS_DEFAULT_LATITUDE,Constants.RECOMMENDED_COMPOUNDS_DEFAULT_LONGITUDE))))
                            .append("$maxDistance", Constants.RECOMMENDED_COMPOUNDS_RADIUS*1000)));
            MongoCursor cur= compounds.find(comDocument).limit(5).cursor();
            cur.forEachRemaining(o -> {
                Document document= ((Document) o);
                Long count = reviews.countDocuments(new Document("compoundID",document.get("_id").toString()));

                CompoundModal compoundModal = new Gson().fromJson(document.toJson(),CompoundModal.class);
                compoundModal.setReviewCount(count.intValue());

                arrayList.add(compoundModal);
            });
        }

        System.out.println(arrayList);

        getCompoundResponse.setCompoundList(arrayList);
        getCompoundResponse.setStatus(true);
        getCompoundResponse.setFetchCode(0);
        return getCompoundResponse;

    }
}
