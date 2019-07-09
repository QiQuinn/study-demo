package com.qiquinn.security.utils.verdifyparam.customverdify;

import com.qiquinn.security.utils.DateUtils;
import com.qiquinn.security.utils.verdifyparam.customeranotation.DateVertify;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2019/7/5.
 * 消息验证：日期注解功能实现类。
 */
public class DateVelidtor implements ConstraintValidator<DateVertify,Object>
{
    @Override
    public void initialize(DateVertify constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return dateComplimentVerdify(o,constraintValidatorContext);
    }

    /**
     * Create by QiQuinn
     * 完全验证日期
     * @param obj 参数
     * @return 是否满足要求
     */
    private static boolean dateComplimentVerdify(Object obj,ConstraintValidatorContext constraint)
    {
        boolean check = DateUtils.isVerdifyDate(String.valueOf(obj));
       if(check)
       {
           return true;
       }
       else
       {
           constraint.disableDefaultConstraintViolation();
           constraint.buildConstraintViolationWithTemplate("日期格式验证错误").addConstraintViolation();
           return false;
       }
    }


}
