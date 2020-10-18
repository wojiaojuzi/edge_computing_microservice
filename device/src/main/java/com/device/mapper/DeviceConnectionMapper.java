package com.device.mapper;

import com.device.model.Device;
import com.device.model.DeviceConnection;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

public interface DeviceConnectionMapper {
    @Select("select * from device_connection where create_at=(select max(create_at) from (select * from device_connection where device_no=#{deviceNo}) as a) and device_no=#{deviceNo}")
    DeviceConnection getByDeviceNo(@Param("deviceNo")String deviceNo);

    @Select("select device_connectivity_status from device_connection where create_at=(select max(create_at) from (select * from device_connection where device_no=#{deviceNo}) as a) and device_no=#{deviceNo}")
    Boolean getDeviceConnectivityStatusByDeviceNo(@Param("deviceNo")String deviceNo);

    /*@Update("UPDATE device_connection SET device_connectivity_status=#{deviceConnectivity},record=#{record} WHERE device_no=#{deviceNo}")
    void updateDeviceConnectivityStatusAndRecordByDeviceNo(@Param("deviceConnectivity")boolean deviceConnectivity,
                 @Param("record")String record,@Param("deviceNo")String deviceNo);*/

    @Insert("INSERT INTO device_connection SET device_connectivity_status=#{deviceConnectivity},create_at=#{createAt},record=#{record},device_no=#{deviceNo}")
    void updateDeviceConnectivityStatusByDeviceNo(@Param("deviceConnectivity")boolean deviceConnectivity,
                                                           @Param("createAt") String createAt,
                                                           @Param("record")String record,@Param("deviceNo")String deviceNo);

    @Delete("DELETE FROM device_connection WHERE device_no=#{deviceNo}")
    void deleteByDeviceNo(@Param("deviceNo")String deviceNo);
}
