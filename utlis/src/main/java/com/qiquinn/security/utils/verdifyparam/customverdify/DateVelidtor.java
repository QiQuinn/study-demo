package com.qiquinn.security.utils.verdifyparam.customverdify;

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
        Pattern pattern1 =  Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
        Pattern pattern2 =  Pattern.compile("[0-9]{4}.[0-9]{2}.[0-9]{2}");
        Pattern pattern3 =  Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9]{2}");
        Matcher match = pattern1.matcher(o.toString());
        StringBuffer stringBuffer = new StringBuffer(o.toString());

        if(!match.matches())
        {
            match = pattern2.matcher(o.toString());
        }
        if(!match.matches())
        {
            match = pattern3.matcher(o.toString());
        }
        if(!match.matches())
        {
            return false;
        }
        int year =  Integer.valueOf( stringBuffer.substring(0,4) );
        int month= Integer.valueOf( stringBuffer.substring(6,7) );
        int day  = Integer.valueOf ( stringBuffer.substring(9,10) );

        int moredaymonth[] = {1,3,5,7,8,10,12};
        int mindaymonth[] = {4,6,9,11};
        System.out.println("========== ymd: "+year+""+month+":"+day);
        //判断2月天数
        if (year%4==0)
        {
            if(month==2 && day >29)
            {
                return false;
            }
        }
        else if(month==2 && day >28)
        {
            return false;
        }
        //判断大约天数
        for (int m : moredaymonth)
        {
            if(m==month && day>31)
                return false;
            else
                return true;
        }
        //判断小月天数
        for (int m : mindaymonth)
        {
            if (m==month && day>30)
                return false;
            else
                return true;
        }
        return true;
    }

}
