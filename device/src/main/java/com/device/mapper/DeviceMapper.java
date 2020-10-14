package com.device.mapper;

import com.device.model.Device;
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

    @Select("SELECT * FROM device;")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "device_no", property = "deviceNo"),
            @Result(column = "device_status", property = "deviceStatus"),
            @Result(column = "device_type", property = "deviceType"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "create_at", property = "createAt"),
            @Result(column = "uid", property = "user", one = @One(select = "com.ecs.mapper.UserMapper.getById"))
    })
    List<Device> getAll();

    @Insert("INSERT INTO device(device_no,device_status,uid,device_type) VALUES(#{deviceNo},#{deviceStatus},#{uid},#{deviceType});")
    Integer createDevice(Device device);

    @Delete("DELETE FROM device WHERE device_no=#{deviceNo};")
    void deleteByDeviceNo(@Param("deviceNo") String deviceNo);

    @Update("UPDATE device SET device_status=#{deviceStatus} WHERE device_no=#{deviceNo};")
    Integer updateDeviceStatusByDeviceNo(@Param("deviceStatus") boolean deviceStatus, @Param("deviceNo") String deviceNo);

    @Select("SELECT device_status FROM device WHERE device_no =#{deviceNo};")
    Boolean getDeviceStatusByDeviceNo(@Param("deviceNo") String deviceNo);

    @Select("SELECT device_no FROM device WHERE user_id = #{userId}")
    String getDeviceNoByUserId(@Param("userId")String userId);

    @Update("UPDATE device SET device_connectivity_status=#{deviceConnectivityStatus} WHERE device_no =#{deviceNo};")
    void updateDeviceConnectivityStatusByDeviceNo(@Param("deviceConnectivityStatus") Boolean deviceConnectivityStatus,
                                  @Param("deviceNo") String deviceNo);
}
