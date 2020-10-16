package com.admin.model;

import io.swagger.annotations.ApiModelProperty;

public class Device {

    @ApiModelProperty(value = "设备编号")
    private String deviceNo;

    @ApiModelProperty(value = "设备类型")
    private String deviceType;

    @ApiModelProperty(value = "所属用户id")
    private String userId;

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceNo='" + deviceNo + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
