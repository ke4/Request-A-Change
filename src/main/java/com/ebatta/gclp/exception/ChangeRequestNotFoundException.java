package com.ebatta.gclp.exception;

public class ChangeRequestNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    private String exceptionMsg;

    public ChangeRequestNotFoundException(String message) {
        this.exceptionMsg = message;
    }

    public String getExceptionMsg(){
        return this.exceptionMsg;
     }

     public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
     }
}
