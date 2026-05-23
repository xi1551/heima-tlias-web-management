package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {

    List<Clazz> List(ClazzQueryParam clazzQueryParam);

    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into clazz(name,room,begin_date,end_date,master_id,subject,create_time,update_time) " +
            "values (#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void add(Clazz clazz);


    @Select("select * from clazz where id = #{id}")
    Clazz getById(Integer id);

    void update(Clazz clazz);

    List<Clazz> getAll();
}
