package org.aurd.user.controllers;

import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;

import org.aurd.user.constant.Constants;
import org.aurd.user.modal.entity.CompoundModal;
import org.aurd.user.modal.request.GetCompoundRequest;
import org.aurd.user.modal.response.GetCompoundResponse;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

import static org.aurd.MongoService.compounds;
import static org.aurd.MongoService.reviews;


@Path("/getCompound")

public class GetCompoundController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public GetCompoundResponse getCompoundResponse(GetCompoundRequest getCompoundRequest){
        MongoCursor mongoCursor;
        Document comDocument = new Document();
        if(getCompoundRequest.getCategory()!=null
                && !getCompoundRequest.getCategory().equals("Any")
        && !getCompoundRequest.getCategory().isEmpty()){
            comDocument.append("category",getCompoundRequest.getCategory());
        }
        if(!getCompoundRequest.getAmenities().isEmpty()
                && !getCompoundRequest.getAmenities().contains("Any")){
            comDocument.append("amenities",new Document("$all",getCompoundRequest.getAmenities()));
        }

        if(getCompoundRequest.getSearch()!=null && !getCompoundRequest.getSearch().isEmpty())
        {
            Document queryDoc1 = new Document();
            queryDoc1.append("compoundname",new Document().append("$regex",getCompoundRequest.getSearch()).append("$options","i"));

            Document queryDoc2 = new Document();
            queryDoc2.append("address",new Document().append("$regex",getCompoundRequest.getSearch()).append("$options","i"));

            Document searchDoc= new Document();
            ArrayList docArrayList= new ArrayList();
            docArrayList.add(queryDoc1);
            docArrayList.add(queryDoc2);

            comDocument.append("$or",docArrayList);
        }
        if(getCompoundRequest.getRadius()!=null && getCompoundRequest.getRadius()<25)
        {
            comDocument.append("position",
                    new Document("$near",new Document("$geometry",new Document("type","Point").
                            append("coordinates",getCompoundRequest.getCoordinates()))
                    .append("$maxDistance",getCompoundRequest.getRadius()*1000)));
        }

int compoundCount=Math.toIntExact(compounds.countDocuments(comDocument));
        if(getCompoundRequest.getLastObjectID() !=null && !getCompoundRequest.getLastObjectID().isEmpty()){
            comDocument.append("_id",new Document("$gt",new ObjectId(getCompoundRequest.getLastObjectID())));
            if(getCompoundRequest.getPage()>0)
            {
                mongoCursor =  compounds.find(comDocument).sort(new Document("_id",1)).skip(Constants.DOCUMENT_NUMBER_PAGE * getCompoundRequest.getPage()).limit(Constants.DOCUMENT_NUMBER_PAGE).cursor();

            }
            else{
                mongoCursor =  compounds.find(comDocument).sort(new Document("_id",1)).limit(Constants.DOCUMENT_NUMBER_PAGE).cursor();

            }
                }else{
            if(comDocument.isEmpty())
            {
                if(getCompoundRequest.getPage()>0)
                {
                    mongoCursor=compounds.find().sort(new Document("_id",1)).skip(Constants.DOCUMENT_NUMBER_PAGE * getCompoundRequest.getPage()).limit(Constants.DOCUMENT_NUMBER_PAGE).iterator();
                }
                else{
                    mongoCursor = compounds.find().sort(new Document("_id",1)).limit(Constants.DOCUMENT_NUMBER_PAGE).iterator();

                }
                   }else {

                if(getCompoundRequest.getPage()>0)
                {
                    mongoCursor = compounds.find(comDocument).sort(new Document("_id",1)).skip(Constants.DOCUMENT_NUMBER_PAGE * getCompoundRequest.getPage()).limit(Constants.DOCUMENT_NUMBER_PAGE).iterator();

                }
                else{
                    mongoCursor = compounds.find(comDocument).sort(new Document("_id",1)).limit(Constants.DOCUMENT_NUMBER_PAGE).iterator();

                }

                         }

        }

        System.out.println(comDocument.toJson());

        ArrayList arrayList = new ArrayList();
        GetCompoundResponse getCompoundResponse = new GetCompoundResponse();

        if(mongoCursor.hasNext()){
            while (mongoCursor.hasNext()){
                Document document = (Document) mongoCursor.next();
                Long count = reviews.countDocuments(new Document("compoundID",document.get("_id").toString()));
                CompoundModal compoundModal = new Gson().fromJson(document.toJson(),CompoundModal.class);
                compoundModal.setReviewCount(count.intValue());

                arrayList.add(compoundModal);
            }
            getCompoundResponse.setCount(compoundCount);
            getCompoundResponse.setStatus(Constants.STATUS_SUCCESS);
            getCompoundResponse.setMessage(Constants.GET_COMPOUNDLIST_SUCCESS);
            getCompoundResponse.setCompoundList(arrayList);
            getCompoundResponse.setFetchCode(Constants.SUCCESS_FETCH_COMPOUNDLIST);

            return getCompoundResponse;
        }
        else{
            getCompoundResponse.setStatus(Constants.STATUS_FAIL);
            getCompoundResponse.setMessage(Constants.GET_COMPOUNDLIST_FAILED);
            getCompoundResponse.setFetchCode(Constants.FAILED_FETCH_COMPOUNDLIST);

            return getCompoundResponse;
        }


    }


}
