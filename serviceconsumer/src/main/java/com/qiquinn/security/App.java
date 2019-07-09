package com.qiquinn.security;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@EnableDubboConfig
@DubboComponentScan(basePackages = "com.qiquinn.security.controller")
@EnableAutoConfiguration
@ComponentScan("com.qiquinn.security.aspect,com.qiquinn.security.controller")
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }
}
