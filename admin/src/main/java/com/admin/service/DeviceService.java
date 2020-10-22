package com.admin.service;

import com.admin.mapper.*;
import com.admin.model.*;
import com.admin.model.Exception.EdgeComputingServiceException;
import com.admin.model.Request.DeviceRegisterRequest;
import com.admin.model.Response.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DeviceService {
    private static final String mysqlSdfPatternString = "yyyy-MM-dd HH:mm:ss";

    private final DeviceMapper deviceMapper;

    @Autowired
    public DeviceService(DeviceMapper deviceMapper) {
        this.deviceMapper = deviceMapper;
    }

    public Device createDevice(DeviceRegisterRequest deviceRegisterRequest) {
        if(deviceRegisterRequest.getUserId() == null || deviceRegisterRequest.getDeviceType() == null || deviceRegisterRequest.getDeviceNo() == null) {
            return null;
        } else {
            String userId = deviceRegisterRequest.getUserId();
            String deviceType = deviceRegisterRequest.getDeviceType();
            String deviceNo = deviceRegisterRequest.getDeviceNo();
            Device de = deviceMapper.getByDeviceNo(deviceNo);
            if(de == null||de.getUserId().equals(userId)) {
                if (deviceType.equals("一体化终端") || deviceType.equals("手持机")) {
                    if (deviceMapper.getByDeviceNo(deviceNo) != null) {
                        deviceMapper.updateDeviceUserByDeviceNo(userId, deviceNo);
                        return deviceMapper.getByDeviceNo(deviceNo);
                    } else {
                        Device device = new Device();
//                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
//                String date = df.format(new Date());
//                String deviceNo = deviceRegisterRequest.getDeviceType() + date;
                        device.setDeviceNo(deviceNo);
                        device.setDeviceType(deviceType);
                        device.setUserId(userId);
                        deviceMapper.createDevice(device);
                        return deviceMapper.getByDeviceNo(deviceNo);
                    }
                } else {
                    return null;
                }
            }
            else
                throw new EdgeComputingServiceException(ResponseEnum.DEVICE_HAS_CREATED.getCode(), ResponseEnum.DEVICE_HAS_CREATED.getMessage());
        }
    }
}

