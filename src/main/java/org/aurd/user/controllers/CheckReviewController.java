package org.aurd.user.controllers;

import com.mongodb.client.MongoCursor;

import org.aurd.user.modal.request.CheckReviewRequest;
import org.aurd.user.modal.response.CheckReviewResponse;
import org.bson.Document;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static org.aurd.MongoService.reviews;


@Path("/checkReview")
public class CheckReviewController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CheckReviewResponse checkReview(CheckReviewRequest request){

        Document find=new Document();

        find.append("userID",request.getUserId());
        find.append("compoundID",request.getCompoundId());

       MongoCursor cursor= reviews.find(find).cursor();
       CheckReviewResponse response =new CheckReviewResponse();
       if(cursor.hasNext())
       {
           response.setStatus(true);
           response.setErrorcode(0);
           response.setErrorDescription("");
           response.setReviewExists(true);
           return response;

       }

        response.setStatus(true);
        response.setErrorcode(0);
        response.setErrorDescription("");
        response.setReviewExists(false);
        return response;
    }
}
