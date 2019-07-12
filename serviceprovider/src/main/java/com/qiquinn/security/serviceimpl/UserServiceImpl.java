package com.qiquinn.security.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qiquinn.security.dao.UserDao;
import com.qiquinn.security.entity.User;
import com.qiquinn.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/11
 * @Modified By:
 */
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDao userDao;
    @Override
    public void insert(User user) {
        userDao.insertData(user);
    }

    @Override
    public void delete(User user) {
        userDao.deleteData(user);
    }

    @Override
    public void update(User user) {
        userDao.updataData(user);
    }

    @Override
    public List<User> select() {
        return userDao.findAllData();
    }

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public User login(String username, String password) {
        return userDao.login(username, password);
    }

    @Override
    public User findUserByUserName(String username) {
        return userDao.findUserByUserName(username);
    }
}
