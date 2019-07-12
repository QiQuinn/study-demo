package com.qiquinn.security.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qiquinn.security.entity.User;
import com.qiquinn.security.service.UserService;
import com.qiquinn.security.utils.Exceptions.MessageEnum;
import com.qiquinn.security.utils.ResultUtils;
import com.qiquinn.security.utils.redis.RedisUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
                redisUtils.setObject(key,user,60*50);
                return ResultUtils.seccuss(MessageEnum.SUSSECC.getMsg(),key);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return ResultUtils.error(MessageEnum.UNKONW_EROOR.getCode(),
                    MessageEnum.UNKONW_EROOR.getMsg());
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
            ex.printStackTrace();
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
