package org.aurd.user.controllers;

import com.google.gson.Gson;
import org.aurd.user.modal.entity.ReportModal;
import org.aurd.user.modal.request.ReportAnswerRequest;
import org.aurd.user.modal.response.ReportAnswerResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/reportAnswer")
public class ReportAnswerController {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public ReportAnswerResponse reportAnswer(ReportAnswerRequest reportAnswerRequest){

        ReportModal reportModal = new Gson().fromJson(new Gson().toJson(reportAnswerRequest), ReportModal.class);
        ReportAnswerResponse reportAnswerResponse = reportModal.reportAnswerAndAddToDB();
        return  reportAnswerResponse;
    }
}
