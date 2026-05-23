package com.itheima.service.impl;

import com.itheima.anno.LogOperation;
import com.itheima.exception.BusinessException;
import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceimpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> findAll() {
        List<Dept> depts = deptMapper.findAll();
        return depts;

    }

    @LogOperation
    @Override
    public void delete(Integer id) {
        if(empMapper.getByDeptId(id)>0){
            throw new BusinessException("对不起，当前部门下有员工，不能直接删除！");
        }
        deptMapper.delete(id);
    }

    @LogOperation
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        try {
            deptMapper.add(dept);
        } catch (org.springframework.dao.DuplicateKeyException e) {
            throw new BusinessException("部门名称已存在");
        }
    }

    @LogOperation
    @Override
    public Dept findById(Integer id) {

        return deptMapper.findById(id);
    }

    @LogOperation
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);

    }
}
