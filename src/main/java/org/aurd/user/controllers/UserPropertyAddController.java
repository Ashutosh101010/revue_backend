package org.aurd.user.controllers;


import com.google.gson.Gson;
import org.aurd.user.modal.request.UserPropertyAddRequest;
import org.aurd.user.modal.response.GeneralResponse;
import org.bson.Document;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static org.aurd.MongoService.userPropertyCollection;

@Path("/addPropertyUser")
public class UserPropertyAddController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GeneralResponse addProperty(UserPropertyAddRequest request)
    {

        Gson gson=new Gson();
        Document document=Document.parse(gson.toJson(request));
        userPropertyCollection.insertOne(document);

        GeneralResponse generalResponse=new GeneralResponse();
        generalResponse.setStatus(true);
        generalResponse.setErrorCode(0);
        generalResponse.setMessage("Property Request sent");

        return generalResponse;

    }
}
