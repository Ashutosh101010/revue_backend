package org.aurd.Admin.adminModal.response;

import org.aurd.user.modal.entity.ReviewModal;

import java.util.ArrayList;

public class GetReportedReviewsResponse {
    String message;
    int errorCode;
    boolean status;
    int totalReviews;
    ArrayList<ReviewModal> reviews;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }

    public ArrayList<ReviewModal> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<ReviewModal> reviews) {
        this.reviews = reviews;
    }
}
