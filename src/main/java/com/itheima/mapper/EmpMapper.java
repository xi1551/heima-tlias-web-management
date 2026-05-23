package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;

import org.apache.ibatis.annotations.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    @Select("select count(*) from emp where dept_id = #{id}")
    Integer getByDeptId(Integer id);


    List<Emp> List(EmpQueryParam empQueryParam);


    void deleteById(ArrayList<Integer> idArray);

    void deleteExprById(ArrayList<Integer> idArray);

    /**
     * 新增员工数据
     * 通过@Options(useGeneratedKeys = true, keyProperty = "id")注释，
     * MyBatis 会自动将数据库生成的 id 填充到emp对象中的id属性中
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void addEmp(Emp emp);


    Emp getInfoById(Integer id);

    void updateEmp(Emp emp);


    @MapKey("gender")
    List<Map<String, Object>> getEmpGenderData();

    @MapKey("pos")
    List<Map<String, Object>> getempJobData();

    List<Emp> getAll();

    /**
     * 根据用户名和密码查询员工信息
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getUsernameAndPassword(Emp emp);
}
