package com.device.mapper;

import com.device.model.Vervel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by Zhaoone on 2019/11/4
 **/
public interface VervelMapper {

    @Select("SELECT * FROM vervel WHERE device_no=#{deviceNo};")
    Vervel getVervelByDeviceNo(@Param("deviceNo") String deviceNo);

    @Select("SELECT vervel_no FROM vervel WHERE device_no=#{deviceNo}")
    String getVervelNoByDeviceNo(@Param("deviceNo")String deviceNo);

    @Select("SELECT * FROM vervel WHERE vervel_no=#{vervelNo}")
    Vervel getVervelByVervelNo(@Param("vervelNo")String vervelNo);

    @Update("UPDATE vervel SET device_no=#{deviceNo},create_at=#{createAt} WHERE vervel_no=#{vervelNo}")
    void updateDeviceNoByVervelNo(@Param("deviceNo")String deviceNo, @Param("vervelNo")String vervelNo ,@Param("createAt")String createAt);

    /*@Update("UPDATE vervel SET device_status=#{deviceStatus} WHERE device_no=#{deviceNo};")
    Integer updateDeviceStatusByDeviceNo(@Param("deviceStatus") boolean deviceStatus, @Param("deviceNo") String deviceNo);*/

    @Delete("DELETE FROM vervel WHERE device_no=#{deviceNo};")
    void deleteByDeviceNo(@Param("deviceNo") String deviceNo);

    /*@Select("SELECT vervel_no FROM vervel WHERE uid=#{uid};")
    String getVervelNoByUid(@Param("uid") String uid);

    @Select("SELECT id FROM vervel WHERE uid=#{uid};")
    String getVervelIdByUid(@Param("uid") String uid);

    @Update("UPDATE vervel SET device_no=#{deviceNo},uid=#{uid},device_status=#{deviceStatus} WHERE vervel_no =#{vervelNo};")
    void updateDeviceNoAndUidByVervelNo(@Param("deviceNo") String deviceNo,
                               @Param("uid") String uid,
                               @Param("deviceStatus") Boolean deviceStatus,
                               @Param("vervelNo") String vervelNo);

    @Select("SELECT vervel_no FROM vervel WHERE prisoner_id=#{prisonerId};")
    String getVervelNoByPrisonerId(@Param("prisonerId") String prisonerId);*/
}
