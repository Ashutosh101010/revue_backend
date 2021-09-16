package org.aurd.Admin.adminModal.request;

public class GetReviewRequest {

    String reviewStatus;
    int pageCount;
    String lastObjectID;

    public String getLastObjectID() {
        return lastObjectID;
    }

    public void setLastObjectID(String lastObjectID) {
        this.lastObjectID = lastObjectID;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }
}
