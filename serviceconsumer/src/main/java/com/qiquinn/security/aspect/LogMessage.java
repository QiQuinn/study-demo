package com.qiquinn.security.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
    public void log()
    {
        System.out.println("aspect running!");
    }
}
