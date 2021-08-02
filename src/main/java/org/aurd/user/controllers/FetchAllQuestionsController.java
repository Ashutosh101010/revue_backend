package org.aurd.user.controllers;


import org.aurd.user.constant.Constants;
import org.aurd.user.modal.entity.QuestionModal;
import org.aurd.user.modal.request.FetchAllQuestionRequest;
import org.aurd.user.modal.response.FetchAllQuestionResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;


@Path("/fetchQuestions")
public class FetchAllQuestionsController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public FetchAllQuestionResponse fetchQuestions(FetchAllQuestionRequest fetchAllQuestionRequest){
        ArrayList arrayList = QuestionModal.fetchQuestions(fetchAllQuestionRequest.getCompoundID());
        FetchAllQuestionResponse fetchAllQuestionResponse = new FetchAllQuestionResponse();

       fetchAllQuestionResponse.setQuestionsList(arrayList);
       fetchAllQuestionResponse.setMessage(Constants.FETCH_ALL_QUESTION_SUCCESS);
       fetchAllQuestionResponse.setErrorCode(Constants.ERROR_CODE);
       fetchAllQuestionResponse.setStatus(Constants.STATUS_SUCCESS);

        return fetchAllQuestionResponse;
    }
}
