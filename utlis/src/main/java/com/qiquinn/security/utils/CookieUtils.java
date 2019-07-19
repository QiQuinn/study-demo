package com.qiquinn.security.utils;

import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * @Author:QiQuinn
 * @Desicription: cookie操作类
 * @Date:Created in 2019/7/12
 * @Modified By:
 */
public class CookieUtils
{
    /**
      * @Author:QiQuinn
      * @Desicription: 获取cookie的方法
      * @Date:Created in 2019/7/12 17:32
      * @param key 键值
      * @param request
      *@return java.lang.String
      * @Modified By:
      */
    public static String getCookieValue(String key, HttpServletRequest request)
    {
        String tokenValue = "";
        Cookie[] cookies = request.getCookies();
        if(null==cookies || cookies.length<0)
        {
            return null;
        }

        for(Cookie cookie : cookies)
        {
            String name = cookie.getName();
            if(!StringUtils.isEmpty(name)&&key.equals(name))
            {
                tokenValue = cookie.getValue();
            }
        }
        return tokenValue;
    }

    /**
      * @Author:QiQuinn
      * @Desicription: 获取cookie
      * @Date:Created in 2019/7/19 10:59
      * @param key Cookie名字
    	 * @param request  请求
      *@return javax.servlet.http.Cookie
      * @Modified By:
      */
    public static Cookie getCookie(String key,HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        if(null==cookies || cookies.length<0)
        {
            return null;
        }
        else
        {
            for(Cookie cookie: cookies)
            {
                if(!cookie.getName().isEmpty() && cookie.getName().equals(key))
                {
                    return cookie;
                }
            }
        }
        return null;
    }
}
