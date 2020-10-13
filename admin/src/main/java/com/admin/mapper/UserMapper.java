package com.admin.mapper;

import com.admin.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: zhaoone
 * @Description:添加功能
 * @Date: Created on 2019/10/14
 */
public interface UserMapper {

    @Select("SELECT * FROM user WHERE user_name=#{userName};")
    User getByUserName(@Param("userName") String userName);

    @Select("SELECT username FROM user WHERE userId=#{userId};")
    String getUserNameByUserId(@Param("userId") String userId);

    @Select("SELECT * FROM user WHERE user_id=#{userId} AND password=#{password};")
    User getByUserIdAndPassword(@Param("userId") String userId, @Param("password") String password);

    @Select("SELECT token_create_at FROM user WHERE user_id =#{id};")
    String getTokenCreateTime(@Param("id") String id);

    @Insert("INSERT INTO user(user_id,username,password,id_card,create_at,token_create_at) " +
            "VALUES(#{userId},#{userName},#{password},#{idCard},#{createAt},#{tokenCreateAt});")
    void createUser(User user);

    @Update("UPDATE user SET token_create_at=#{tokenCreateTime} WHERE user_id =#{userId};")
    void updateTokenCreateTimeByUserId(@Param("tokenCreateTime") String tokenCreateTime, @Param("userId") String userId);

    @Update("UPDATE user SET prisoner_id = #{prisonerId} WHERE id = #{id};")       //prisonerId修改
    void updatePrisonerIdById(@Param("prisonerId") String prisonerId, @Param("id") String id);


    @Update("UPDATE user SET username=#{userName} WHERE id =#{id};")
    void updateUserNameById(@Param("userName") String userName, @Param("id") String id);

    @Update("UPDATE user SET password=#{password} WHERE id =#{id};")
    void updatePasswordById(@Param("password") String password, @Param("id") String id);

    @Update("UPDATE user SET phone=#{phone} WHERE id =#{id};")
    void updatePhoneById(@Param("phone") String phone, @Param("id") String id);

    @Delete("DELETE FROM user WHERE id=#{id};")
    void deleteById(@Param("id") String id);


    @Select("SELECT id FROM user WHERE username=#{userName};")
    String getUserIdByUserName(@Param("userName") String userName);

    @Select("SELECT * FROM user;")
    List<User> getAll();

    @Select("SELECT * FROM user WHERE user_id=#{userId};")
    User getByUserId(@Param("userId") String userId);
}
