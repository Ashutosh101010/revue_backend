package org.aurd.user.controllers;


import com.mongodb.client.MongoCursor;

import org.aurd.user.constant.Constants;
import org.aurd.user.modal.request.FavoriteRequest;
import org.aurd.user.modal.response.FavoriteResponse;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

import static org.aurd.MongoService.favCollection;

@Path("/removeFavoriteCompound")
public class RemoveFavoriteController {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public FavoriteResponse removeFavoriteCompound(FavoriteRequest favoriteRequest){
        FavoriteResponse favoriteResponse = new FavoriteResponse();
        Document document = new Document();
        document.append("userID",favoriteRequest.getUserID());

      MongoCursor cursor = favCollection.find(document).cursor();
      if(cursor.hasNext()){
       Document doc = (Document) cursor.next();
          ArrayList arrayList = (ArrayList) doc.get("favorites");
          if(!arrayList.isEmpty() &&
                  arrayList.contains(new ObjectId(favoriteRequest.getCompoundID()))){
              arrayList.remove(new ObjectId(favoriteRequest.getCompoundID()));
              favCollection.findOneAndUpdate(document,
                      new Document("$set",new Document("favorites",arrayList)));
          }else if(arrayList.isEmpty()){
              favCollection.findOneAndDelete(doc);
          }

          favoriteResponse.setMessage(Constants.REMOVE_FROM_FAVORITE_SUCCESS);
          favoriteResponse.setStatus(Constants.STATUS_SUCCESS);
          favoriteResponse.setErrorcode(Constants.SUCCESS_CODE);
          return  favoriteResponse;
      }else{
          favoriteResponse.setMessage(Constants.REMOVE_FROM_FAVORITE_FAILURE);
          favoriteResponse.setStatus(Constants.STATUS_FAIL);
          favoriteResponse.setErrorcode(Constants.ERROR_CODE);
          return  favoriteResponse;
      }



    }

}
