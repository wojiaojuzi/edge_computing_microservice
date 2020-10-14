package com.device.mapper;

import com.device.model.DeviceState;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface DeviceStateMapper {
    @Delete("DELETE FROM device_state WHERE device_no=#{deviceNo}")
    void deleteByDeviceNo(@Param("deviceNo")String deviceNo);

    @Select("select * from device_state where create_at=(select max(create_at) from (select * from device_state where device_no=#{deviceNo}) as a) and device_no=#{deviceNo}")
    DeviceState getByDeivceNo(@Param("deviceNo")String deviceNo);

    @Insert("INSERT INTO device_state(create_at,dump_energy_rate,cpu_usage_rate,memory_usage_rate,device_no) " +
            "VALUES(#{createAt},#{dumpEnergyRate},#{cpuUsageRate},#{memoryUsageRate},#{deviceNo})")
    void createDeviceState( @Param("createAt") String createAt,@Param("dumpEnergyRate") String dumpEnergyRate,
                            @Param("cpuUsageRate") String cpuUsageRate, @Param("memoryUsageRate") String memoryUsageRate,
                            @Param("deviceNo") String deviceNo);
}
