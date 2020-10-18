package com.device.service;


import com.device.mapper.DeviceConnectionMapper;
import com.device.mapper.DeviceMapper;
import com.device.mapper.VervelGpsMapper;
import com.device.mapper.VervelMapper;
import com.device.model.Device;
import com.device.model.Vervel;
import com.device.model.VervelGps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class VervelService {
    private static final String mysqlSdfPatternString = "yyyy-MM-dd HH:mm:ss";
    private final VervelMapper vervelMapper;
    private final VervelGpsMapper vervelGpsMapper;
    private final DeviceConnectionMapper deviceConnectionMapper;
    private final DeviceMapper deviceMapper;

    @Autowired
    public VervelService(VervelMapper vervelMapper, VervelGpsMapper vervelGpsMapper, DeviceConnectionMapper deviceConnectionMapper, DeviceMapper deviceMapper) {
        this.vervelMapper = vervelMapper;
        this.vervelGpsMapper = vervelGpsMapper;
        this.deviceConnectionMapper = deviceConnectionMapper;
        this.deviceMapper = deviceMapper;
    }




    public Vervel getVervelByDeviceNo(String deviceNo){
        return vervelMapper.getVervelByDeviceNo(deviceNo);
    }

    public VervelGps getVervelGpsByVervelNo(String vervelNo){
        return vervelGpsMapper.getByVervelNo(vervelNo);
    }

    public Vervel getByVervelNo(String vervelNo){
        return vervelMapper.getVervelByVervelNo(vervelNo);
    }

    public void updateDeviceNoByVervelNo(String vervelNo, String deviceNo,String createAt){
        vervelMapper.updateDeviceNoByVervelNo(deviceNo,vervelNo,createAt);
    }

    public String getVervelNoByDeviceNo(String deviceNo){
        return vervelMapper.getVervelNoByDeviceNo(deviceNo);
    }

    public boolean updateVervelConnectivityStatus(String deviceNo, String vervelNo){
        SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
        Date time = new Date();
        String createAt = mysqlSdf.format(time);
        //找到原来的设备


        String lastDeviceNo = vervelMapper.getDeviceNoByVervelNo(vervelNo);
        String lastVervelNo = vervelMapper.getVervelNoByDeviceNo(deviceNo);
        if(deviceMapper.getByDeviceNo(deviceNo)!=null) {
            //System.out.println("deviceNo:"+lastDeviceNo+" "+deviceNo);
            if (lastDeviceNo != deviceNo && lastDeviceNo!=null) {//如果手环要绑的设备和之前绑定的不同  就先解绑
                updateDeviceConnectivityStatusByDeviceNo(false, "手环解绑", vervelNo);
            }
            //System.out.println("braceletNo:"+lastBraceletNo+" "+braceletNo);
            if(lastVervelNo != vervelNo && lastVervelNo!=null) {//如果新设备已经绑定了其他设备  就先解绑
                updateDeviceConnectivityStatusByDeviceNo(false, "手环解绑", lastVervelNo);
                vervelMapper.deleteByVervelNo(lastVervelNo);
            }


            if(vervelMapper.getVervelByVervelNo(vervelNo) == null)
                vervelMapper.createVervel(vervelNo,deviceNo,createAt);
            //与新设备建立连接
            vervelMapper.updateDeviceNoByVervelNo( deviceNo, vervelNo, createAt);
            updateDeviceConnectivityStatusByDeviceNo(true, "绑定手环", vervelNo);

            return true;
        }

        return false;

    }

    public boolean bindNewVervel(String vervelNo, String device_no) {
        Device device = deviceMapper.getByDeviceNo(device_no);
        if(device == null) {
            return false;
        }
        SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
        Date createTime = new Date();
        String createAt = mysqlSdf.format(createTime);

        vervelMapper.createVervel(vervelNo,device_no,createAt);
        updateDeviceConnectivityStatusByDeviceNo(true, "绑定手环",device_no);
        return true;
    }

    public void updateDeviceConnectivityStatusByDeviceNo(Boolean deviceConnectivityStatus, String record, String deviceNo){
        SimpleDateFormat mysqlSdf = new SimpleDateFormat(mysqlSdfPatternString);
        Date createTime = new Date();
        String createAt = mysqlSdf.format(createTime);
        deviceConnectionMapper.updateDeviceConnectivityStatusByDeviceNo(deviceConnectivityStatus, createAt, record,deviceNo);
    }
}
