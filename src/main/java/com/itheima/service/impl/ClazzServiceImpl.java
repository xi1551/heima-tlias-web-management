package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.exception.BusinessException;
import com.itheima.mapper.ClazzMapper;
import com.itheima.mapper.StuMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private StuMapper stuMapper;

    @Override
    public PageResult<Clazz> pageSearch(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        List<Clazz> clazzs = clazzMapper.List(clazzQueryParam);
        for (Clazz clazz : clazzs){
            if(LocalDate.now().isAfter(clazz.getEndDate())){
                clazz.setStatus("已结课");
            }else if(LocalDate.now().isBefore(clazz.getBeginDate())){
                clazz.setStatus("未开班");
            }else {
                clazz.setStatus("在读中");
            }
        }
        Page<Clazz> page = (Page<Clazz>) clazzs;
        return new PageResult<>(page.getResult(),page.getTotal());

    }

    @Override
    public void delete(Integer id) {
        if(stuMapper.getByClazzId(id)>0){
            throw new BusinessException("对不起, 该班级下有学生, 不能直接删除");
        }
        clazzMapper.deleteById(id);
    }

    @Override
    public void add(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazz.setCreateTime(LocalDateTime.now());
        clazzMapper.add(clazz);
    }

    @Override
    public Clazz getById(Integer id) {

        return clazzMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Override
    public List<Clazz> getAll() {
        List<Clazz> clazzs = clazzMapper.getAll();
        return clazzs;
    }
}
