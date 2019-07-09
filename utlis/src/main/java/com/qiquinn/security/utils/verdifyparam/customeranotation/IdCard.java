package com.qiquinn.security.utils.verdifyparam.customeranotation;

import com.qiquinn.security.utils.verdifyparam.customverdify.IdCardVelidtor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by QiQuinn on 2019/7/8.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdCardVelidtor.class)
public @interface IdCard
{
    String message() default "IdCard msg.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
