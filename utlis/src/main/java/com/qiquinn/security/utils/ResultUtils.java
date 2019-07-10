package com.qiquinn.security.utils;

import com.qiquinn.security.utils.resultinfo.Result;

import java.util.Map;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/10
 * @Modified By:
 */
public class ResultUtils
{

    public static Map<String,Object> seccuss(Object object)
    {
        Result result = new Result();
        return result.putSuccess(object);
    }

    public static Map<String,Object> seccuss()
    {
        Result result = new Result();
        return result.putSuccess();
    }

    public static Map<String,Object> error(Integer code,String message)
    {
        Result result = new Result();
        return result.putError(code,message);
    }
}
