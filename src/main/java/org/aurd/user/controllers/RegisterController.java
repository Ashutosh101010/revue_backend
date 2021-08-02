package org.aurd.user.controllers;


import com.google.gson.Gson;

import org.aurd.user.constant.Constants;
import org.aurd.user.modal.request.RegisterRequest;
import org.aurd.user.modal.response.RegisterResponse;
import org.bson.Document;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static org.aurd.MongoService.users;

@Path("/register")
public class RegisterController {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public RegisterResponse register(RegisterRequest registerRequest)
    {
//        UserModal user = new Gson().fromJson(new Gson().toJson(registerRequest),UserModal.class);
//        RegisterResponse registerResponse = user.saveUserToDB();
//        return registerResponse;

        Document document = new Document();
        document.append("email",registerRequest.getEmail());
        boolean userExist =  users.find(document).cursor().hasNext();

        if(userExist){
            RegisterResponse registerResponse=new RegisterResponse();
            registerResponse.setStatus(Constants.STATUS_FAIL);
            registerResponse.setMessage(Constants.REGISTER_FAILED_ALREADY_EXIST);
            registerResponse.setErrorCode(Constants.ERROR_CODE);
            return registerResponse;
        }else{
            Document doc = Document.parse(new Gson().toJson(registerRequest));
            System.out.println(doc.toJson());
            users.insertOne(doc);
            RegisterResponse registerResponse = new RegisterResponse();
            registerResponse.setStatus(Constants.STATUS_SUCCESS);
            registerResponse.setMessage(Constants.MESSAGE_REGISTRATION_SUCCESS);
            registerResponse.setErrorCode(Constants.SUCCESS_REGISTER_CODE);
            return registerResponse;
        }
    }
}
