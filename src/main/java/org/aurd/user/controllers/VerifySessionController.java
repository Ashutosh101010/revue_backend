package org.aurd.user.controllers;


import com.mongodb.client.MongoCursor;
import org.aurd.user.modal.request.VerifySessionRequest;
import org.aurd.user.modal.response.GeneralResponse;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static org.aurd.MongoService.users;
@Path("/verifySession")
public class VerifySessionController {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GeneralResponse verifySession(VerifySessionRequest request)
    {

        GeneralResponse generalResponse=new GeneralResponse();
       MongoCursor cursor= users.find(new Document("_id",new ObjectId(request.getUserId()))).cursor();

       if(cursor.hasNext())
       {
           Document document= (Document) cursor.next();
           if(document.getString("token").equals(request.getToken()) && document.getBoolean("isLoggedIn")){
               generalResponse.setErrorCode(0);
               generalResponse.setStatus(true);
               generalResponse.setMessage("Logged In True");
               return generalResponse;
           }
           else{
               generalResponse.setErrorCode(2);
               generalResponse.setStatus(false);
               generalResponse.setErrorDescription("session not exists");
               return generalResponse;
           }
       }
       else{
           generalResponse.setErrorCode(1);
           generalResponse.setStatus(false);
           generalResponse.setErrorDescription("User Not Found");
           return generalResponse;
       }
    }
}
