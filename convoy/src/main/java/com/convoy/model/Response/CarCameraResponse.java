package com.convoy.model.Response;

public class CarCameraResponse {
    private String carNo;

    private String carInnerVideoUrl;

    private String carOuterVideoUrl;

    private String cameraInnerIp;

    private String cameraOuterIp;

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getCarInnerVideoUrl() {
        return carInnerVideoUrl;
    }

    public void setCarInnerVideoUrl(String carInnerVideoUrl) {
        this.carInnerVideoUrl = carInnerVideoUrl;
    }

    public String getCarOuterVideoUrl() {
        return carOuterVideoUrl;
    }

    public void setCarOuterVideoUrl(String carOuterVideoUrl) {
        this.carOuterVideoUrl = carOuterVideoUrl;
    }

    public String getCameraInnerIp() {
        return cameraInnerIp;
    }

    public void setCameraInnerIp(String cameraInnerIp) {
        this.cameraInnerIp = cameraInnerIp;
    }

    public String getCameraOuterIp() {
        return cameraOuterIp;
    }

    public void setCameraOuterIp(String cameraOuterIp) {
        this.cameraOuterIp = cameraOuterIp;
    }
}
