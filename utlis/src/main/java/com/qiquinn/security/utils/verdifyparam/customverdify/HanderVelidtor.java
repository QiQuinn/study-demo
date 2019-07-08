package com.qiquinn.security.utils.verdifyparam.customverdify;

import com.qiquinn.security.utils.verdifyparam.customeranotation.IsHander;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HanderVelidtor implements ConstraintValidator<IsHander,Object> {
    @Override
    public void initialize(IsHander constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if(o instanceof Integer)
        {
            int is_hander = (Integer)o;
            if(is_hander==0||is_hander==1)
                return true;
        }
        return false;
    }
}
