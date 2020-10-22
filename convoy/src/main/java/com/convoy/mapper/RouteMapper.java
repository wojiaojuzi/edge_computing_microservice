package com.convoy.mapper;

import com.convoy.model.Response.CoordinateResponse;
import com.convoy.model.Route;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RouteMapper {
    @Select("SELECT * FROM route")
    List<Route> getAllRoute();

    @Select("SELECT * FROM route WHERE point_id=#{pointId}")
    Route getByPointId(@Param("pointId")int pointId);

    @Select("SELECT longitude,latitude From route")
    List<CoordinateResponse> getCoordinateResponse();
}
