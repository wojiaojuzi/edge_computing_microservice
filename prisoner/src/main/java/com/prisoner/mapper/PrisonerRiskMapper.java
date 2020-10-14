package com.prisoner.mapper;

import com.prisoner.model.PrisonerRisk;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PrisonerRiskMapper {
    @Insert("INSERT INTO prisoner_risk(prisoner_id,create_at,risk_value) VALUES(#{prisonerId},#{createAt},#{riskValue})")
    void createPrisonerRisk(@Param("prisonerId") String prisonerId,@Param("createAt")String createAt,@Param("riskValue")String riskValue);

    @Select("SELECT * FROM prisoner_risk " +
            "WHERE create_at = " +
            "  (SELECT MAX(create_at) FROM prisoner_risk " +
            "   WHERE prisoner_id = #{prisonerId}) AND prisoner_id=#{prisonerId};")
    PrisonerRisk getByPrisonerId(@Param("prisonerId") String prisonerId);

    @Select("SELECT * FROM prisoner_risk WHERE risk_value >= #{riskValue}")
    List<PrisonerRisk> getAllHighRisk(@Param("riskValue") String riskValue);

    @Select("SELECT * FROM prisoner_risk WHERE prisoner_id= #{prisonerId}")
    List<PrisonerRisk> getPrisonerRiskByPrisonerId(@Param("prisonerId") String prisonerId);
}
