package org.aurd.Admin.adminControllers;


import com.google.gson.Gson;
import org.aurd.Admin.adminModal.entity.AdminUserModal;
import org.aurd.Admin.adminModal.request.AdminLoginRequest;
import org.aurd.Admin.adminModal.response.LoginResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/admin/login")
public class AdminLoginController {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)


    public LoginResponse loginResponse(AdminLoginRequest loginRequest){
        AdminUserModal userModal = new Gson().fromJson(new Gson().toJson(loginRequest), AdminUserModal.class);
        return userModal.loginResponse();
    }
}
