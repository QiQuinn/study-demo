package com.qiquinn.security.utils.verdifyparam.customeranotation;

import com.qiquinn.security.utils.verdifyparam.customverdify.HanderVelidtor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HanderVelidtor.class)
public @interface IsHander {
    String message() default "ColumIdVerdify";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
