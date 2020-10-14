package com.prisoner.mapper;

import com.prisoner.model.Prisoner;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: jojo
 * @Description:
 * @Date: Created on 2019/5/6 21:16
 */

/**
 * @Author: zhaoone
 * @Description:添加功能
 * @Date: Created on 2019/10/14
 */
public interface PrisonerMapper {

    /*
    *修改
    **/
    @Select("SELECT * FROM prisoner WHERE prisoner_id=#{prisonerId};")
    Prisoner getByPrisonerId(@Param("prisonerId") String prisonerId);


    @Select("SELECT * FROM prisoner;")
    List<Prisoner> getAll();

    @Select("SELECT feature FROM prisoner WHERE prisoner_id=#{prisonerId}")
    String getFeatureByPrisonerId(@Param("prisonerId") String prisonerId);

    @Select("SELECT * FROM prisoner WHERE prisoner_name=#{prisonerName}")
    Prisoner getByPrisonerName(@Param("prisonerName") String prisonerName);

    @Select("SELECT prisoner_name FROM prisoner WHERE prisoner_id = #{prisonerId}")
    String getPrisonerNameByPrisonerId(@Param("prisonerId")String prisonerId);
}
