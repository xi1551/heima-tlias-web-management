package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping()
    public Result list() {
        List<Dept> depts = deptService.findAll();
        return Result.success(depts);
    }
    @DeleteMapping()
    public Result delete(Integer id) {
        System.out.println("根据id删除部门："+id);
        deptService.delete(id);
        return Result.success();
    }

    @PostMapping()
    public Result add(@RequestBody Dept dept){
        System.out.println("添加部门："+dept);
        deptService.add(dept);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        System.out.println("根据id查询部门："+id);
        Dept dept = deptService.findById(id);
        return Result.success(dept);

    }

    @PutMapping()
    public Result update(@RequestBody Dept dept) {
        System.out.println("修改部门："+dept);
        deptService.update(dept);
        return Result.success();
    }
}
