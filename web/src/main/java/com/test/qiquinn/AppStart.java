package com.test.qiquinn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/11
 * @Modified By:
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.test.qiquinn")
public class AppStart
{
    public static void main(String[] args)
    {
        SpringApplication.run(AppStart.class,args);
        System.out.println("============== APPStart ================");
    }
}
