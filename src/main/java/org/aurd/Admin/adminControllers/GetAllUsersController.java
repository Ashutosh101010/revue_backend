package org.aurd.Admin.adminControllers;

import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;
import org.aurd.Admin.adminModal.request.GetAllUsersRequest;
import org.aurd.Admin.adminModal.response.GetAllUsersResponse;
import org.aurd.Admin.adminModal.response.UpdateUserStatusResponse;
import org.aurd.user.constant.Constants;
import org.aurd.user.modal.entity.UserModal;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static org.aurd.MongoService.*;
import static org.aurd.MongoService.users;

@Path("/admin/users")
public class GetAllUsersController {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GetAllUsersResponse getAllUsers(GetAllUsersRequest getAllUsersRequest){
        ArrayList<UserModal> arrayList =new ArrayList();
        Gson gson =new Gson();
        Document findDoc = new Document();
        findDoc.append("_id",new Document("$gt",new ObjectId(getAllUsersRequest.getUserId())));
        MongoCursor cursor;
        int usersCount;
        usersCount = Math.toIntExact(reviews.countDocuments(new Document("status", getAllUsersRequest.getUserStatus())));
        if(getAllUsersRequest.getPageCount()>0){
            cursor = users.find().sort(new Document("_id",1)).skip(Constants.DOCUMENT_NUMBER_PAGE * getAllUsersRequest.getPageCount()).limit(Constants.DOCUMENT_NUMBER_PAGE).cursor();

        }else{
            cursor = users.find(findDoc).sort(new Document("_id",1)).limit(Constants.DOCUMENT_NUMBER_PAGE).cursor();
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
        getUsersResponse.setUserCount(usersCount);
        return  getUsersResponse;

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{status}")
    public GetAllUsersResponse getParticularUsers(@PathParam("status") boolean status){
        ArrayList<UserModal> arrayList =new ArrayList();
        Gson gson =new Gson();
        Document findDoc = new Document();
        int usersCount;
        usersCount = Math.toIntExact(users.countDocuments(new Document("status", status)));
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
        getUsersResponse.setUserCount(usersCount);
        return  getUsersResponse;

    }




    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{status}")
    public GetAllUsersResponse getParticularUsers(@PathParam("status") boolean status, GetAllUsersRequest request){
        ArrayList<UserModal> arrayList =new ArrayList();
        Gson gson =new Gson();
        Document findDoc = new Document();
        MongoCursor cursor;
        int usersCount;
        String search = request.getSearch();
        if(!status)
        {
            findDoc.append("status",status);
        }

        if(search!=null){
            ArrayList orList = new ArrayList();
            orList.add(new Document("email", new Document("$regex", Pattern.compile(search,Pattern.CASE_INSENSITIVE))));
            orList.add(new Document("firstname", new Document("$regex", Pattern.compile(search,Pattern.CASE_INSENSITIVE))));
            orList.add(new Document("lastname", new Document("$regex", Pattern.compile(search,Pattern.CASE_INSENSITIVE))));
            findDoc.append("$or",orList);
        }
        usersCount = Math.toIntExact(users.countDocuments(findDoc));


        if(findDoc.isEmpty())
        {
            System.out.println("find doc is empty");
            if(request.getPageCount()>0)
            {
                cursor=users.find().sort(new Document("_id",1)).skip(Constants.DOCUMENT_NUMBER_PAGE * request.getPageCount()).limit(Constants.DOCUMENT_NUMBER_PAGE).iterator();
            }
            else{
                cursor = users.find().sort(new Document("_id",1)).limit(Constants.DOCUMENT_NUMBER_PAGE).iterator();

            }
        }else {

            if(request.getPageCount()>0)
            {
                cursor = users.find(findDoc).sort(new Document("_id",1)).skip(Constants.DOCUMENT_NUMBER_PAGE * request.getPageCount()).limit(Constants.DOCUMENT_NUMBER_PAGE).iterator();

            }
            else{
                cursor = users.find(findDoc).sort(new Document("_id",1)).limit(Constants.DOCUMENT_NUMBER_PAGE).iterator();

            }

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
        getUsersResponse.setUserCount(usersCount);
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
