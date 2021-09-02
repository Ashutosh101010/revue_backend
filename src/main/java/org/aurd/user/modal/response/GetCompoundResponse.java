package org.aurd.user.modal.response;

import java.util.ArrayList;

public class GetCompoundResponse {
    boolean status;
    String message;
    int fetchCode;
    ArrayList compoundList = new ArrayList();
    int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFetchCode() {
        return fetchCode;
    }

    public void setFetchCode(int fetchCode) {
        this.fetchCode = fetchCode;
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

    public ArrayList getCompoundList() {
        return compoundList;
    }

    public void setCompoundList(ArrayList compoundList) {
        this.compoundList = compoundList;
    }
}
