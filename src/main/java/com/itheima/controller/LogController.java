package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.LogService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/page")
    public Result list(LogQueryParam logQueryParam){
        PageResult<OperateLog> PageResult = logService.pageSearch(logQueryParam);

        return Result.success(PageResult);
    }
}
