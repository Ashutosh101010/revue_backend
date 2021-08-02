package org.aurd.user.controllers;

import com.mongodb.client.MongoCursor;

import org.aurd.user.constant.Constants;
import org.aurd.user.modal.request.VerifyOtpRequest;
import org.aurd.user.modal.response.VerifyOtpResponse;
import org.bson.Document;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static org.aurd.MongoService.authCollection;


@Path("/verifyOtp")
public class VerifyOtpController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public VerifyOtpResponse verifyOtp(VerifyOtpRequest request)
    {
        Document find=new Document();
        find.append("otp",request.getOtp());
        find.append("email",request.getEmail());

        MongoCursor cursor=authCollection.find(find).sort(new Document("_id",-1)).limit(1).cursor();

        VerifyOtpResponse response=new VerifyOtpResponse();
        if(cursor.hasNext())
        {
            Document doc=((Document) cursor.next());
            if(doc.getString("otp").equals(request.getOtp()))
            {
                response.setErrorCode(0);
                response.setMessage("Otp Verified Successfully");
                response.setStatus(Constants.STATUS_SUCCESS);
                return response;
            }

        }
        response.setErrorCode(10);
        response.setMessage("Invalid Otp");
        response.setStatus(Constants.STATUS_SUCCESS);
        return response;

    }

}
