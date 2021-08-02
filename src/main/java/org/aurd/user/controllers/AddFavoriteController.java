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


@Path("/addFavoriteCompound")
public class AddFavoriteController {


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public FavoriteResponse addFavorite(FavoriteRequest addFavoriteRequest){
        Document document = new Document();
        document.put("userID",addFavoriteRequest.getUserID());
   MongoCursor cursor  =   favCollection.find(document).cursor();
      if(cursor.hasNext()){
       Document doc = (Document) cursor.next();
        ArrayList arrayList = (ArrayList) doc.get("favorites");
        arrayList.add(new ObjectId(addFavoriteRequest.getCompoundID()));
        favCollection.findOneAndUpdate(document,
                new Document("$set",new Document("favorites",arrayList)));

      }
      else{

          ArrayList arrayList = new ArrayList();
          arrayList.add(new ObjectId(addFavoriteRequest.getCompoundID()));
          document.put("favorites",arrayList);
          favCollection.insertOne(document);
      }
        FavoriteResponse addFavoriteResponse =new FavoriteResponse() ;
        addFavoriteResponse.setErrorcode(Constants.ERROR_CODE);
        addFavoriteResponse.setStatus(Constants.STATUS_SUCCESS);
        addFavoriteResponse.setMessage(Constants.ADD_TO_FAVORITE);

        return  addFavoriteResponse;

    }
}
