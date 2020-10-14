package com.device.model;

import java.util.List;

public class CarInfo {
    private String escortCarNo;

    private List<DeviceAndRing> deviceAndRingList;

    public String getEscortCarNo() {
        return escortCarNo;
    }

    public void setEscortCarNo(String escortCarNo) {
        this.escortCarNo = escortCarNo;
    }

    public List<DeviceAndRing> getDeviceAndRingList() {
        return deviceAndRingList;
    }

    public void setDeviceAndRingList(List<DeviceAndRing> deviceAndRingList) {
        this.deviceAndRingList = deviceAndRingList;
    }
}
