package com.qiquinn.security.utils.verdifyparam.customverdify;

import com.qiquinn.security.utils.verdifyparam.customeranotation.TrueOrFalse;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by QiQuinn on 2019/7/5.
 * 消息验证：1 0 验证实现类
 */
public class IsHanderValidtor implements ConstraintValidator<TrueOrFalse,Object>
{
    private int value;
    private String message;


    @Override
    public void initialize(TrueOrFalse constraintAnnotation) {
        this.value = constraintAnnotation.value();
        message = constraintAnnotation.message();
        System.out.println("============ message : "+message);
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        if(obj instanceof Integer)
        {
            Integer status = (Integer)obj;
            if( status == 1 || status ==0 )
            {
                return true;
            }
        }
        return false;
    }
}
