package org.aurd.Admin.adminModal.request;

public class GetReviewRequest {

    int status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
