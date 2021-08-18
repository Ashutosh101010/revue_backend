package org.aurd.user.controllers;


import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;

import org.aurd.user.constant.Constants;
import org.aurd.user.modal.entity.CompoundModal;
import org.aurd.user.modal.request.FavoriteRequest;
import org.aurd.user.modal.response.FavoriteResponse;
import org.bson.Document;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

import static org.aurd.MongoService.*;


@Path("/getFavorites")
public class GetFavoritesController {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public FavoriteResponse getFavoritesByUser(FavoriteRequest favoriteRequest){
        FavoriteResponse favoriteResponse = new FavoriteResponse();
        Document findDoc = new Document();
        findDoc.append("userID",favoriteRequest.getUserID());
        ArrayList compoundList = new ArrayList();
       MongoCursor cursor = favCollection.find(findDoc).iterator();
       if(cursor.hasNext()){
           Document favDocument = (Document) cursor.next();
           ArrayList arrayList = (ArrayList) favDocument.get("favorites");

           Document document = new Document();
           document.put("_id",new Document("$in",arrayList));
           MongoCursor compoundCursor =  compounds.find(document).iterator();

           while(compoundCursor.hasNext()){
               Document compoundDoc = (Document) compoundCursor.next();
               document.put("reviewCount",
                       reviews.countDocuments(new Document("compoundID",document.get("_id"))));

               CompoundModal compoundModal = new Gson().fromJson(compoundDoc.toJson(),CompoundModal.class);
               compoundList.add(compoundModal);
           }


          favoriteResponse.setCompound(compoundList);
          favoriteResponse.setStatus(Constants.STATUS_SUCCESS);
          favoriteResponse.setErrorcode(Constants.SUCCESS_CODE);
          favoriteResponse.setMessage(Constants.FETCH_FAVORITES_SUCCESS);
          return  favoriteResponse;

       }else{
           favoriteResponse.setCompound(compoundList);
           favoriteResponse.setStatus(Constants.STATUS_FAIL);
           favoriteResponse.setErrorcode(Constants.ERROR_CODE);
           favoriteResponse.setMessage(Constants.FETCH_FAVORITES_FAILURE);
           return  favoriteResponse;
       }

    }
}


