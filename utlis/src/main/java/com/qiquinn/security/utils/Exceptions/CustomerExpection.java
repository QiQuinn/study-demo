package com.qiquinn.security.utils.Exceptions;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/10
 * @Modified By:
 */
public class CustomerExpection extends RuntimeException {

    /* 报错的类 */
    private String errorClass;
    /* 报错代码*/
    private Integer code;

    private String function;

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public CustomerExpection(Integer code, String message, String function, Object errorClass)
    {
        super(message);
        this.errorClass = errorClass.getClass().getName();
        this.code = code;
        this.function = function;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorClass() {
        return errorClass;
    }

    public void setErrorClass(String errorClass) {
        this.errorClass = errorClass;
    }

    @Override
    public String toString() {
        return errorClass+"."+function+":"+getClass().getSimpleName()+": "+getLocalizedMessage();
    }
}
