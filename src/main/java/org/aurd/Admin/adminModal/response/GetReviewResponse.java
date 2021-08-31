package org.aurd.Admin.adminModal.response;

import org.aurd.Admin.adminModal.entity.AdminReviewModal;

import java.util.ArrayList;

public class GetReviewResponse {
    boolean status;
    int errorCode;
    String message;
    String errorDescription;
    ArrayList<AdminReviewModal> adminReviewModals;

    public ArrayList<AdminReviewModal> getAdminReviewModals() {
        return adminReviewModals;
    }

    public void setAdminReviewModals(ArrayList<AdminReviewModal> adminReviewModals) {
        this.adminReviewModals = adminReviewModals;
    }

    public boolean isStatus() {
        return status;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorDescription() {
        return errorDescription;
    }


    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }


}
