package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

/*
 * 员工服务实现类
 */
@Slf4j
@Service
public class EmpServiceImpl implements EmpService {


    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogServiceImpl empLogServiceImpl;


    @Override
    public PageResult<Emp> pageSearch(EmpQueryParam empQueryParam) {

        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        List<Emp> emps = empMapper.List(empQueryParam);
        Page<Emp> page = (Page<Emp>) emps;

        return new PageResult<>(page.getResult(), page.getTotal());
    }

    //删除员工和员工经验
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(ArrayList<Integer> idArray) {

            empMapper.deleteById(idArray);
            log.info("删除员工经验：");
            empMapper.deleteExprById(idArray);

    }

    @Transactional(rollbackFor = Exception.class)
    //通过rollbackFor这个属性可以指定出现何种异常类型回滚事务。
    @Override
    public void addEmp(Emp emp) {
    try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            //保存基本信息
            empMapper.addEmp(emp);
            //保存经验信息
            Integer ID = emp.getId();
            if (!CollectionUtils.isEmpty(emp.getExprList())) {
                for (EmpExpr empExpr : emp.getExprList()) {
                    empExpr.setEmpId(ID);
                }
                log.info("添加员工经验：" + emp.getExprList());
                empExprMapper.addEmpExpr(emp.getExprList());
            }
    }finally {
            //操作日志
            log.info("添加员工日志：");
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), emp.toString());
            empLogServiceImpl.insertLog(empLog);
        }
    }

    //查询回显
    @Override
    public Emp getInfoById(Integer id) {
        return empMapper.getInfoById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateEmp(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        //修改基本信息
        empMapper.updateEmp(emp);
        //修改经验
        empMapper.deleteExprById(new ArrayList<>(Arrays.asList(emp.getId())));

        Integer empid = emp.getId();
        if(!CollectionUtils.isEmpty(emp.getExprList())){
            for(EmpExpr empExpr:emp.getExprList()){
                empExpr.setEmpId(empid);
            }
            empExprMapper.addEmpExpr(emp.getExprList());
        }
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.getEmpGenderData();
    }

    @Override
    public empJobData getempJobData() {
        List<Map<String, Object>> list = empMapper.getempJobData();
        List<String> jobList = list.stream().map(map -> (String) map.get("pos")).toList();
        List<Long> dataList = list.stream().map(map -> (Long) map.get("num")).toList();
        empJobData empJobData = new empJobData();
        empJobData.setJobList(jobList);
        empJobData.setDataList(dataList);

        return empJobData;
    }

    @Override
    public List<Emp> getAll() {
        return empMapper.getAll();
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp empLogin = empMapper.getUsernameAndPassword(emp);
        if(empLogin != null){
            //1. 生成JWT令牌
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("id", empLogin.getId());
            dataMap.put("username", empLogin.getUsername());
            String jwt = JwtUtils.generateJwt(dataMap);
            LoginInfo loginInfo = new LoginInfo(empLogin.getId(), empLogin.getUsername(), empLogin.getName(), jwt);
            return loginInfo;
        }

        return null;
    }


}
