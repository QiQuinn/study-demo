package com.qiquinn.security.service.basic;

import java.util.List;

public interface BasicService<T>
{
    //增加
    void insert(T t);
    //删除
    void delete(T t);
    //修改
    void update(T t);
    //查询
    List<T> select();

    T findById(Integer id);
}
