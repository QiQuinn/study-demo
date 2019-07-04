package com.qiquinn.security;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableDubbo
@EnableAutoConfiguration
@MapperScan(basePackages = "com.qiquinn.security.dao")
@ComponentScan(basePackages = "com.qiquinn.security")
public class Application
{
    public static void main (String[] args)
    {
        SpringApplication.run(Application.class,args);
        System.out.println("========================");
    }
}
