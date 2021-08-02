package org.aurd.user.controllers;

import org.aurd.user.constant.Constants;
import org.aurd.user.modal.entity.ReviewModal;
import org.aurd.user.modal.request.GetAllReviewRequest;
import org.aurd.user.modal.response.GetAllReviewsResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/getReviews")
public class GetAllReviewsController {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public GetAllReviewsResponse getAllReviewsByCompoundID(GetAllReviewRequest getAllReviewRequest){
      ArrayList arrayList = ReviewModal.getAllReviews(getAllReviewRequest.getCompoundID());
      GetAllReviewsResponse getAllReviewsResponse = new GetAllReviewsResponse();
      getAllReviewsResponse.setReviewList(arrayList);
      getAllReviewsResponse.setStatus(Constants.STATUS_SUCCESS);
      getAllReviewsResponse.setMessage(Constants.FETCH_ALLREVIEW_SUCCESS);
      getAllReviewsResponse.setErrorcode(Constants.ERROR_CODE);
      return getAllReviewsResponse;
    }

}
