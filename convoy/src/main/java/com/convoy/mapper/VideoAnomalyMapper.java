package com.convoy.mapper;

import com.convoy.model.VideoAnomaly;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface VideoAnomalyMapper {
    @Select("SELECT * FROM video_anomaly" +
            " WHERE create_at =" +
            " (SELECT MAX(create_at) FROM video_anomaly " +
            " WHERE car_no = #{carNo}) AND car_no=#{carNo};")
    VideoAnomaly getLastestByCarNo(@Param("carNo") String carNo);

    @Select("SELECT * FROM video_anomaly WHERE car_no=#{carNo} ORDER BY create_at DESC")
    List<VideoAnomaly> getSomeByCarNo(@Param("carNo") String carNo);

    @Select("SELECT * FROM video_anomaly WHERE car_no=#{carNo} and id<=#{id} ORDER BY create_at DESC LIMIT 10")
    List<VideoAnomaly> getSomeByCarNoAndId(@Param("id") String id, @Param("carNo") String carNo);
}
