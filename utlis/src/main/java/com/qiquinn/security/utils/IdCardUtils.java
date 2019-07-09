package com.qiquinn.security.utils;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
  * @Author: QiQuinn
  * @Desicription: 身份证日期检查类
  * @Date:Created in 2019/7/8 11:22
  * @Modified By:
  */
public class IdCardUtils
{
    //省市代码
    private static String provinceCode[] = {"11", "12", "13", "14", "15", "21", "22",
            "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
            "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63",
            "64", "65", "71", "81", "82", "91"};
    //加权英子
    private static int weight[] = {7, 9, 10, 5, 8, 4, 2,
            1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    //校验码
    private static String refNumber[] = {"1", "0", "X", "9", "8",
            "7", "6", "5", "4", "3", "2"};

    /**
      * @Author:QiQuinn
      * @Desicription: 检查身份证日期
      * @Date:Created in 2019/7/8 11:21
      * @param idCardDate 身份证日期
      * @return boolean 身份证日期格式检查结果
      * @Modified By:
      */
    public static boolean checkIdCardDate(String idCardDate)
    {
        StringBuffer idCard = new StringBuffer(idCardDate);
        String year = idCard.substring(0,4);
        String month = idCard.substring(4,6);
        String day = idCard.substring(6,8);
        System.out.println("year: "+year+" month: "+month+" day: "+day);
        return DateUtils.checkMonthAndDay(year,month,day);
    }

    /**
      * @Author QiQuinn
      * @Desicription: 验证身份证号码的结构合法性
      * @Date:Created in 2019/7/8 11:28
      * @param idCard 身份证号码
      * @return boolean 验证结果
      * @Modified By:
      */
    public static boolean checkIdCardPattern(String idCard)
    {
        Pattern pattern = Pattern.compile("^(\\d{6})(18|19|20)?(\\d{2})([01]\\d)([0123]\\d)(\\d{3})(\\d|X|x)?$");
        Matcher matcher = pattern.matcher(idCard);
        return matcher.matches();
    }

    /**
      * @Author:QiQuinn
      * @Desicription: 验证加权验证码(身份证最后一位)
      * @Date:Created in 2019/7/8 11:33
      * @param idCard 身份证号码
      * @return boolean
      * @Modified By:
      */
    public static boolean checkPowerCode(String idCard)
    {
        if(idCard==null || idCard.length()!=18) {
            return false;
        }
        char[] tmp = idCard.toCharArray();
        int[] idcardArray = new int[tmp.length-1];
        for (int i=0;i<tmp.length-1;i++)
        {
            idcardArray[i] = Character.getNumericValue(tmp[i]);
        }
        String check_code = calculatePowerCode(idcardArray);
        String idcard_lastcode = tmp[tmp.length-1] + "";
        if(idcard_lastcode.equals("x"))
        {
            idcard_lastcode = idcard_lastcode.toUpperCase();
        }
        if(!check_code.equals(idcard_lastcode))
        {
            return false;
        }
        return true;
    }

    /**
      * @Author:QiQuinn
      * @Desicription: 计算身份号码加权计算出验证码
      * @Date:Created in 2019/7/8 11:29
      * @param idCardArray 身份证号的数组
      * @return java.lang.String
      * @Modified By:
      */
    public static String calculatePowerCode(int[] idCardArray)
    {
        int result = 0;
        for(int i=0;i<weight.length;i++)
        {
            result += weight[i] * idCardArray[i];
            System.out.print(idCardArray[i]);
        }
        return refNumber[result%11];
    }



    /**
      * @Author:QiQuinn
      * @Desicription: 验证身份证中省份代码是否一致
      * @Date:Created in 2019/7/8 11:31
      * @param provinceId 省份代码
      * @return boolean
      * @Modified By:
      */
    public static boolean isVaildProvinceld(String provinceId)
    {
        if(provinceId==null)
        {
            return false;
        }
        for (String id : provinceCode)
        {
            if(provinceId.equals(id))
            {
                return true;
            }
        }
        return false;
    }
}
