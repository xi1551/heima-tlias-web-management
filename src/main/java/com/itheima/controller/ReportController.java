package com.itheima.controller;


import com.itheima.pojo.Result;
import com.itheima.pojo.StudentCountData;
import com.itheima.pojo.empJobData;
import com.itheima.service.impl.EmpServiceImpl;
import com.itheima.service.impl.StuServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private EmpServiceImpl empService;
    @Autowired
    private StuServiceImpl stuService;


    @GetMapping("/empGenderData")
    public Result GetempGenderDate() {
        log.info("获取员工性别数据");
        List<Map<String, Object>> list = empService.getEmpGenderData();
      return Result.success(list);
    }

    @GetMapping("/empJobData")
    public Result GetempAgeDate() {
        log.info("获取员工年龄数据");
        empJobData list = empService.getempJobData();
        return Result.success(list);
    }

    //统计学员的学历信息
    @GetMapping("/studentDegreeData")
    public Result GetStudentDegreeData() {
        log.info("获取学员的学历信息");
        List<Map<String, Object>> list = stuService.GetStudentDegreeData();
        return Result.success(list);
    }

    //统计每一个班级的人数
    @GetMapping("/studentCountData")
    public Result GetStudentCountData() {
        log.info("获取每一个班级的人数");
        StudentCountData studentCountData = stuService.GetStudentCountData();
        return Result.success(studentCountData);
    }


}
