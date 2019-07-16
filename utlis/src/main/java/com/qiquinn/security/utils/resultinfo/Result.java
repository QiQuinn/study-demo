package com.qiquinn.security.utils.resultinfo;

import com.qiquinn.security.utils.Exceptions.MessageEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:QiQuinn
 * @Desicription: 返回信息处理类
 * @Date:Created in 2019/7/10
 * @Modified By:
 */
public class Result extends HashMap<String,Object>
{
    /**
      * @Author:QiQuinn
      * @Desicription: 成功信息返回数据
      * @Date:Created in 2019/7/10 9:41
      * @param object 返回的数据
      * @Modified By:
      */
    public Map<String,Object> putSuccess(Object object)
    {
        this.put("ret",true);
        this.put("msg", MessageEnum.SUSSECC.getMsg());
        this.put("code", MessageEnum.SUSSECC.getCode());
        this.put("message"," ");
        this.put("data",object);
        return this;
    }
    /**
     * @Author:QiQuinn
     * @Desicription: 成功信息返回数据
     * @Date:Created in 2019/7/10 9:41
     * @param object 返回的数据
     * @Modified By:
     */
    public Map<String,Object> putSuccess(String msg,Object object)
    {
        this.put("ret",true);
        this.put("msg", MessageEnum.SUSSECC.getMsg());
        this.put("code", MessageEnum.SUSSECC.getCode());
        this.put("message",msg);
        this.put("data",object);
        return this;
    }
    /**
      * @Author:QiQuinn
      * @Desicription:  成功信息无返回数据
      * @Date:Created in 2019/7/10 9:42
      * @Modified By:
      */
    public Map<String,Object> putSuccess()
    {
        this.put("ret",true);
        this.put("msg", MessageEnum.SUSSECC.getMsg());
        this.put("code", MessageEnum.SUSSECC.getCode());
        this.put("message","");
        return this;
    }
    /**
     * @Author:QiQuinn
     * @Desicription:  成功信息无返回数据
     * @Date:Created in 2019/7/10 9:42
     * @Modified By:
     */
    public Map<String,Object> putSuccess(String msg)
    {
        this.put("ret",true);
        this.put("msg", MessageEnum.SUSSECC.getMsg());
        this.put("code", MessageEnum.SUSSECC.getCode());
        this.put("message",msg);
        return this;
    }

    /**
      * @Author:QiQuinn
      * @Desicription: 错误信息
      * @Date:Created in 2019/7/10 9:43
      * @param code    错误码
      * @param message 错误信息
      * @Modified By:
      */
    public Map<String,Object> putError(Integer code,String message)
    {
        this.put("ret",false);
        this.put("msg", "操作失败");
        this.put("code",code);
        this.put("message",message);
        return this;
    }

    /**
      * @Author:QiQuinn
      * @Desicription: 报错返回信息
      * @Date:Created in 2019/7/16 15:27
      * @param code 错误码
      * @param message 错误信息
      * @param ex  错误类型
      *@return java.util.Map<java.lang.String,java.lang.Object>
      * @Modified By:
      */
    public Map<String,Object> putErrorExption(Integer code,String message,Exception ex)
    {
        this.put("ret",false);
        this.put("msg", "操作失败");
        this.put("code",code);
        this.put("message",message);
        this.put("data",ex.toString());
        return this;
    }
}
