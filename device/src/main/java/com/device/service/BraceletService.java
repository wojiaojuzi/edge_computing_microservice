package com.device.service;

import com.device.mapper.BraceletMapper;
import com.device.mapper.DeviceConnectionMapper;
import com.device.mapper.DeviceMapper;
import com.device.mapper.VervelMapper;
import com.device.model.Bracelet;
import com.device.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Zhaoone on 2019/11/4
 **/
@Service
public class BraceletService {
    private static final String mysqlSdfPatternString = "yyyy-MM-dd HH:mm:ss";
    private final DeviceMapper deviceMapper;
    private final BraceletMapper braceletMapper;
    private final DeviceConnectionMapper deviceConnectionMapper;
    private final VervelMapper vervelMapper;

    @Autowired
    public BraceletService(DeviceMapper deviceMapper, BraceletMapper braceletMapper, DeviceConnectionMapper deviceConnectionMapper, VervelMapper vervelMapper) {
        this.deviceMapper = deviceMapper;
        this.braceletMapper = braceletMapper;
        this.deviceConnectionMapper = deviceConnectionMapper;
        this.vervelMapper = vervelMapper;
    }

    public boolean bindNewBracelet(String braceletNo, String device_no) {
        Device device = deviceMapper.getByDeviceNo(device_no);
        if(device == null) {
            return false;
        }
        SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
        Date createTime = new Date();
        String createAt = mysqlSdf.format(createTime);
        //Bracelet bracelet = new Bracelet();
        //bracelet.setBraceletNo(braceletNo);
        //bracelet.setCreateAt(createAt);
        //bracelet.setDeviceNo(device_no);
        braceletMapper.createBracelet(braceletNo,device_no,createAt);
        updateDeviceConnectivityStatusByDeviceNo(true, "绑定手环",device_no);
        return true;
    }

    /*public void updateBraceletStatus(Boolean deviceStatus, String braceletNo){
        braceletMapper.updateDeviceStatusByBraceletNo(deviceStatus, braceletNo);
        Device device = deviceService.getByDeviceNo(getDeviceNoByBraceletNo(braceletNo));
        if(deviceStatus == true)
            deviceLogsService.insertRecord(device, "bracelet", "设备重连");
        else    deviceLogsService.insertRecord(device, "bracelet", "设备掉线");
    }*/

    public boolean updateBraceletConnectivityStatus(String deviceNo, String braceletNo){
        SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
        Date time = new Date();
        String createAt = mysqlSdf.format(time);
        //找到原来的设备

        if(braceletMapper.getByBraceletNo(braceletNo) == null)
            braceletMapper.createBracelet(braceletNo,deviceNo,createAt);
        String lastDeviceNo = braceletMapper.getDeviceNoByBraceletNo(braceletNo);
        if(deviceMapper.getByDeviceNo(deviceNo)!=null) {
            if (lastDeviceNo != null) {
                if (braceletMapper.getDeviceNoByBraceletNo(braceletNo) != lastDeviceNo) {
                    updateDeviceConnectivityStatusByDeviceNo(false, "手环解绑", lastDeviceNo);
                    updateDeviceConnectivityStatusByDeviceNo(true, "绑定手环", deviceNo);
                }
                //与新设备建立连接
                braceletMapper.updateDeviceNoByBraceletNo(braceletNo, deviceNo, createAt);

                return true;
            }
        }
        /*else{// 就创建新的
            if(braceletMapper.getByBraceletNo(braceletNo) == null) {
                if (!bindNewBracelet(braceletNo, deviceNo)) {
                    throw new EdgeComputingServiceException(ResponseEnum.DEVICE_NOT_EXIST.getCode(), ResponseEnum.DEVICE_NOT_EXIST.getMessage());
                }
                return true;
            }
            else {
                braceletMapper.updateDeviceNoByBraceletNo(braceletNo, deviceNo, createAt);
                updateDeviceConnectivityStatusByDeviceNo(true, "绑定手环",deviceNo);
                return true;
            }
        }*/

        return false;

    }



    public String getDeviceNoByBraceletNo(String braceletNo){
        return braceletMapper.getByBraceletNo(braceletNo).getDeviceNo();
    }

    public Bracelet getByDeviceNo(String deviceNo){
        return braceletMapper.getBraceletByDeviceNo(deviceNo);
    }

    public void updateDeviceNoByBraceletNo(String braceletNo,String deviceNo){
        SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
        Date createTime = new Date();
        String createAt = mysqlSdf.format(createTime);
        braceletMapper.updateDeviceNoByBraceletNo(deviceNo,braceletNo,createAt);
    }

    public String getBraceletNoByDeviceNo(String deviceNo){
        return braceletMapper.getBraceletNoByDeviceNo(deviceNo);
    }

    public void updateDeviceConnectivityStatusByDeviceNo(Boolean deviceConnectivityStatus, String record, String deviceNo){
        SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
        Date createTime = new Date();
        String createAt = mysqlSdf.format(createTime);
        deviceConnectionMapper.updateDeviceConnectivityStatusByDeviceNo(deviceConnectivityStatus, createAt, record,deviceNo);
    }

}
