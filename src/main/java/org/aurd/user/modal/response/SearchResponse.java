package org.aurd.user.modal.response;


import org.aurd.user.modal.entity.CompoundModal;

import java.util.ArrayList;

public class SearchResponse {
    String message;
    int code;
    boolean  status;
    ArrayList<CompoundModal> compoundList ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<CompoundModal> getCompoundList() {
        return compoundList;
    }

    public void setCompoundList(ArrayList<CompoundModal> compoundList) {
        this.compoundList = compoundList;
    }
}
