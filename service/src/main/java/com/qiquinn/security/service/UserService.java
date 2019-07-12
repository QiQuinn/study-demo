package com.qiquinn.security.service;

import com.qiquinn.security.entity.User;
import com.qiquinn.security.service.basic.BasicService;

import java.util.List;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/11
 * @Modified By:
 */

public interface UserService extends BasicService<User>
{
    @Override
    void insert(User user);

    @Override
    void delete(User user);

    @Override
    void update(User user);

    @Override
    List<User> select();

    @Override
    User findById(Integer id);

    User login(String username,String password);

    User findUserByUserName(String username);
}
