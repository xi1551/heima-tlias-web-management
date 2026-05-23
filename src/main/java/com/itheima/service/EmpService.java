package com.itheima.service;

import com.itheima.pojo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface EmpService {

    PageResult<Emp> pageSearch(EmpQueryParam empQueryParam);

    void delete(ArrayList<Integer> idArray);

    void addEmp(Emp emp);


    Emp getInfoById(Integer id);

    void updateEmp(Emp emp);

    List<Map<String, Object>> getEmpGenderData();

    empJobData getempJobData();

    List<Emp> getAll();


    LoginInfo login(Emp emp);
}
