package com.convoy.model;

public class Camera {
    private String cameraId;

    private String carNo;

    private String url;

    private Boolean cameraState;

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getCameraState() {
        return cameraState;
    }

    public void setCameraState(Boolean cameraState) {
        this.cameraState = cameraState;
    }
}
