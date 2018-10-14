package com.and3r.octoprint.javadisplay.connection;

public class StatusCodeException extends Exception {

    public int getStatusCode() {
        return statusCode;
    }

    private int statusCode;

    public StatusCodeException(int statusCode){
        super(String.valueOf(statusCode));
        this.statusCode = statusCode;
    }

}
