package com.youtubemimic.bean;

import java.util.HashMap;
import java.util.Map;

public class ApiResponce {
    private String status_code;
    private String message;
    private String status;
    private Map<String, Object> details = new HashMap();

    public ApiResponce(String status_code, String message, String status, Map<String, Object> details) {
        this.status_code = status_code;
        this.message = message;
        this.status = status;
        this.details = details;
    }

    public String getStatus_code() {
        return this.status_code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public Map<String, Object> getDetails() {
        return this.details;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }
}
