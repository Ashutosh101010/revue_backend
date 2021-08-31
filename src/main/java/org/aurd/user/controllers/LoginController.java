package org.aurd.user.controllers;


import com.google.gson.Gson;
import org.aurd.user.modal.entity.UserModal;
import org.aurd.user.modal.request.LoginRequest;
import org.aurd.user.modal.response.LoginResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/login")
public class LoginController{

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public LoginResponse login(LoginRequest loginRequest){
        UserModal userModal = new Gson().fromJson(new Gson().toJson(loginRequest),UserModal.class);
        return userModal.login();

    }

}
