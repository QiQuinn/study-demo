package com.qiquinn.security.utils;

import com.qiquinn.security.utils.resultinfo.Result;

import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/10
 * @Modified By:
 */
public class ResultSerlizerUtils
{

    public static Map<String,Object> seccuss(Object object)
    {
        Result result = new Result();
        return result.putSuccess(object);
    }

    public static Map<String,Object> loginSeccuss(String msg,String token,Object object)
    {
        Result result = new Result();
        return result.putSuccess(msg,token,object);
    }

    public static Map<String,Object> seccuss(String msg,Object object)
    {
        Result result = new Result();
        return result.putSuccess(msg,object);
    }

    public static Map<String,Object> seccuss(String msg)
    {
        Result result = new Result();
        return result.putSuccess(msg);
    }

    public static Map<String,Object> error(Integer code,String message)
    {
        Result result = new Result();
        return result.putError(code,message);
    }

    public static Map<String,Object> error(Integer code,String message,Exception ex)
    {
        Result result = new Result();
        return result.putErrorExption(code, message, ex);
    }
}
