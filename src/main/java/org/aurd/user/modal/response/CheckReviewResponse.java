package org.aurd.user.modal.response;

public class CheckReviewResponse {
    boolean reviewExists;
    boolean status;
    int errorcode;
    String errorDescription;

    public boolean isReviewExists() {
        return reviewExists;
    }

    public void setReviewExists(boolean reviewExists) {
        this.reviewExists = reviewExists;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
