package com.prisoner.mapper;


import com.prisoner.model.Task;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Zhaoone on 2019/10/22
 **/
public interface TaskMapper {

    @Select("SELECT * FROM task WHERE task_no=#{taskNo};")
    Task getByTaskNo(@Param("taskNo") String taskNo);

    @Select("SELECT * FROM task")
    List<Task> getAllTasks();

    @Select("SELECT * FROM task WHERE car_no=#{carNo};")
    List<Task> getByCarNo(@Param("carNo") String carNo);

    @Select("SELECT * FROM task WHERE prisoner_name=#{prisonerName};")
    Task getByPrisonerName(@Param("prisonerName") String prisonerName);

    @Select("SELECT * FROM task WHERE user_name=#{userName};")
    Task getByUserName(@Param("userName") String userName);

    @Select("SELECT car_no FROM task WHERE prisoner_name=#{prisonerName};")
    String getCarNoByPrisonerName(@Param("prisonerName") String prisonerName);

    @Select("SELECT count(SCHEMA_NAME) as SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME=#{SCHEMA_NAME}")
    Integer getSchemaStatus(@Param("SCHEMA_NAME")String SCHEMA_NAME);

    @Select("select count(table_name) from information_schema.TABLES where TABLE_NAME = #{tableName} and TABLE_SCHEMA = #{SCHEMA_NAME}")
    Integer getTableStatus(@Param("tableName")String tableName,@Param("SCHEMA_NAME")String SCHEMA_NAME);

    @Select("SELECT count(task_no) FROM task")
    Integer getTaskNumber();
}
