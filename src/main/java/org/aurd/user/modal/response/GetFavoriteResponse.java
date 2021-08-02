package org.aurd.user.modal.response;

import org.aurd.user.modal.entity.CompoundModal;

import java.util.ArrayList;

public class GetFavoriteResponse {
    String message;
    boolean status;
    int errorCode;
    ArrayList<CompoundModal> favList;

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

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public ArrayList getFavList() {
        return favList;
    }

    public void setFavList(ArrayList favList) {
        this.favList = favList;
    }
}
