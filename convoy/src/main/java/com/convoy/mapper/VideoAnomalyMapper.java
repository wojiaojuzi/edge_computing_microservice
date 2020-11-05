package com.convoy.mapper;

import com.convoy.model.VideoAnomaly;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface VideoAnomalyMapper {
    @Select("SELECT * FROM video_anomaly" +
            " WHERE create_at =" +
            " (SELECT MAX(create_at) FROM video_anomaly " +
            " WHERE car_no = #{carNo}) AND car_no=#{carNo};")
    VideoAnomaly getLastestByCarNo(@Param("carNo") String carNo);


}
