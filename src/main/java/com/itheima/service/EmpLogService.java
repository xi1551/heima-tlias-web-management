package com.itheima.service;

import com.itheima.pojo.EmpLog;
import org.springframework.stereotype.Service;

@Service
public interface EmpLogService {
    public void insertLog(EmpLog empLog);
}
