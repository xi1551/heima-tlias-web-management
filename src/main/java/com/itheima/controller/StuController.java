package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.StuQueryParam;
import com.itheima.pojo.Student;
import com.itheima.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/students")
public class StuController {
    @Autowired
    private StuService stuService;

    //分页查询学生
    @GetMapping()
    public Result page(StuQueryParam stuQueryParam) {
        log.info("分页查询学生：{}", stuQueryParam);
        PageResult<Student> PageResult = stuService.pageSearch(stuQueryParam);
        return Result.success(PageResult);
    }

    //删除学员
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable ArrayList<Integer> ids) {
        log.info("删除学员：{}", ids);
        stuService.delete(ids);
        return Result.success();
    }

    //添加学员信息
    @PostMapping()
    public Result addStu(@RequestBody Student student){
        log.info("添加学员信息：{}", student);
        stuService.add(student);
        return Result.success();
    }

    //ID查询学员的信息
    @GetMapping("/{id}")
    public Result getInfoById(@PathVariable Integer id){
        log.info("根据id查询学员信息：{}", id);
        Student student = stuService.getInfoById(id);
        return Result.success(student);
    }

    //修改学员的数据信息
    @PutMapping()
    public Result updateStu(@RequestBody Student student){
        log.info("修改学员信息：{}", student);
        stuService.update(student);
        return Result.success();
    }

    //违纪处理
    @PutMapping("/violation/{id}/{score}")
    public Result updateStu(@PathVariable Integer id,@PathVariable Integer score){
        log.info("违纪处理：{},{}", id,score);
        stuService.vioupdate(id,score);
        return Result.success();
    }
}
