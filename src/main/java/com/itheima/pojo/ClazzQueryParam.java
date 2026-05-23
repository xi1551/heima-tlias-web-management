package com.itheima.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data

//clazzs?name=java&begin=2023-01-01&end=2023-06-30&page=1&pageSize=5
public class ClazzQueryParam {

    private Integer page = 1; //页码
    private Integer pageSize = 10; //每页展示记录数
    private String name; //科目

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; //开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; //结束时间
}
