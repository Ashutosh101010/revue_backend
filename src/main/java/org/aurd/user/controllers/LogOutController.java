package org.aurd.user.controllers;

import org.aurd.user.modal.request.LogOutRequest;
import org.aurd.user.modal.response.GeneralResponse;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static org.aurd.MongoService.users;

@Path("/logOut")
public class LogOutController {


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public GeneralResponse logOut(LogOutRequest request)
    {
        users.findOneAndUpdate(new Document("_id",new ObjectId(request.getUserId())),new Document("$set",new Document("isLoggedIn",false)));
        GeneralResponse response=new GeneralResponse();
        response.setErrorCode(0);
        response.setStatus(true);
        return response;
    }
}
