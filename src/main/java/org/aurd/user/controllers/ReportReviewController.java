package org.aurd.user.controllers;

import com.google.gson.Gson;

import org.aurd.user.constant.Constants;
import org.aurd.user.modal.request.ReportReviewRequest;
import org.aurd.user.modal.response.ReportReviewResponse;
import org.bson.Document;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static org.aurd.MongoService.reportCollection;


@Path("/reportReview")
public class ReportReviewController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ReportReviewResponse reportReview(ReportReviewRequest reportReviewRequest){

        Document document = Document.parse(new Gson().toJson(reportReviewRequest));
        reportCollection.insertOne(document);

        ReportReviewResponse reportReviewResponse = new ReportReviewResponse();
        reportReviewResponse.setMessage(Constants.REPORT_REVIEW_SUCCESS);
        reportReviewResponse.setStatus(Constants.STATUS_SUCCESS);
        reportReviewResponse.setErrorCode(Constants.ERROR_CODE);
        return  reportReviewResponse;
    }

}
