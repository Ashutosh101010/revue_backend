package org.aurd.Admin.adminControllers;

import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;
import org.aurd.Admin.adminModal.request.GetAllUsersRequest;
import org.aurd.Admin.adminModal.response.GetAllUsersResponse;
import org.aurd.user.modal.entity.UserModal;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

import static org.aurd.MongoService.reviews;
import static org.aurd.MongoService.users;

@Path("/admin/users")
public class GetAllUsersController {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GetAllUsersResponse getAllUsers(GetAllUsersRequest request){
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
}
