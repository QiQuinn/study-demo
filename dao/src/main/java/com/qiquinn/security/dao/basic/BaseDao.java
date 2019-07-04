package com.qiquinn.security.dao.basic;

import java.util.List;

public interface BaseDao<T>
{
    void deleteData(T t);
    void updataData(T t);
    List<T> findAllData();
    void insertData(T t);
    T findById(Integer id);
}
