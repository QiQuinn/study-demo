package com.qiquinn.security.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by QiQuinn on 2019/7/8.
 * 验证日期格式是否正确
 *
 */
public class DateUtils
{
    /**
     * Create by QiQuinn
     * 完全验证日期
     * @param date 参数
     * @return 是否满足要求
     */
    public static boolean isVerdifyDate(String date) {
        Pattern pattern1 = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
        Pattern pattern2 = Pattern.compile("[0-9]{4}.[0-9]{2}.[0-9]{2}");
        Pattern pattern3 = Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9]{2}");
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(date));
        Matcher match = pattern1.matcher(stringBuffer);

        if (!match.matches()) {
            match = pattern2.matcher(stringBuffer);
        }
        if (!match.matches()) {
            match = pattern3.matcher(stringBuffer);
        }
        if (!match.matches()) {
            return false;
        }
        String year = stringBuffer.substring(0, 4);
        String month = stringBuffer.substring(6, 7);
        String day = stringBuffer.substring(9, 10);

        return checkMonthAndDay(year,month,day);

    }

    /**
      * @Author:QiQuinn
      * @Desicription: 验证年月日是否满足规则
      * @Date:Created in 2019/7/9 14:49
      * @param strYear 年份
    	 * @param strMonth 月份
    	 * @param strDay  天数
      *@return boolean 是否返祖
      * @Modified By:
      */

    public static boolean checkMonthAndDay(String strYear,String strMonth,String strDay)
    {
        int month = Integer.valueOf(strMonth);
        int day = Integer.valueOf(strDay);
        //判断2月天数
        if(month==2 && isLeapYear(strYear))
        {
            if(day>29)
            {
                return false;
            }
        }
        else if(month==2 && day>28)
        {
            return false;
        }
        int moredaymonth[] = {1, 3, 5, 7, 8, 10, 12};
        int mindaymonth[] = {4, 6, 9, 11};
        //判断大约天数
        for (int m : moredaymonth) {
            if (m == month && day > 31)
                return false;
            else
                return true;
        }
        //判断小月天数
        for (int m : mindaymonth) {
            if (m == month && day > 30)
                return false;
            else
                return true;
        }
        return true;
    }

    /**
      * @Author:QiQuinn
      * @Desicription: 是否是闰年
      * @Date:Created in 2019/7/9 14:50
      * @param year  年份
      *@return boolean true-闰年  false-平年
      * @Modified By:
      */
    public static boolean isLeapYear(String year)
    {
        if(year==null)
        {
            return false;
        }
        else
        {
            int year_int = Integer.valueOf(year);
            if(year_int%4==0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
