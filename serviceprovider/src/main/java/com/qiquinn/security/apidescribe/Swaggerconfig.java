package com.qiquinn.security.apidescribe;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.not;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.withMethodAnnotation;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "com.qiquinn.security")
public class Swaggerconfig {

    @Bean
    public Docket SwagApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(swaggerApiInfo())
                .select()
                .apis(not(withMethodAnnotation(SwaggerIgnor.class)))
                .paths(allowPaths())
                .build();
    }

    private ApiInfo swaggerApiInfo() {
        //构建联系实体，在UI界面会显示
//        Contact contact = new Contact("测试项目");
        return new ApiInfoBuilder()
//                .contact(contact)
                //文档标题
                .title("Swagger2构建RESTful API文档")
                //文档描述
                .description("SpringBoot集成Springbox开源项目，实现OAS，构建成RESTful API文档")
                //文档版本
                .version("1.0.0")
                .build();
    }

    private Predicate<String> allowPaths(){
        return or(
                regex("/user.*"),
                regex("/role.*")
        );
    }
}
