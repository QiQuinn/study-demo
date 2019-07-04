package com.qiquinn.security.dao;

import java.util.List;

import com.qiquinn.security.dao.basic.BaseDao;
import com.qiquinn.security.entity.ColumnInfo;

public interface ColumnInfoDao extends BaseDao<ColumnInfo>
{
    //增加
    public void insert(ColumnInfo columninfo);
    //删除
    public void delete(ColumnInfo columninfo);
    //修改
    public void update(ColumnInfo columninfo);
    //查询
    public List<ColumnInfo> select();

    ColumnInfo findById(Integer id);
}