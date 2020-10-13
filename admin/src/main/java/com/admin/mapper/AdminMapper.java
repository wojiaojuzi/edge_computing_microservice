package com.admin.mapper;

import com.admin.model.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by Zhaoone on 2019/10/22
 **/
public interface AdminMapper {
    @Select("SELECT * FROM admin WHERE admin_id=#{adminId} AND password=#{password};")
    Admin getByAdminIdAndPassword(@Param("adminId") String adminId, @Param("password") String password);

    @Update("UPDATE admin SET token_created_at=#{tokenCreatedTime} WHERE admin_id=#{adminId};")
    void updateTokenCreateTimeByadminId(@Param("tokenCreatedTime") String tokenCreatedTime, @Param("adminId") String adminId);

    @Select("SELECT token_created_at FROM admin WHERE admin_id=#{adminId};")
    String getTokenCreatedTime(@Param("adminId") String adminId);

    @Select("SELECT * FROM admin WHERE admin_id =#{adminId};")
    Admin getByadminId(@Param("adminId") String adminId);
}
