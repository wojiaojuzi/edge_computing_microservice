package com.device.mapper;

import com.device.model.DeviceGps;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface DeviceGpsMapper {
    @Delete("DELETE FROM device_gps WHERE device_no=#{deviceNo}")
    void deleteByDeviceNo(@Param("deviceNo")String deviceNo);

    @Select("select * from device_gps where create_at=(select max(create_at) from (select * from device_gps where device_no=#{deviceNo}) as a) and device_no=#{deviceNo}")
    DeviceGps getByDeviceNo(@Param("deviceNo")String deviceNo);

    @Insert("INSERT INTO device_gps SET device_no=#{deviceNo},longitude=#{longitude},latitude=#{latitude},height=#{height},create_at=#{createAt}")
    void createDeviceGps(@Param("deviceNo")String deviceNo, @Param("longitude")String longitude,
                         @Param("latitude")String latitude,@Param("height")String height,@Param("createAt")String create_at);
}
