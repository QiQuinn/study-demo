package com.qiquinn.security.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qiquinn.security.entity.ColumnInfo;
import com.qiquinn.security.entity.User;
import com.qiquinn.security.service.UserService;
import com.qiquinn.security.utils.stringenum.MessageEnum;
import com.qiquinn.security.utils.ResultSerlizerUtils;
import com.qiquinn.security.utils.redis.RedisNormerUtils;
import com.qiquinn.security.utils.redis.RedisSerializeUtils;
import com.qiquinn.security.utils.stringenum.RedisSaveStringConfig;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:QiQuinn
 * @Desicription: 用户操作类
 * @Date:Created in 2019/7/11
 * @Modified By:
 */
@RestController
@RequestMapping("/user")
public class UserController
{
    @Reference
    private UserService userService;
    @Autowired
    private RedisSerializeUtils redisSerializeUtils;
    @Autowired
    private RedisNormerUtils redisUtils;


    @RequestMapping("/serliaze")
    public String fsave()
    {
        long start = System.currentTimeMillis();
        List<ColumnInfo> list = new ArrayList<>();
        for(int i=0;i<1000000;i++)
        {
            ColumnInfo c = new ColumnInfo();
            c.setId(1);
            c.setName("S"+1);
            c.setParentId(2);
            list.add(c);
        }
//        byte[] bytes = JRedisSerializeUtils.kryoSerizlize(list);
        redisSerializeUtils.setObject(RedisSaveStringConfig.COLUMN_FLIST,list,30);
        return "系统存储耗时: "+ (System.currentTimeMillis()-start);
    }

    @RequestMapping("/getflist")
    public String getFlist()
    {
        long start = System.currentTimeMillis();
//        byte[] bytes = redisUtils.getObjectMySerlizable("flist");
        Object obj = redisSerializeUtils.getObject(RedisSaveStringConfig.COLUMN_FLIST);
        List<ColumnInfo> list = (List<ColumnInfo>) obj;
        return "系统读取耗时: "+(System.currentTimeMillis()-start);
    }

    @RequestMapping("/save")
    public String save()
    {
        long start = System.currentTimeMillis();
        List<ColumnInfo> list = new ArrayList<>();
        for(int i=0;i<1000000;i++)
        {
            ColumnInfo c = new ColumnInfo();
            c.setId(i);
            c.setName("S"+i);
            c.setParentId(i);
            list.add(c);
        }
        redisUtils.leftPush(RedisSaveStringConfig.COLUMN_LIST,list);
        return "系统存储耗时: "+ (System.currentTimeMillis()-start);
    }

    @RequestMapping("/getlist")
    public String getlist()
    {
        long start = System.currentTimeMillis();
        List<Object> list = redisUtils.range(RedisSaveStringConfig.COLUMN_LIST,0,-1);
        return "系统读取耗时: "+(System.currentTimeMillis()-start);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(@RequestBody Map<String,Object> params)
    {
        try
        {
            String sign = "qiquinn";
            String username = String.valueOf( params.get("username") );
            String password = String.valueOf( params.get("password") );
            User user = userService.findUserByUserName(username.trim());
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
                redisUtils.set(key, user,RedisSaveStringConfig.LOGIN_ACTIVE_TIME);
                Object obj = redisUtils.get(key);
                User resultUser = (User) obj;
                System.out.println("serliza: "+ resultUser);
                return ResultSerlizerUtils.loginSeccuss(MessageEnum.SUSSECC.getMsg(),key,user);
            }
        }
        catch (Exception ex)
        {
            return ResultSerlizerUtils.error(MessageEnum.UNKONW_EROOR.getCode(),
                    MessageEnum.UNKONW_EROOR.getMsg(),ex);
        }
    }

    @PostMapping(value = "auth")
    public Map<String,Object> auth(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            String token = request.getParameter("token");
            Object userObject = redisUtils.get(token);
            if(userObject==null)
            {
                return ResultSerlizerUtils.error(MessageEnum.ERROR_AUTHORIZATION_NULL.getCode(), MessageEnum.ERROR_AUTHORIZATION_NULL.getMsg());
            }
            else
            {
                User user = (User)userObject;
//                更新redis
                redisUtils.set(token,user,3000);
                return ResultSerlizerUtils.seccuss("授权成功!");
            }
        }
        catch (Exception ex)
        {
            return ResultSerlizerUtils.error(MessageEnum.ERROR_AUTHORIZATION.getCode(),
                    MessageEnum.ERROR_AUTHORIZATION.getMsg());
        }
    }

    @PostMapping(value = "/regist")
    public Map<String,Object> regist(@RequestBody @Validated User user)
    {
        if(user == null)
            return ResultSerlizerUtils.error(MessageEnum.UNKONW_EROOR.getCode(), MessageEnum.UNKONW_EROOR.getMsg());
        try
        {
//            将密码变成md5存入?
            userService.insert(user);
            return ResultSerlizerUtils.seccuss(MessageEnum.SUSSECC.getMsg());
        }
        catch (Exception ex)
        {
            return ResultSerlizerUtils.error(MessageEnum.EROOR_USER_REGIST.getCode(),
                    MessageEnum.EROOR_USER_REGIST.getMsg(),ex);
        }
    }
}
