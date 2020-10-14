package com.device.model.Response;

import com.device.model.*;

public class DeviceResponse {
    private Bracelet bracelet;

    private Device device;

    private DeviceConnection deviceConnection;

    private DeviceGps deviceGps;

    private DeviceState deviceState;

    private Vervel vervel;

    private VervelGps vervelGps;

    public Bracelet getBracelet() {
        return bracelet;
    }

    public void setBracelet(Bracelet bracelet) {
        this.bracelet = bracelet;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public DeviceConnection getDeviceConnection() {
        return deviceConnection;
    }

    public void setDeviceConnection(DeviceConnection deviceConnection) {
        this.deviceConnection = deviceConnection;
    }

    public DeviceGps getDeviceGps() {
        return deviceGps;
    }

    public void setDeviceGps(DeviceGps deviceGps) {
        this.deviceGps = deviceGps;
    }

    public DeviceState getDeviceState() {
        return deviceState;
    }

    public void setDeviceState(DeviceState deviceState) {
        this.deviceState = deviceState;
    }

    public Vervel getVervel() {
        return vervel;
    }

    public void setVervel(Vervel vervel) {
        this.vervel = vervel;
    }

    public VervelGps getVervelGps() {
        return vervelGps;
    }

    public void setVervelGps(VervelGps vervelGps) {
        this.vervelGps = vervelGps;
    }
}
