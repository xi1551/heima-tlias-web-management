package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StuMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.StuQueryParam;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentCountData;
import com.itheima.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StuServiceImpl implements StuService {
    @Autowired
    private StuMapper stuMapper;

    @Override
    public PageResult<Student> pageSearch(StuQueryParam stuQueryParam) {
        PageHelper.startPage(stuQueryParam.getPage(),stuQueryParam.getPageSize());
        List<Student> list = stuMapper.List(stuQueryParam);
        Page<Student> page = (Page<Student>) list;
        return new PageResult<>(page.getResult(),page.getTotal());

    }

    @Override
    public void delete(@RequestParam("ids") ArrayList<Integer> ids) {
        stuMapper.deleteById(ids);
    }

    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        stuMapper.add(student);
    }

    @Override
    public Student getInfoById(Integer id) {
        return stuMapper.getById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        stuMapper.update(student);
    }

    @Override
    public void vioupdate(Integer id, Integer score) {
        LocalDateTime now = LocalDateTime.now();
        stuMapper.vioupdate(id,score,now);
    }

    @Override
    public List<Map<String, Object>> GetStudentDegreeData() {
         List<Map<String, Object>> list = stuMapper.GetStudentDegreeData();
        return list;
    }

    @Override
    public StudentCountData GetStudentCountData() {
        List<Map<String, Object>> list = stuMapper.GetStudentCountData();
        List<String> clazzList=list.stream().map(map->(String) map.get("clazzname")).toList();
        List<Long> dataList=list.stream().map(map->(Long) map.get("value")).toList();
        StudentCountData studentCountData = new StudentCountData();
        studentCountData.setClazzList(clazzList);
        studentCountData.setDataList(dataList);
        return studentCountData;


    }

}
