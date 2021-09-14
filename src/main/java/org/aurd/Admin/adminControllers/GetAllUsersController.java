package org.aurd.Admin.adminControllers;

import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;
import org.aurd.Admin.adminModal.request.GetAllUsersRequest;
import org.aurd.Admin.adminModal.response.GetAllUsersResponse;
import org.aurd.Admin.adminModal.response.UpdateUserStatusResponse;
import org.aurd.user.modal.entity.UserModal;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

import static org.aurd.MongoService.reviews;
import static org.aurd.MongoService.users;

@Path("/admin/users")
public class GetAllUsersController {
    @GET
//    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GetAllUsersResponse getAllUsers(){
        ArrayList<UserModal> arrayList =new ArrayList();
        Gson gson =new Gson();
        Document findDoc = new Document();
        MongoCursor cursor;
        if(findDoc.isEmpty()){
            cursor = users.find().sort(new Document("_id",1)).limit(8).iterator();

        }else{
            cursor = users.find(findDoc).sort(new Document("_id",1)).limit(8).iterator();
        }
        while(cursor.hasNext()){
            Document doc = (Document) cursor.next();
            UserModal userModal = gson.fromJson(doc.toJson(),UserModal.class);
            int count = Math.toIntExact(reviews.countDocuments(new Document("userID", userModal.get_id())));
            userModal.setReviewCount(count);
            arrayList.add(userModal);
        }
        GetAllUsersResponse getUsersResponse = new GetAllUsersResponse();
        getUsersResponse.setMessage("Get User Successfully");
        getUsersResponse.setErrorCode(0);
        getUsersResponse.setUsers(arrayList);
        getUsersResponse.setStatus(true);
        return  getUsersResponse;

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{status}")
    public GetAllUsersResponse getParticularUsers(@PathParam("status") boolean status){
        ArrayList<UserModal> arrayList =new ArrayList();
        Gson gson =new Gson();
        Document findDoc = new Document();
        MongoCursor cursor;
        if(findDoc.isEmpty()){
            cursor = users.find(new Document("status",status)).sort(new Document("_id",1)).cursor();

        }else{
            cursor = users.find(findDoc).sort(new Document("_id",1)).cursor();
        }
        while(cursor.hasNext()){
            Document doc = (Document) cursor.next();
            UserModal userModal = gson.fromJson(doc.toJson(),UserModal.class);
            int count = Math.toIntExact(reviews.countDocuments(new Document("userID", userModal.get_id())));
            userModal.setReviewCount(count);
            arrayList.add(userModal);
        }
        GetAllUsersResponse getUsersResponse = new GetAllUsersResponse();
        getUsersResponse.setMessage("Get User Successfully");
        getUsersResponse.setErrorCode(0);
        getUsersResponse.setUsers(arrayList);
        getUsersResponse.setStatus(true);
        return  getUsersResponse;

    }

// to change the user status
    @POST
    @Path("{userId}/{status}")
    @Produces(MediaType.APPLICATION_JSON)
    public UpdateUserStatusResponse updateUserStatusResponse(@PathParam("status") boolean status, @PathParam("userId") String userId){
        UpdateUserStatusResponse userStatusResponse = new UpdateUserStatusResponse();
        Document doc = new Document("status", status);
        Document findDoc = new Document("_id", new ObjectId(userId));
        users.findOneAndUpdate(findDoc,new Document("$set",doc));
        userStatusResponse.setStatus(true);
        userStatusResponse.setErrorCode(0);
        userStatusResponse.setMessage("User status updated to " + status + " successfully");
        return userStatusResponse;
    }


}
