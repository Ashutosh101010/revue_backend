package org.aurd.user.modal.response;

import org.aurd.user.modal.entity.ReviewModal;

import java.util.ArrayList;

public class GetAllReviewsResponse {
    String message;
   boolean status;
   ArrayList<ReviewModal> reviewList = new ArrayList<>();
   int errorcode;

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public ArrayList<ReviewModal> getReviewList() {
        return reviewList;
    }

    public void setReviewList(ArrayList<ReviewModal> reviewList) {
        this.reviewList = reviewList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
