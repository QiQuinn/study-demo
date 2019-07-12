package com.qiquinn.security.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/12
 * @Modified By:
 */
@Configuration
public class InterceptorConfig  implements WebMvcConfigurer
{
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
    }
}
