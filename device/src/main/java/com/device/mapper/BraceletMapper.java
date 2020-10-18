package com.device.mapper;

import com.device.model.Bracelet;
import org.apache.ibatis.annotations.*;

/**
 * Created by Zhaoone on 2019/11/4
 **/
public interface BraceletMapper {

    @Select("SELECT * FROM bracelet WHERE device_no=#{deviceNo};")
    Bracelet getBraceletByDeviceNo(@Param("deviceNo") String deviceNo);

    @Select("SELECT bracelet_no FROM bracelet WHERE device_no=#{deviceNo};")
    String getBraceletNoByDeviceNo(@Param("deviceNo") String deviceNo);

    @Delete("DELETE FROM bracelet WHERE device_no=#{deviceNo};")
    void deleteByDeviceNo(@Param("deviceNo") String deviceNo);

    @Delete("DELETE FROM bracelet WHERE bracelet_no=#{braceletNo};")
    void deleteByBraceletNo(@Param("braceletNo") String braceletNo);

    @Select("select * from bracelet where create_at=(select max(create_at) from (select * from bracelet where bracelet_no =#{braceletNo}) as a)")
    Bracelet getByBraceletNo(@Param("braceletNo") String braceletNo);

    @Update("UPDATE bracelet SET device_no=#{deviceNo},create_at=#{createAt} WHERE bracelet_no=#{braceletNo}")
    void updateDeviceNoByBraceletNo(@Param("deviceNo") String deviceNo,@Param("braceletNo") String braceletNo,@Param("createAt")String createAt);

    @Select("SELECT device_no FROM bracelet WHERE bracelet_no=#{braceletNo}")
    String getDeviceNoByBraceletNo(@Param("braceletNo")String braceletNo);



    /*@Update("UPDATE bracelet SET device_no=#{deviceNo},uid=#{uid},device_status=#{deviceStatus} WHERE bracelet_no =#{braceletNo};")//@Param("deviceNo")
    void updateDeviceNoAndUidByBraceletNo(@Param("deviceNo") String deviceNo,
                                          @Param("uid") String uid,
                                          @Param("deviceStatus") Boolean deviceStatus,
                                          @Param("braceletNo") String braceletNo);*/



    /*@Select("SELECT prisoner_id FROM bracelet WHERE uid=#{uid};")
    String getPrisonerIdByUid(@Param("uid") String uid);*/

    /*@Update("UPDATE bracelet SET prisoner_id=#{prisonerId} WHERE bracelet_no=#{braceletNo};")
    void updatePrisonerIdByBraceletNo(@Param("braceletNo") String braceletNo, @Param("prisonerId") String prisonerId);*/

    @Insert("INSERT INTO bracelet(bracelet_no,device_no,create_at) " +
            "VALUES(#{braceletNo},#{deviceNo},#{createAt});")
    void createBracelet(@Param("braceletNo") String braceletNo, @Param("deviceNo") String deviceNo, @Param("createAt")String createAt);
}
