package org.aurd.user.modal.request;

public class CheckReviewRequest {

    String userId;
    String compoundId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompoundId() {
        return compoundId;
    }

    public void setCompoundId(String compoundId) {
        this.compoundId = compoundId;
    }
}
