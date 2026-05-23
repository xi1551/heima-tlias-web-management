package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.LogQueryParam;
import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;
import com.itheima.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageResult<OperateLog> pageSearch(LogQueryParam logQueryParam) {
        PageHelper.startPage(logQueryParam.getPage(),logQueryParam.getPageSize());
        List<OperateLog> operateLogs = operateLogMapper.pageSearch(logQueryParam);
        Page<OperateLog> page = (Page<OperateLog>) operateLogs;

        return  new PageResult<>(page.getResult(),page.getTotal());
    }
}
