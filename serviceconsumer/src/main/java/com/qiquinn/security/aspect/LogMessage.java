package com.qiquinn.security.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:QiQuinn
 * @Desicription: 日志记录
 * @Date:Created in 2019/7/9
 * @Modified By:
 */
@Aspect
@Component
public class LogMessage
{
    private final static Logger logger = LoggerFactory.getLogger(LogMessage.class);

    //注意方法必须是public否则扫描不到方法会报空指针
    @Pointcut("execution(public * com.qiquinn.security.controller.*.*(..))")
    public void LogMessage(){}

    @Before("LogMessage()")
    public void before(JoinPoint joinPoint)
    {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        logger.info("request: url={}",request.getRequestURL());

        logger.info("request: method={}",request.getMethod());

        logger.info("request: ip={}",request.getRemoteAddr());

        logger.info("request: class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());

        logger.info("request: args={}",joinPoint.getArgs());
    }

    @After("LogMessage()")
    public void after()
    {

    }

    @AfterReturning(returning = "object",pointcut = "LogMessage()")
    public void around(Object object)
    {
        if(object==null)
            return ;
        logger.info("response={}",object.toString());
    }
}
