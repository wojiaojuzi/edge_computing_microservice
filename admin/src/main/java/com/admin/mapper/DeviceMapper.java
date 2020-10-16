package com.admin.mapper;

import com.admin.model.Device;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DeviceMapper {

    //property是实体类属性，column是数据库字段
    @Select("SELECT * FROM device WHERE device_no=#{deviceNo};")
    Device getByDeviceNo(@Param("deviceNo") String deviceNo);

    @Select("SELECT * FROM device WHERE user_id=#{userId} AND device_type=#{deviceType};")
    Device getByUserIdAndType(@Param("userId") String userId, @Param("deviceType") String deviceType);

    @Select("SELECT * FROM device WHERE user_id=#{userId}")
    Device getByUserId(@Param("userId") String userId);

    @Select("SELECT * FROM device")
    List<Device> getAll();

    @Insert("INSERT INTO device(device_no,user_id,device_type) VALUES(#{deviceNo},#{userId},#{deviceType});")
    Integer createDevice(Device device);

    @Delete("DELETE FROM device WHERE device_no=#{deviceNo};")
    void deleteByDeviceNo(@Param("deviceNo") String deviceNo);

    @Update("UPDATE device SET device_status=#{deviceStatus} WHERE device_no=#{deviceNo};")
    Integer updateDeviceStatusByDeviceNo(@Param("deviceStatus") boolean deviceStatus, @Param("deviceNo") String deviceNo);

    @Select("SELECT device_status FROM device WHERE device_no =#{deviceNo};")
    Boolean getDeviceStatusByDeviceNo(@Param("deviceNo") String deviceNo);

    @Select("SELECT device_no FROM device WHERE user_id = #{userId}")
    String getDeviceNoByUserId(@Param("userId")String userId);

    @Update("UPDATE device SET user_id=#{user_id} WHERE device_no=#{deviceNo};")
    Integer updateDeviceUserByDeviceNo(@Param("user_id") String user_id, @Param("deviceNo") String deviceNo);

    @Update("UPDATE device SET device_connectivity_status=#{deviceConnectivityStatus} WHERE device_no =#{deviceNo};")
    void updateDeviceConnectivityStatusByDeviceNo(@Param("deviceConnectivityStatus") Boolean deviceConnectivityStatus,
                                  @Param("deviceNo") String deviceNo);
}
