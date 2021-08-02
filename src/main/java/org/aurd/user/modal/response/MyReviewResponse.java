package org.aurd.user.modal.response;


import org.aurd.user.modal.entity.MyReviewModal;

import java.util.ArrayList;

public class MyReviewResponse {
    String message;
    boolean status;
    ArrayList<MyReviewModal> reviewList ;
    int errorcode;

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

    public ArrayList<MyReviewModal> getReviewList() {
        return reviewList;
    }

    public void setReviewList(ArrayList<MyReviewModal> reviewList) {
        this.reviewList = reviewList;
    }

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }
}
