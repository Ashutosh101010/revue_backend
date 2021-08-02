package org.aurd.user.controllers;



import org.aurd.user.constant.Constants;
import org.aurd.user.modal.request.UpdatePasswordRequest;
import org.aurd.user.modal.response.UpdatePasswordResponse;
import org.bson.Document;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static org.aurd.MongoService.users;


@Path("/updatePassword")
public class UpdatePasswordController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UpdatePasswordResponse updatePassword(UpdatePasswordRequest request)
    {

        users.findOneAndUpdate(new Document("email",request.getEmail()),new Document("$set",new Document("password",request.getPassword())));
        UpdatePasswordResponse response=new UpdatePasswordResponse();
        response.setErrorCode(0);
        response.setStatus(Constants.STATUS_SUCCESS);
        response.setMessage("Password Updated Successfully");

        return response;
    }
}
