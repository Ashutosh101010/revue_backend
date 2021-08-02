package org.aurd.user.controllers;

import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;

import org.aurd.user.constant.Constants;
import org.aurd.user.modal.entity.AnswerModal;
import org.aurd.user.modal.request.GetAllAnswerRequest;
import org.aurd.user.modal.response.GetAllAnswerResponse;
import org.bson.Document;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

import static org.aurd.MongoService.answerCollection;
import static org.aurd.MongoService.likeUnlikeCollection;


@Path("/getAllAnswers")
public class GetAllAnswersController {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public GetAllAnswerResponse getAnswers(GetAllAnswerRequest getAllAnswerRequest){
        Document document = new Document();
        document.append("questionID",getAllAnswerRequest.getQuestionID());

       MongoCursor cursor = answerCollection.find(document).iterator();
        ArrayList <AnswerModal> arrayList = new ArrayList();
       while(cursor.hasNext()){
           Document ansDocument = (Document) cursor.next();
           System.out.println(ansDocument.toJson());


           AnswerModal answerModal = new Gson().fromJson(ansDocument.toJson(),AnswerModal.class);

           Document doc = new Document();
           doc.append("answerID",answerModal.get_id());
           doc.append("userID",getAllAnswerRequest.getUserID());

           MongoCursor likeCursor  =likeUnlikeCollection.find(doc).cursor();
           if(likeCursor.hasNext()){
               Document likeDocument = (Document) likeCursor.next();
               if(likeDocument.getInteger("operation")==1){
                   answerModal.setLiked(true);
               }else if(likeDocument.getInteger("operation")==2){
                   answerModal.setDisliked(true);
               }

           }

           arrayList.add(answerModal);
       }
       GetAllAnswerResponse allAnswerResponse = new GetAllAnswerResponse();
       allAnswerResponse.setAnswerList(arrayList);
       allAnswerResponse.setErrorCode(Constants.ERROR_CODE);
       allAnswerResponse.setStatus(Constants.STATUS_SUCCESS);
       allAnswerResponse.setMessage(Constants.GET_ALL_ANSWERS_SUCCESS);
       return  allAnswerResponse;
    }


}
