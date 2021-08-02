package org.aurd.user.modal.response;

import java.util.ArrayList;

public class FavoriteResponse {
    String message;
    boolean status;
    int errorcode;
    ArrayList compound;

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

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public ArrayList getCompound() {
        return compound;
    }

    public void setCompound(ArrayList compound) {
        this.compound = compound;
    }
}
