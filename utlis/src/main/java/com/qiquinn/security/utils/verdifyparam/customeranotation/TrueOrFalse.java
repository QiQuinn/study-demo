package com.qiquinn.security.utils.verdifyparam.customeranotation;

import com.qiquinn.security.utils.verdifyparam.customverdify.IsHanderValidtor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by QiQuinn on 2019/7/5.
 */
@Target(ElementType.FIELD)  //作用于的类型，此处为对象的属性
@Retention(RetentionPolicy.RUNTIME)  //运行时生效
@Constraint(validatedBy = IsHanderValidtor.class) //通过PasswordNotNullValidator类实现注解的相关校验操作
public @interface TrueOrFalse {

    int value() default 0;

    String message() default "IsHander错误"; //必填，校验未通过时的提示信息
    Class<?>[] groups() default {};  //必填，下文会将到此参数的作用
    Class<? extends Payload>[] payload() default {}; //必填

    @Target({ElementType.FIELD,ElementType.METHOD,ElementType.TYPE})
    @interface Hander
    {
        TrueOrFalse[] value();
    }
}
