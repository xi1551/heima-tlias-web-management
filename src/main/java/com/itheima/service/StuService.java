package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.StuQueryParam;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentCountData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface StuService {
    PageResult<Student> pageSearch(StuQueryParam stuQueryParam);

    void delete(ArrayList<Integer> ids);

    void add(Student student);

    Student getInfoById(Integer id);

    void update(Student student);

    void vioupdate(Integer id, Integer score);

    List<Map<String, Object>> GetStudentDegreeData();

     StudentCountData GetStudentCountData();
}
