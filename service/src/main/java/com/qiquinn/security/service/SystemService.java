package com.qiquinn.security.service;

import com.qiquinn.security.entity.SystemInfo;

import java.util.List;

public interface SystemService
{
    //增加
    public void insert(SystemInfo systeminfo);
    //删除
    public void delete(SystemInfo systeminfo);
    //修改
    public void update(SystemInfo systeminfo);
    //查询
    public List<SystemInfo> select();
    //模糊查询
    public List<SystemInfo> vagueSelect(SystemInfo systeminfo);
}
