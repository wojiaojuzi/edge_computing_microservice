package com.convoy.mapper;

import com.convoy.model.Camera;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.scheduling.annotation.Scheduled;

public interface CameraMapper {
    @Select("SELECT * FROM camera WHERE car_no=#{carNo}")
    Camera getCameraByCarNo(@Param("carNo")String carNo);
}
