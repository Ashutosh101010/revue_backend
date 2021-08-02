package org.aurd.user.controllers;


import com.google.gson.Gson;
import org.aurd.user.modal.entity.AnswerModal;
import org.aurd.user.modal.request.PostAnswerRequest;
import org.aurd.user.modal.response.PostAnswerResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/postAnswer")
public class PostAnswerController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)


    public PostAnswerResponse addAnswer(PostAnswerRequest addAnswerRequest){
        Gson gson = new Gson();
        AnswerModal answerModal = gson.fromJson(gson.toJson(addAnswerRequest),AnswerModal.class);
        PostAnswerResponse addAnswerResponse = answerModal.addAnswerToDB();
        return  addAnswerResponse;
    }
}
