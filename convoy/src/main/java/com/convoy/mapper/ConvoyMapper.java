package com.convoy.mapper;

import com.convoy.model.Convoy;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ConvoyMapper {
    @Select("SELECT * FROM convoy WHERE user_id = #{userId}")
    Convoy getConvoyByUserId(@Param("userId") String userId);

    @Select("SELECT DISTINCT car_no FROM convoy WHERE prisoner_id = #{prisonerId}")
    String getCarNoByPrisonerId(@Param("prisonerId") String prisonerId);

    @Select("select distinct * from convoy where car_no=#{carNo} group by car_no")
    List<Convoy> getNoRepeat(@Param("carNo")String carNo);

    @Select("SELECT * FROM convoy WHERE car_no = #{carNo}")
    List<Convoy> getByCarNo(@Param("carNo")String carNo);

    @Select("SELECT distinct car_no FROM convoy WHERE task_no=#{taskNo}")
    List<String> getCarNoByTaskNo(@Param("taskNo")String TaskNo);

    @Select("SELECT distinct task_no FROM convoy WHERE car_no=#{carNo}")
    String getTaskNoByCarNo(@Param("carNo")String carNo);

    @Select("SELECT user_id FROM convoy WHERE car_no=#{carNo}")
    List<String> getUserIdByCarNo(@Param("carNo")String carNo);

    @Select("SELECT prisoner_id FROM convoy WHERE car_no=#{carNo}")
    List<String> getPrisonerIdByCarNo(@Param("carNo")String carNo);

    @Select("SELECT prisoner_id FROM convoy WHERE user_id = #{userId}")
    String getPrisonerIdByUserId(@Param("userId")String userId);

    @Select("SELECT task_no FROM convoy WHERE prisoner_id = #{prisonerId}")
    String getTaskNoByPrisonerId(@Param("prisonerId")String prisonerId);
}
