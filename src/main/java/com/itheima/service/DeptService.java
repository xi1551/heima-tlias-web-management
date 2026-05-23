package com.itheima.service;

import com.itheima.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DeptService {
    public List<Dept> findAll();

    public void delete(Integer id);

    public void add(Dept dept);

    public Dept findById(Integer id);

    public void update(Dept dept);
}
