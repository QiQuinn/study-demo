package com.qiquinn.security.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qiquinn.security.entity.ColumnInfo;
import com.qiquinn.security.entity.User;
import com.qiquinn.security.service.UserService;
import com.qiquinn.security.utils.CookieUtils;
import com.qiquinn.security.utils.stringenum.MessageEnum;
import com.qiquinn.security.utils.ResultSerlizerUtils;
import com.qiquinn.security.utils.redis.RedisNormerUtils;
import com.qiquinn.security.utils.redis.RedisSerializeUtils;
import com.qiquinn.security.utils.stringenum.RedisSaveStringConfig;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
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
        if(params!=null || params.isEmpty())
        {
            String username = String.valueOf( params.get("username") );
            String password = String.valueOf( params.get("password") );
            if(username!=null && password!=null )
            {
                return userService.login(username,password);
            }
            else
            {
                return ResultSerlizerUtils.error(MessageEnum.UNKONW_EROOR.getCode(),
                        MessageEnum.UNKONW_EROOR.getMsg());
            }
        }
        else
        {
            return ResultSerlizerUtils.error(MessageEnum.ERROR_USER_LOGINPARMAS_NOTNULL.getCode(),
                    MessageEnum.ERROR_USER_LOGINPARMAS_NOTNULL.getMsg());
        }
    }

    @PostMapping(value = "/auth")
    @ResponseBody
    public Map<String,Object> auth(@RequestBody Map<String,Object> params)
    {
        System.out.println("auth: "+params);
        if(params==null || params.get(RedisSaveStringConfig.USER_TOKEN)==null )
            return ResultSerlizerUtils.error(MessageEnum.ERROR_AUTHORIZATION_NULL.getCode(),
                    MessageEnum.ERROR_AUTHORIZATION_NULL.getMsg());
        return userService.auth(params);
    }

    @PostMapping(value = "/regist")
    public Map<String,Object> regist(@RequestBody @Validated User user)
    {
        return userService.regist(user);
    }

    @RequestMapping(value = "/updatauserinfo",method = RequestMethod.PUT)
    public Map<String,Object> updataUserInfo(User user)
    {
        return null;
    }
}
