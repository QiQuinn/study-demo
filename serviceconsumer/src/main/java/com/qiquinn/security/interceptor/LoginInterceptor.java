package com.qiquinn.security.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiquinn.security.utils.CookieUtils;
import com.qiquinn.security.utils.Exceptions.CustomerExpection;
import com.qiquinn.security.utils.stringenum.MessageEnum;
import com.qiquinn.security.utils.HttpUtils;
import com.qiquinn.security.utils.stringenum.RedisSaveStringConfig;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/12
 * @Modified By:
 */
public class LoginInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String urlString = request.getRequestURL().toString();
        String method = request.getMethod();

        if((urlString.endsWith("login") || urlString.endsWith("regist") || urlString.endsWith("auth"))
                && "POST".equals(method))
        {
            return true;
        }
        else
        {
            /* 如果不是登陆方法 那么就去sso服务器验证是否登陆通过 */
            Cookie token_cookie = CookieUtils.getCookie(RedisSaveStringConfig.USER_TOKEN,request);
            System.out.println("参数: "+request.getRequestURI()+"带过来的cookie: "+token_cookie);
            System.out.println("====拦截器====="+request.getMethod());
            if(token_cookie!=null)
            {
                Map<String,Object> map = new HashMap<>();
                map.put(token_cookie.getName(),token_cookie.getValue());
                String result = HttpUtils.send("http://192.168.96.1:8881/user/auth",map,"utf-8");
                JSONObject jsonObject = JSON.parseObject(result);
                if(jsonObject.getInteger("code")==0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                throw new CustomerExpection(MessageEnum.ERROR_AUTHORIZATION_NULL.getCode(),
                        MessageEnum.ERROR_AUTHORIZATION_NULL.getMsg(),"preHandle",this);
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
