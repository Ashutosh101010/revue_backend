package org.aurd.user.controllers;


import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;

import org.aurd.user.constant.Constants;
import org.aurd.user.modal.entity.CompoundModal;
import org.aurd.user.modal.request.GetCompoundDetailsRequest;
import org.aurd.user.modal.response.GetCompoundDetailResponse;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static org.aurd.MongoService.compounds;


@Path("/getCompoundDetails")
public class GetCompoundDetailsController {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public GetCompoundDetailResponse compoundDetails(GetCompoundDetailsRequest compoundDetailsRequest){
      System.out.println(compoundDetailsRequest.getId());
        MongoCursor cursor =  compounds.find(new Document("_id",new ObjectId(compoundDetailsRequest.getId()))).cursor();
        GetCompoundDetailResponse getCompoundDetailResponse = new GetCompoundDetailResponse();
        if(cursor.hasNext()){
            Document doc = (Document) cursor.next();

            getCompoundDetailResponse.setStatus(Constants.STATUS_SUCCESS);
            getCompoundDetailResponse.setErrorCode(Constants.SUCCESS_CODE);
            CompoundModal compoundModal = new Gson().fromJson(doc.toJson(),CompoundModal.class);
            getCompoundDetailResponse.setCompoundModal(compoundModal);
            return getCompoundDetailResponse;
        }else{
            getCompoundDetailResponse.setStatus(Constants.STATUS_FAIL);
            getCompoundDetailResponse.setErrorCode(Constants.ERROR_CODE);
            return getCompoundDetailResponse;
        }
    }


}
