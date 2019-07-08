package com.qiquinn.security.utils.verdifyparam.customverdify;

import com.qiquinn.security.utils.IdCardUtils;
import com.qiquinn.security.utils.verdifyparam.customeranotation.IdCard;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdCardVelidtor implements ConstraintValidator<IdCard,Object>
{

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if(obj==null)
        {
            return false;
        }
        String id_card = String.valueOf(obj);
         /*先判断是否满足身份证格式,判断省份号是否满足，
         判断日期格式是否满足，判断最后一位是否满足 */
        String provinceId = id_card.substring(0,2);
        String date = id_card.substring(6,14);
        return checkIdCardPattern(id_card,context)
                && checkProvinceld(provinceId,context)
                &&checkIdCardDate(date,context)
                && checkPowerCode(id_card,context);
    }

    /**
      * @Author:QiQuinn
      * @Desicription: 验证身份证格式是否正确
      * @Date:Created in 2019/7/8 11:57
      * @param idCard 身份证号码
      * @param context 错误信息构造类
      *@return boolean
      * @Modified By:
      */
    private boolean checkIdCardPattern(String idCard,ConstraintValidatorContext context)
    {
        return checkResultMsg(IdCardUtils.checkIdCardPattern(idCard),"格式错误！",context);
    }

    /**
      * @Author:QiQuinn
      * @Desicription: 验证身份证日期是否合法
      * @Date:Created in 2019/7/8 14:10
      * @param date 日期
      * @param context 错误构造类
      *@return boolean true通过,false不通过
      * @Modified By:
      */
    private boolean checkIdCardDate(String date,ConstraintValidatorContext context)
    {
        return checkResultMsg(IdCardUtils.checkIdCardDate(date),"日期错误",context);
    }

    /**
      * @Author:QiQuinn
      * @Desicription: 验证码确定
      * @Date:Created in 2019/7/8 12:02
      * @param idCard 身份证号码
      * @param context 错误信息构造类
      *@return boolean
      * @Modified By:
      */

    private boolean checkPowerCode(String idCard,ConstraintValidatorContext context)
    {
        return checkResultMsg(IdCardUtils.checkPowerCode(idCard),"最后一位号码不对",context);
    }

    /**
      * @Author:QiQuinn
      * @Desicription: 省份码判断
      * @Date:Created in 2019/7/8 12:01
      * @param provinceId 省份字段
      * @param context 错误信息构造类
      *@return boolean
      * @Modified By:
      */
    public boolean checkProvinceld(String provinceId,ConstraintValidatorContext context)
    {
        return checkResultMsg(IdCardUtils.isVaildProvinceld(provinceId),"省份码错误",context);
    }

    /**
      * @Author:QiQuinn
      * @Desicription: 处理验证后结果
      * @Date:Created in 2019/7/8 12:03
      * @param check  验证结果
      * @param errorMsg 验证失败的错误信息
      * @param context  错误信息构造类
      *@return boolean
      * @Modified By:
      */
    private static boolean checkResultMsg(boolean check,String errorMsg,ConstraintValidatorContext context)
    {
        if(check)
            return true;
        else
        {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(errorMsg)
                    .addConstraintViolation();
            return false;
        }
    }
}
