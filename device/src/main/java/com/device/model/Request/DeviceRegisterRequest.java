package com.device.model.Request;

/**
 * @Author: jojo
 * @Description:
 * @Date: Created on 2019/4/13 17:26
 */
public class DeviceRegisterRequest {

    private String deviceType;
    private String deviceNo;
    private String userId;

    public DeviceRegisterRequest(String deviceType, String userId, String deviceNo) {
        this.deviceType = deviceType;
        this.userId = userId;
        this.deviceNo = deviceNo;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
