package com.itheima.pojo;

import lombok.Data;

@Data
public class LogQueryParam {
    private Integer page = 1; //页码
    private Integer pageSize = 10; //每页展示记录数
}
