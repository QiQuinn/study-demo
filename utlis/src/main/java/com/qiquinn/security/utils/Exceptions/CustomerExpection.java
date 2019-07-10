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

    public CustomerExpection(Integer code,String message,String errorClass)
    {
        super(message);
        this.errorClass = errorClass;
        this.code = code;
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
        return this.getErrorClass()+": "+super.toString();
    }
}
