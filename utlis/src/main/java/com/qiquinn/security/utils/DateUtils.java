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
        int year = Integer.valueOf(stringBuffer.substring(0, 4));
        int month = Integer.valueOf(stringBuffer.substring(6, 7));
        int day = Integer.valueOf(stringBuffer.substring(9, 10));

        int moredaymonth[] = {1, 3, 5, 7, 8, 10, 12};
        int mindaymonth[] = {4, 6, 9, 11};
        System.out.println("========== ymd: " + year + "" + month + ":" + day);
        //判断2月天数 先判断闰年然后再判断普通年份
        if (year % 4 == 0) {
            if (month == 2 && day > 29) {
                return false;
            }
        } else if (month == 2 && day > 28) {
            return false;
        }
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
}
