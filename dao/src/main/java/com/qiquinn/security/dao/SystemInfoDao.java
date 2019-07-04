package com.qiquinn.security.dao;

import com.qiquinn.security.entity.SystemInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemInfoDao
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
