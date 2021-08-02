package org.aurd.user.controllers;


import org.aurd.user.constant.Constants;
import org.aurd.user.constant.JsonConstants;
import org.aurd.user.modal.request.LikeDislikeRequest;
import org.aurd.user.modal.response.LikeDislikeResponse;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static org.aurd.MongoService.answerCollection;
import static org.aurd.MongoService.likeUnlikeCollection;

@Path("/likeDislike")
public class LikeDislikeController {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public LikeDislikeResponse addLikesDislike(LikeDislikeRequest likeDislikeRequest){
        LikeDislikeResponse likeDislikeResponse = new LikeDislikeResponse();
        Document update=new Document();
        if(likeDislikeRequest.getOperation()==1)
        {
            Document  document = new Document();
            document.put("userID",likeDislikeRequest.getUserID());
            document.put("answerID",likeDislikeRequest.get_id());
            document.put("operation",2);
            boolean exists=likeUnlikeCollection.find(document).limit(1).cursor().hasNext();
            if(exists)
            {
                likeUnlikeCollection.findOneAndUpdate(document,new Document("$set",new Document("operation",1)));
                update.append("$inc",new Document().append("dislike",-1).append("like",1));
            }
            else {
                document.put("operation",1);
                likeUnlikeCollection.insertOne(document);
                update.append("$inc",new Document().append("like",1));
            }



        }
        else if(likeDislikeRequest.getOperation()==2)
        {
            Document document = new Document();
            document.put("userID",likeDislikeRequest.getUserID());
            document.put("answerID",likeDislikeRequest.get_id());
            document.put("operation",1);
            boolean exists=likeUnlikeCollection.find(document).limit(1).cursor().hasNext();
            if(exists)
            {
                likeUnlikeCollection.findOneAndUpdate(document,new Document("$set",new Document("operation",2)));
                update.append("$inc",new Document().append("dislike",1).append("like",-1));
            }
            else {
                document.put("operation",2);
                likeUnlikeCollection.insertOne(document);
                update.append("$inc",new Document().append("dislike",1));
            }
        }
        else if(likeDislikeRequest.getOperation()== -1)
        {
            Document  document = new Document();
            document.append("userID",likeDislikeRequest.getUserID());
            document.append("answerID",likeDislikeRequest.get_id());
            document.append("operation",1);
            likeUnlikeCollection.findOneAndDelete(document);

            update.append("$inc",new Document().append("like",-1));

        } else if(likeDislikeRequest.getOperation()== -2)
        {
            Document  document = new Document();
            document.append("userID",likeDislikeRequest.getUserID());
            document.append("answerID",likeDislikeRequest.get_id());
            document.append("operation",2);
            likeUnlikeCollection.findOneAndDelete(document);

            update.append("$inc",new Document().append("dislike",-1));
        }

        else{

            likeDislikeResponse.setErrorCode(Constants.ERROR_CODE_LIKE_DISLIKE);
            likeDislikeResponse.setDescription(Constants.INVALID_OPERATION);
            likeDislikeResponse.setStatus(Constants.STATUS_FAIL);
            likeDislikeResponse.setMessage(Constants.LIKE_DISLIKE_FAILED);
            return likeDislikeResponse;
        }



        Document findDoc =new Document();
        findDoc.append(JsonConstants.ANSWERID,new ObjectId(likeDislikeRequest.get_id()));
        answerCollection.findOneAndUpdate(findDoc,update);


        likeDislikeResponse.setErrorCode(Constants.ERROR_CODE);
        likeDislikeResponse.setStatus(Constants.STATUS_SUCCESS);
        likeDislikeResponse.setMessage(Constants.LIKE_DISLIKE_SUCCESS);

        return likeDislikeResponse;
    }

}
