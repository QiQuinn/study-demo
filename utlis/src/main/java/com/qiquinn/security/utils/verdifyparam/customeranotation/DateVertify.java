package com.qiquinn.security.utils.verdifyparam.customeranotation;

import com.qiquinn.security.utils.verdifyparam.customverdify.DateVelidtor;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by QiQuinn on 2019/7/5.
 * 日期验证注释。
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateVelidtor.class)
public @interface DateVertify
{
    String message() default "DateVertify";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
