package com.convoy.mapper;

import com.convoy.model.Car;
import com.convoy.model.Response.CarCameraResponse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Zhaoone on 2019/10/21
 **/
public interface CarMapper {

    @Select("SELECT * FROM car;")
    List<Car> getAll();

    @Select("SELECT * FROM car WHERE car_no=#{carNo};")
    Car getByCarNo(@Param("carNo") String carNo);

    @Select("SELECT car_no FROM car WHERE type='指挥车' ")
    String getCommandCarNo();

    @Select("SELECT car_no FROM car WHERE type='押解车'")
    List<String> getCarNo();

    @Select("SELECT car_no,car_inner_video_url,car_outer_video_url,camera_inner_ip,camera_outer_ip FROM car WHERE type=#{type}")
    List<CarCameraResponse> getCarCamera(@Param("type")String type);
}
