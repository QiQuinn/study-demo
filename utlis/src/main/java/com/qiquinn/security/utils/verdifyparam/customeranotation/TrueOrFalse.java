package com.qiquinn.security.utils.verdifyparam.customeranotation;

import com.qiquinn.security.utils.verdifyparam.customverdify.IsHanderValidtor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
  * @Author:QiQuinn
  * @Desicription: 验证是否设置为首页标题的参数合法性
  * @Date:Created in 2019/7/8 15:14
  * @Modified By:
  */

@Target(ElementType.FIELD)  //作用于的类型，此处为对象的属性s
@Retention(RetentionPolicy.RUNTIME)  //运行时生效
@Constraint(validatedBy = IsHanderValidtor.class) //通过PasswordNotNullValidator类实现注解的相关校验操作
public @interface TrueOrFalse {

    String message() default "IsHander错误"; //必填，校验未通过时的提示信息

    Class<?>[] groups() default {};  //必填，下文会将到此参数的作用
    Class<? extends Payload>[] payload() default {}; //必填

}
