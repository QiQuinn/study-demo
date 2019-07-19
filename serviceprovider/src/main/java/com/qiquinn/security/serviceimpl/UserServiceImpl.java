package com.qiquinn.security.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qiquinn.security.dao.UserDao;
import com.qiquinn.security.entity.User;
import com.qiquinn.security.service.UserService;
import com.qiquinn.security.utils.CookieUtils;
import com.qiquinn.security.utils.ResultSerlizerUtils;
import com.qiquinn.security.utils.redis.RedisNormerUtils;
import com.qiquinn.security.utils.redis.RedisSerializeUtils;
import com.qiquinn.security.utils.stringenum.MessageEnum;
import com.qiquinn.security.utils.stringenum.RedisSaveStringConfig;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private RedisNormerUtils redisUtils;
    @Autowired
    private RedisSerializeUtils redisSerializeUtils;

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
    public Map<String,Object> login(String username, String password)
    {
        try
        {
            String sign = "qiquinn";
            User user = findUserByUserName(username.trim());
            if(user == null )
                return ResultSerlizerUtils.error(MessageEnum.ERROR_USER_NULL.getCode(),
                        MessageEnum.ERROR_USER_NULL.getMsg());
            if(!user.getPassword().equals(password.trim()))
                return ResultSerlizerUtils.error(MessageEnum.ERROR_USER_PASSWORD_WRONG.getCode(),
                        MessageEnum.ERROR_USER_PASSWORD_WRONG.getMsg());
            else
            {
                String key = DigestUtils.md5Hex(username+password+sign);
                user.setPassword("");
                redisUtils.set(key, user, RedisSaveStringConfig.LOGIN_ACTIVE_TIME);
                Object obj = redisUtils.get(key);
                User resultUser = (User) obj;
                return ResultSerlizerUtils.loginSeccuss(MessageEnum.SUSSECC.getMsg(),key,user);
            }
        }
        catch (Exception ex)
        {
            return ResultSerlizerUtils.error(MessageEnum.UNKONW_EROOR.getCode(),
                    MessageEnum.UNKONW_EROOR.getMsg(),ex);
        }
    }

    @Override
    public User findUserByUserName(String username) {
        return userDao.findUserByUserName(username);
    }

    @Override
    public Map<String, Object> auth(Map<String,Object> params) {
        try
        {
            if(params==null)
                return ResultSerlizerUtils.error(MessageEnum.ERROR_AUTH_PARAMSNULL.getCode(),
                        MessageEnum.ERROR_AUTH_PARAMSNULL.getMsg());
            String token = String.valueOf(params.get(RedisSaveStringConfig.USER_TOKEN));
            Object userObject = redisUtils.get(token);
            if(userObject==null)
            {
                return ResultSerlizerUtils.error(MessageEnum.ERROR_AUTHORIZATION_NULL.getCode(),
                        MessageEnum.ERROR_AUTHORIZATION_NULL.getMsg());
            }
            else
            {
                User user = (User)userObject;
                redisUtils.set(token,user,RedisSaveStringConfig.LOGIN_ACTIVE_TIME);
                return ResultSerlizerUtils.seccuss("授权成功!");
            }
        }
        catch (Exception ex)
        {
            return ResultSerlizerUtils.error(MessageEnum.ERROR_AUTHORIZATION.getCode(),
                    MessageEnum.ERROR_AUTHORIZATION.getMsg(),ex);
        }
    }

    @Override
    public Map<String, Object> regist(User user)
    {
        if(user == null)
            return ResultSerlizerUtils.error(MessageEnum.UNKONW_EROOR.getCode(), MessageEnum.UNKONW_EROOR.getMsg());
        try
        {
            //判断数据库中是否有
            if(findUserByUserName(user.getUsername())!=null)
            {
                return ResultSerlizerUtils.error(MessageEnum.ERROR_USER_NAMEISEXIST.getCode(),
                        MessageEnum.ERROR_USER_NAMEISEXIST.getMsg());
            }
            else
            {
                insert(user);
                return ResultSerlizerUtils.seccuss(MessageEnum.SUSSECC.getMsg());
            }
        }
        catch (Exception ex)
        {
            return ResultSerlizerUtils.error(MessageEnum.EROOR_USER_REGIST.getCode(),
                    MessageEnum.EROOR_USER_REGIST.getMsg(),ex);
        }
    }

    @Override
    public Map<String, Object> updataUserInfomation(User user) {
        return null;
    }
}
