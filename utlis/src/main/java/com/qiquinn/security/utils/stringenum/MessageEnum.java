package com.qiquinn.security.utils.stringenum;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/10
 * @Modified By:
 */
public enum MessageEnum {
    UNKONW_EROOR(-1,"位置错误"),
    ERROR_PARAM(1001,"参数格式错误"),
    ERROR_AUTHORIZATION(1002,"应用授权码错误"),
    ERROR_AUTHORIZATION_UPDATA(1003,"应用授权码过期"),
    ERROR_AUTHORIZATION_NULL(1004,"应用未授权"),
    ERROR_USER_NULL(2001,"用户不存在"),
    ERROR_USER_PASSWORD_WRONG(2002,"密码错误"),
    EROOR_USER_PASSWORD_UPDATA(2003,"用户密码未修改"),
    ERROR_USER_ACTIVITY(2004,"用户未激活"),
    ERROR_USER_AUTHORIZATION(2005,"用户授权码错误"),
    EROOR_USER_REGIST(2006,"注册失败"),
    ERROR_SYSTEM_REQUEST(5001,"请求错误"),
    ERRPR_SYSTEM_DATABASE(5002,"数据库访问失败"),
    ERROR_RIDES_INCREASE(1100,"redis递增因子必须大于0"),
    ERROR_RIDES_DDR(1101,"redis递减因子必须大于0"),
    ERROR_REDIS_WRITE(1102,"redis写入错误"),
    ERROR_REDIS_READ(1103,"redis读取错误"),



    SUSSECC(0,"操作成功");


    private Integer code;
    private String msg;

    MessageEnum(Integer code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
