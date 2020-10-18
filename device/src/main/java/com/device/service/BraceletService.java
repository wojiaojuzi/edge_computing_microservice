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


        String lastDeviceNo = braceletMapper.getDeviceNoByBraceletNo(braceletNo);
        String lastBraceletNo = braceletMapper.getBraceletNoByDeviceNo(deviceNo);
        if(deviceMapper.getByDeviceNo(deviceNo)!=null) {
            //System.out.println("deviceNo:"+lastDeviceNo+" "+deviceNo);
            if (lastDeviceNo != deviceNo && lastDeviceNo!=null) {//如果手环要绑的设备和之前绑定的不同  就先解绑
                updateDeviceConnectivityStatusByDeviceNo(false, "手环解绑", braceletNo);
            }
            //System.out.println("braceletNo:"+lastBraceletNo+" "+braceletNo);
            if(lastBraceletNo != braceletNo && lastBraceletNo!=null) {//如果新设备已经绑定了其他设备  就先解绑
                updateDeviceConnectivityStatusByDeviceNo(false, "手环解绑", lastBraceletNo);
                braceletMapper.deleteByBraceletNo(lastBraceletNo);
            }


            if(braceletMapper.getByBraceletNo(braceletNo) == null)
                braceletMapper.createBracelet(braceletNo,deviceNo,createAt);
            //与新设备建立连接
            braceletMapper.updateDeviceNoByBraceletNo(deviceNo, braceletNo,  createAt);
            updateDeviceConnectivityStatusByDeviceNo(true, "绑定手环", braceletNo);

            return true;
        }

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
