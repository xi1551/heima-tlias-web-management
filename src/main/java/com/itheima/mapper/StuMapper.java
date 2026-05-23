package com.itheima.mapper;

import com.itheima.pojo.StuQueryParam;
import com.itheima.pojo.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface StuMapper {

    List<Student> List(StuQueryParam stuQueryParam);


    void deleteById(ArrayList<Integer> ids);


    @Insert("insert into student(name,no,gender,degree,clazz_id,phone,id_card,is_college,address,graduation_date) "+
            "values(#{name},#{no},#{gender},#{degree},#{clazzId},#{phone},#{idCard},#{isCollege},#{address},#{graduationDate})")
    void add(Student student);

    @Select("select * from student where id = #{id}")
    Student getById(Integer id);

    void update(Student student);

    void vioupdate(Integer id, Integer score, LocalDateTime now);

    @Select("select COUNT(*) from student where clazz_id = #{ClazzId}")
    Integer getByClazzId(Integer ClazzId);

    @MapKey("degree")
    List<Map<String, Object>> GetStudentDegreeData();

    @MapKey("clazzname")
    List<Map<String, Object>> GetStudentCountData();
}
