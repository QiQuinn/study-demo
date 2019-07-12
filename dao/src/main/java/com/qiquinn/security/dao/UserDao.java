package com.qiquinn.security.dao;

import com.qiquinn.security.dao.basic.BaseDao;
import com.qiquinn.security.entity.User;

import java.util.List;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/11
 * @Modified By:
 */
public interface UserDao extends BaseDao<User>
{
    void deleteData(User user);

    @Override
    void updataData(User user);

    @Override
    List<User> findAllData();

    @Override
    void insertData(User user);

    @Override
    User findById(Integer id);

    User login(String username,String password);

    User findUserByUserName(String username);
}
