package org.aurd.user.controllers;


import org.aurd.user.constant.Constants;
import org.aurd.user.modal.entity.MyReviewModal;
import org.aurd.user.modal.request.MyReviewRequest;
import org.aurd.user.modal.response.MyReviewResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/myReviews")
public class GetUserReviews {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public MyReviewResponse getMyReviews(MyReviewRequest myReviewRequest){

        ArrayList userReviewList = MyReviewModal.getParticularUserReview(myReviewRequest.getUserID());
        MyReviewResponse myReviewResponse = new MyReviewResponse();
        myReviewResponse.setReviewList(userReviewList);
        myReviewResponse.setErrorcode(Constants.ERROR_CODE);
        myReviewResponse.setStatus(Constants.STATUS_SUCCESS);
        myReviewResponse.setMessage(Constants.FETCH_MY_REVIEW_SUCCESS);
        return  myReviewResponse;
    }
}
