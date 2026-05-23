package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询所有部门
     */
//    @Select("select id, name, create_time createTime, update_time updateTime from dept")
//    public List<Dept> findAll();
    @Select("select id,name,create_time,update_time from dept")
    public List<Dept> findAll();

    @Delete("delete from dept where id = #{id}")
    public void delete(Integer id);

    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    public boolean add(Dept dept);

    @Select("select id,name,create_time,update_time from dept where id = #{id}")
    public Dept findById(Integer id);

    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
