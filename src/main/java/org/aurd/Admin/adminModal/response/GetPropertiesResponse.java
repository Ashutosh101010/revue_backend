package org.aurd.Admin.adminModal.response;


import org.aurd.Admin.adminModal.entity.PropertyModal;

import java.util.ArrayList;

public class GetPropertiesResponse {
    boolean status;
    int errorCode;
    String message;
    String errorDescription;
    ArrayList<PropertyModal> propertyModals;
    int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public ArrayList<PropertyModal> getPropertyModals() {
        return propertyModals;
    }

    public void setPropertyModals(ArrayList<PropertyModal> propertyModals) {
        this.propertyModals = propertyModals;
    }
}
