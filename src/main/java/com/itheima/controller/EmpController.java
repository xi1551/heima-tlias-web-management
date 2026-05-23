package com.itheima.controller;


import com.itheima.pojo.*;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController()
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;


    @GetMapping()
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询员工：{}", empQueryParam);
        PageResult<Emp> PageResult = empService.pageSearch(empQueryParam);

        return Result.success(PageResult);
    }
    @DeleteMapping()
    public Result delete(@RequestParam("ids") ArrayList<Integer> id) {
        log.info("根据id删除员工："+id);
        empService.delete(id);
        return Result.success();
    }
    @PostMapping()
    public Result addEmp(@RequestBody Emp emp){
        log.info("添加员工："+emp);
        empService.addEmp(emp);
        return Result.success();
    }

    //查询回显
    @GetMapping("/{id}")
    public Result getInfoById(@PathVariable Integer id){
        log.info("根据id查询员工："+id);
        Emp emp = empService.getInfoById(id);
        return Result.success(emp);
    }

    //修改员工信息
    @PutMapping()
    public Result updateEmp(@RequestBody Emp emp){
        log.info("修改员工信息："+emp);
        empService.updateEmp(emp);
        return Result.success();
    }

    //查询全部员工
    @GetMapping("/list")
    public Result getAll(){
        log.info("查询全部员工");
        List<Emp> list = empService.getAll();
        return Result.success(list);
    }

}
