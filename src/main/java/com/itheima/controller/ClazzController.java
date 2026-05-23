package com.itheima.controller;

import com.itheima.exception.BusinessException;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
@RestControllerAdvice
public class ClazzController {
    @Autowired
    private ClazzService clazzService;


    @ExceptionHandler
    public Result exceptionHandler(BusinessException e){
        log.error("业务异常：{}",e.getMessage());
        return Result.error(e.getMessage());
    }

    //查询班级
    @GetMapping()
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("分页查询班级：{}", clazzQueryParam);
        PageResult<Clazz> PageResult = clazzService.pageSearch(clazzQueryParam);
        return Result.success(PageResult);
    }

    //删除班级
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除班级：{}", id);
        clazzService.delete(id);
        return Result.success();
    }

    //添加班级
    @PostMapping()
    public Result add(@RequestBody Clazz clazz) {
        log.info("添加班级：{}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    //根据主键ID查询班级的信息
    public Result getById(@PathVariable Integer id) {
        log.info("根据主键ID查询班级的信息：{}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    //修改班级的数据信息
    @PutMapping()
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级的数据信息：{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    //查询所有班级信息
    @GetMapping("/list")
    public Result getAll(){
        List<Clazz> clazzs = clazzService.getAll();
        return Result.success(clazzs);
    }
}
