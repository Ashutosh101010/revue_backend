package org.aurd.user.controllers;


import com.google.gson.Gson;

import org.aurd.user.constant.Constants;
import org.aurd.user.modal.entity.QuestionModal;
import org.aurd.user.modal.request.PostQuestionRequest;
import org.aurd.user.modal.response.PostQuestionResponse;
import org.bson.Document;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static org.aurd.MongoService.questionCollection;


@Path("/postQuestion")
public class PostQuestionController {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)


    public PostQuestionResponse askQuestion(PostQuestionRequest questionRequest){
        Gson gson = new Gson();
        QuestionModal questionModal = gson.fromJson(gson.toJson(questionRequest),QuestionModal.class);
        Document document = Document.parse(new Gson().toJson(questionModal));
        questionCollection.insertOne(document);

        PostQuestionResponse questionResponse = new PostQuestionResponse();
        questionResponse.setMessage(Constants.ASK_QUESTION_SUCCESS);
        questionResponse.setStatusCode(Constants.ERROR_CODE);
        questionResponse.setStatus(Constants.STATUS_SUCCESS);

        return questionResponse;

    }
}
