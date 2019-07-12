package com.qiquinn.security.utils;

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
    public static String getCookies(String key, HttpServletRequest request)
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
}
