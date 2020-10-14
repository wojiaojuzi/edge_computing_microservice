package com.device.model.Response;

import com.device.model.CarInfo;

import java.util.List;

public class DeviceCloudGraphResponse {
    private String commandCarNo;

    private List<CarInfo> carInfoList;

    public String getCommandCarNo() {
        return commandCarNo;
    }

    public void setCommandCarNo(String commandCarNo) {
        this.commandCarNo = commandCarNo;
    }

    public List<CarInfo> getCarInfoList() {
        return carInfoList;
    }

    public void setCarInfoList(List<CarInfo> carInfoList) {
        this.carInfoList = carInfoList;
    }
}
