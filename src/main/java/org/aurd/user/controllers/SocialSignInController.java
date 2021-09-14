package org.aurd.user.controllers;


import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;

import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import org.apache.commons.codec.digest.DigestUtils;
import org.aurd.user.constant.Constants;
import org.aurd.user.modal.entity.UserModal;
import org.aurd.user.modal.request.SocialSignInRequest;
import org.aurd.user.modal.response.SocialSignInResponse;
import org.bson.Document;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static org.aurd.MongoService.users;


@Path("/socialSignIn")
public class SocialSignInController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SocialSignInResponse socialSignIn(SocialSignInRequest request)
    {

        Gson gson=new Gson();
        SocialSignInResponse response=new SocialSignInResponse();
        if(request.getEmail()!=null && !request.getEmail().isEmpty())
        {
          MongoCursor cursor= users.find(new Document("email",request.getEmail())).cursor();
           if(cursor.hasNext()){

               Document doc= ((Document) cursor.next());

               FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
               options.returnDocument(ReturnDocument.AFTER);
               options.upsert(true);

               Document update=new Document();
               update.append("token", DigestUtils.sha3_256Hex((doc.get("_id").toString())));
               update.append("isLoggedIn",true);
              doc= (Document) users.findOneAndUpdate(new Document("_id",doc.get("_id")),new Document("$set",update),options);
               UserModal user=gson.fromJson(doc.toJson(),UserModal.class);

               response.setStatus(Constants.STATUS_SUCCESS);
               response.setErrorCode(Constants.SUCCESS_LOGIN_CODE);
               response.setUser(user);
               return  response;
           }
        }
        if(request.isType()){
            Document document = new Document();
            document.append("firstname",request.getFirstname());
            MongoCursor cursor = users.find(document).cursor();

            if(cursor.hasNext()){

                Document user= (Document) cursor.next();
                FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
                options.returnDocument(ReturnDocument.AFTER);
                options.upsert(true);

                Document update=new Document();
                update.append("token", DigestUtils.sha3_256Hex((user.get("_id").toString())));
                update.append("isLoggedIn",true);
                user= (Document) users.findOneAndUpdate(new Document("_id",user.get("_id")),new Document("$set",update),options);
                response.setUser(gson.fromJson(user.toJson(),UserModal.class));
                response.setStatus(Constants.STATUS_SUCCESS);
                response.setErrorCode(Constants.SUCCESS_LOGIN_CODE);
            }else{
                Document user= new Document();
                user.append("firstname",request.getFirstname());
                user.append("email",request.getEmail());
                user.append("type",request.isType());
                users.insertOne(user);
                FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
                options.returnDocument(ReturnDocument.AFTER);
                options.upsert(true);

                Document update=new Document();
                update.append("token", DigestUtils.sha3_256Hex((user.get("_id").toString())));
                update.append("isLoggedIn",true);
                user= (Document) users.findOneAndUpdate(new Document("_id",user.get("_id")),new Document("$set",update),options);
                response.setStatus(Constants.STATUS_SUCCESS);
                response.setErrorCode(Constants.SUCCESS_LOGIN_CODE);
                response.setUser(gson.fromJson(user.toJson(),UserModal.class));
            }
            return response;
        }  else{
            Document user= new Document();
            user.append("firstname",request.getFirstname());
            user.append("email",request.getEmail());
            users.insertOne(user);

            FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
            options.returnDocument(ReturnDocument.AFTER);
            options.upsert(true);

            Document update=new Document();
            update.append("token", DigestUtils.sha3_256Hex((user.get("_id").toString())));
            update.append("isLoggedIn",true);
            user= (Document) users.findOneAndUpdate(new Document("_id",user.get("_id")),new Document("$set",update),options);


            response.setStatus(Constants.STATUS_SUCCESS);
            response.setErrorCode(Constants.SUCCESS_LOGIN_CODE);
            response.setUser(gson.fromJson(user.toJson(),UserModal.class));
            return  response;
        }




    }
}
