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

    @Override
    public void initialize(TrueOrFalse constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("obj is int: "+(obj instanceof String)
        +"   ：  "+ obj);
        if(obj instanceof Integer)
        {
            Integer status = (Integer)obj;
            System.out.println(status);
            if( status == 1 || status ==0 )
            {
                return true;
            }
        }
        return false;
    }
}
