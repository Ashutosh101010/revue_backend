package org.aurd.user.controllers;

import com.mongodb.client.MongoCursor;
import org.aurd.user.modal.request.VerifyEmailRequest;
import org.aurd.user.modal.response.GeneralResponse;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static org.aurd.MongoService.users;
@Path("/verifyEmail")
public class VerifyEmailController {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public GeneralResponse verifyEmail(VerifyEmailRequest request)
    {
       MongoCursor cursor = users.find(new Document("_id",new ObjectId(request.getUserId()))).cursor();


       GeneralResponse response=new GeneralResponse();
       if(cursor.hasNext())
       {
           Document user = (Document) cursor.next();
           String token=user.getString("token");

           if(request.getToken().equals(token))
           {
               users.findOneAndUpdate(new Document("_id",new ObjectId(request.getUserId())),new Document("$set",new Document("verified",true)));
response.setStatus(true);
response.setErrorCode(0);
response.setMessage("verified");
return response;
           }else{
               response.setStatus(true);
               response.setErrorCode(10);
               response.setMessage("email not verified");
               return response;
           }

       }
       else{
           response.setErrorCode(3);
           response.setStatus(false);
           response.setMessage("user not found");
           return response;
       }


    }
}
