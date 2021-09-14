package org.aurd.user.controllers;


import com.google.gson.Gson;

import com.mongodb.client.MongoCursor;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.reactive.ReactiveMailer;
import org.apache.commons.codec.digest.DigestUtils;
import org.aurd.user.constant.Constants;
import org.aurd.user.modal.request.RegisterRequest;
import org.aurd.user.modal.response.RegisterResponse;

import org.bson.Document;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import static org.aurd.MongoService.users;

@Path("/register")
public class RegisterController {

    @Inject
    ReactiveMailer reactiveMailer;

    @Inject
    Mailer mailer;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public RegisterResponse register(RegisterRequest registerRequest)
    {
        Document document = new Document();
        document.append("email",registerRequest.getEmail());
        MongoCursor cursor =  users.find(document).cursor();

        if(cursor.hasNext()){
            Document us= (Document) cursor.next();
            if(us.getBoolean("verified"))
            {
                RegisterResponse registerResponse=new RegisterResponse();
                registerResponse.setStatus(Constants.STATUS_FAIL);
                registerResponse.setMessage(Constants.REGISTER_FAILED_ALREADY_EXIST);
                registerResponse.setErrorCode(Constants.ERROR_CODE);
                return registerResponse;
            }
            else{
                String token=DigestUtils.sha256Hex(String.valueOf((System.currentTimeMillis())));

                Document update=new Document();
                update.append("token",token);
                update.append("verified",false);
                users.findOneAndUpdate(new Document("_id",us.get("_id")),new Document("$set",update));
                mailer.send(Mail.withText(registerRequest.getEmail(),Constants.GMAIL_SUBJECT,"Please click below to verify your email \n https://revue-app.com/#/verifyEmail/"+token+"/"+us.get("_id").toString()));
//                new Mailer(registerRequest.getEmail(),us.get("_id").toString(),token).run();
                RegisterResponse registerResponse = new RegisterResponse();
                registerResponse.setStatus(Constants.STATUS_SUCCESS);
                registerResponse.setMessage(Constants.MESSAGE_REGISTRATION_SUCCESS);
                registerResponse.setErrorCode(Constants.SUCCESS_REGISTER_CODE);
                return registerResponse;
            }

        }else{
            Document doc = Document.parse(new Gson().toJson(registerRequest));
            System.out.println(doc.toJson());
            String token=DigestUtils.sha256Hex(String.valueOf((System.currentTimeMillis())));
            doc.append("token", token);
            doc.append("verified",false);
            String id=users.insertOne(doc).getInsertedId().asObjectId().getValue().toHexString();
            mailer.send(Mail.withText(registerRequest.getEmail(),Constants.GMAIL_SUBJECT,"Please click below to verify your email \n https://revue-app.com/#/verifyEmail/"+token+"/"+id));


            RegisterResponse registerResponse = new RegisterResponse();
            registerResponse.setStatus(Constants.STATUS_SUCCESS);
            registerResponse.setMessage(Constants.MESSAGE_REGISTRATION_SUCCESS);
            registerResponse.setErrorCode(Constants.SUCCESS_REGISTER_CODE);
            return registerResponse;
        }
    }




}
