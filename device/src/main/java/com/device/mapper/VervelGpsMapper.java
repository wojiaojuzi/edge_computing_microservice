package com.device.mapper;


import com.device.model.VervelGps;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface VervelGpsMapper {
    @Select("select * from vervel_gps where create_at=(select max(create_at) from (select * from vervel_gps where vervel_no=#{vervelNo}) as a) and vervel_no=#{vervelNo}")
    VervelGps getByVervelNo(@Param("vervelNo") String VervelNo);
}
