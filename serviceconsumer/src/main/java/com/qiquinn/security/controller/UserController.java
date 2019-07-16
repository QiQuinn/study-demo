package com.qiquinn.security.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qiquinn.security.entity.ColumnInfo;
import com.qiquinn.security.entity.User;
import com.qiquinn.security.service.UserService;
import com.qiquinn.security.utils.Exceptions.MessageEnum;
import com.qiquinn.security.utils.ResultUtils;
import com.qiquinn.security.utils.SerializeUtils;
import com.qiquinn.security.utils.redis.RedisUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.plugin2.message.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.ArrayList;
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
    private RedisUtils redisUtils ;


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
        redisUtils.lSet("list",list);
        return "系统存储耗时: "+ (System.currentTimeMillis()-start);
    }
    @RequestMapping("/csave")
    public String cSave()
    {
        long start = System.currentTimeMillis();
        List<ColumnInfo> list = new ArrayList<>();
        for(int i=0;i<1000000;i++)
        {
            ColumnInfo c = new ColumnInfo();
            c.setId(i);
            c.setName("C"+i);
            c.setParentId(i);
            list.add(c);
        }
        byte[] bytes = SerializeUtils.serialize(list);
        redisUtils.setObjcetMySerlizable("clist",bytes,11111);
        return "系统存储耗时: "+ (System.currentTimeMillis()-start);
    }

    @RequestMapping("/getlist")
    public String getlist()
    {
        long start = System.currentTimeMillis();
        List<Object> list = redisUtils.lGet("list",0,-1);
        System.out.println(list.toString());
        return "系统读取耗时: "+(System.currentTimeMillis()-start);
    }
    @RequestMapping("/getclist")
    public String getcList()
    {
        long start = System.currentTimeMillis();
        Object obj = redisUtils.getObjectMySerlizable("clist");
        System.out.println(obj+"");
        return "系统读取耗时: "+(System.currentTimeMillis()-start);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(@RequestBody Map<String,Object> params)
    {
        System.out.println("========================= Consumer:Login ==========================");
        try
        {
            String sign = "qiquinn";
            String username = String.valueOf( params.get("username") );
            String password = String.valueOf( params.get("password") );
            User user = userService.findUserByUserName(username.trim());
            if(user == null )
                return ResultUtils.error(MessageEnum.ERROR_USER_NULL.getCode(),
                        MessageEnum.ERROR_USER_NULL.getMsg());
            if(!user.getPassword().equals(password.trim()))
                return ResultUtils.error(MessageEnum.ERROR_USER_PASSWORD_WRONG.getCode(),
                        MessageEnum.ERROR_USER_PASSWORD_WRONG.getMsg());
            else
            {
                String key = DigestUtils.md5Hex(username+password+sign);
                user.setPassword("");
                redisUtils.setObject(key, user,2222);
                Object obj = redisUtils.getObject(key);
                User resultUser = (User) obj;
                System.out.println("serliza: "+ resultUser);
                return ResultUtils.seccuss(MessageEnum.SUSSECC.getMsg(),key);
            }
        }
        catch (Exception ex)
        {
            return ResultUtils.error(MessageEnum.UNKONW_EROOR.getCode(),
                    MessageEnum.UNKONW_EROOR.getMsg(),ex);
        }
    }

    @PostMapping(value = "auth")
    public Map<String,Object> auth(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            String token = request.getParameter("token");
            Object userObject = redisUtils.getObject(token);
            if(userObject==null)
            {
                return ResultUtils.error(MessageEnum.ERROR_AUTHORIZATION_NULL.getCode(), MessageEnum.ERROR_AUTHORIZATION_NULL.getMsg());
            }
            else
            {
                User user = (User)userObject;
                //更新redis
                redisUtils.setObject(token,user,3000);
                return ResultUtils.seccuss("授权成功!");
            }
        }
        catch (Exception ex)
        {
            return ResultUtils.error(MessageEnum.ERROR_AUTHORIZATION.getCode(),
                    MessageEnum.ERROR_AUTHORIZATION.getMsg());
        }
    }

    @PostMapping(value = "/regist")
    public Map<String,Object> regist(@RequestBody @Validated User user)
    {
        if(user == null)
            return ResultUtils.error(MessageEnum.UNKONW_EROOR.getCode(), MessageEnum.UNKONW_EROOR.getMsg());
        userService.insert(user);
        System.out.println("注册充公！");
        return ResultUtils.seccuss(MessageEnum.SUSSECC.getMsg());
    }
}
