package com.prisoner.model.Exception;

/**
 * @Author: jojo
 * @Description:
 * @Date: Created on 2019/4/4 22:17
 */
public class EdgeComputingServiceException extends RuntimeException {

    private int httpStatus;
    private String myMessage;

    public EdgeComputingServiceException(int httpStatus, String myMessage) {
        this.httpStatus = httpStatus;
        this.myMessage = myMessage;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMyMessage() {
        return myMessage;
    }

    public void setMyMessage(String myMessage) {
        this.myMessage = myMessage;
    }

}
