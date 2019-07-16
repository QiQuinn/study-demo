package com.test.qiquinn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiquinn.security.utils.HttpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/11
 * @Modified By:
 */
@RestController
@RequestMapping("/web")
public class TestController
{
    @GetMapping("login")
    public String webUser(HttpServletRequest request, HttpServletResponse response)
    {
        Map<String,String> params = new HashMap<String,String>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(null==username || null==password)
            return "null";
        params.put("username",username);
        params.put("password",password);
        try
        {
            String result = HttpUtils.send("http://192.168.96.1:8881/user/login",params,"utf-8");
            System.out.println("web1登陆返回的数据: "+result);
            JSONObject jsonObject = JSON.parseObject(result);
            if(jsonObject.getInteger("code")==0)
            {
                /* 登陆成功 */
                Cookie clientCookie = new Cookie("token",jsonObject.getString("data"));
                clientCookie.setMaxAge(60);
                clientCookie.setPath("/");
                clientCookie.setDomain("localhost");
                response.addCookie(clientCookie);
                return jsonObject.getInteger("code")+"";
            }
            return result;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return "error: null";
        }

    }
}
