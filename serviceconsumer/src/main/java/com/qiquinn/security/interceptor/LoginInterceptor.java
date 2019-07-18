package com.qiquinn.security.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiquinn.security.utils.CookieUtils;
import com.qiquinn.security.utils.Exceptions.CustomerExpection;
import com.qiquinn.security.utils.stringenum.MessageEnum;
import com.qiquinn.security.utils.HttpUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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
        System.out.println("======== 拦截器 ============");
        String urlString = request.getRequestURL().toString();
        String contextPath = request.getContextPath();
        String method = request.getMethod();
        System.out.println("contextPath: "+contextPath);
        System.out.println("url: "+urlString);
        if(urlString.endsWith("login")&&"POST".equals(method))
        {
            return true;
        }
        else
        {
            /* 如果不是登陆方法 那么就去sso服务器验证是否登陆通过 */
            String tokenValue = CookieUtils.getCookies("token",request);
            if(StringUtils.isEmpty(tokenValue))
            {
                throw new CustomerExpection(MessageEnum.ERROR_AUTHORIZATION_NULL.getCode(),
                        MessageEnum.ERROR_AUTHORIZATION_NULL.getMsg(),"preHandle",this);
            }
            Map<String,String> params = new HashMap<String,String>();
            params.put("token",tokenValue);
            String result = HttpUtils.send("localhost:8881/user/auth",params,"utf-8");
            JSONObject jsonObject = JSON.parseObject(result);
            if(jsonObject.getInteger("code")==0)
            {
                System.out.println("================== 用户登陆授权成功 ==================");
                return true;
            }
            else
            {
                System.out.println("=================== 用户授权失败 ======================");
                return false;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("=============== postHandle ================");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("=============== afterCompletion ================");
    }
}
