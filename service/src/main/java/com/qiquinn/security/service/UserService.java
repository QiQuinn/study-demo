package com.qiquinn.security.service;

import com.qiquinn.security.entity.User;
import com.qiquinn.security.service.basic.BasicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Author:QiQuinn
 * @Desicription: 用户登录服务接口
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

    Map<String,Object> login(String username, String password);

    User findUserByUserName(String username);

    Map<String,Object> auth(Map<String,Object> params);

    Map<String,Object> regist(User user);

    Map<String,Object> updataUserInfomation(User user);
}
