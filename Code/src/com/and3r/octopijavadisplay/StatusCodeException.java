package com.and3r.octopijavadisplay;

public class StatusCodeException extends Exception {

    public int getStatusCode() {
        return statusCode;
    }

    private int statusCode;

    public StatusCodeException(int statusCode){
        this.statusCode = statusCode;
    }

}
