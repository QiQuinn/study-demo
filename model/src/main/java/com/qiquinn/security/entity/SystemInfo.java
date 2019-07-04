package com.qiquinn.security.entity;

import java.io.Serializable;

public class SystemInfo implements Serializable
{

    private Integer id;
    /**
     * 网站名称
     * */
    private String webName;
    /**
     * 网站logo
     * */
    private String webLogo;
    /**
     * ICP备案号
     * */
    private String icpCode;
    /**
     * 版权信息
     * */
    private String copyRight;
    /**
     * 关键词
     * */
    private String keyWords;
    /**
     * 描述
     * */
    private String discribe;
    /**
     * 客户注册短信验证码发送
     * */
    private String indentifyCode;

    public SystemInfo(){}

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebLogo(String webLogo) {
        this.webLogo = webLogo;
    }

    public String getWebLogo() {
        return webLogo;
    }

    public void setIcpCode(String icpCode) {
        this.icpCode = icpCode;
    }

    public String getIcpCode() {
        return icpCode;
    }

    public void setCopyRight(String copyRight) {
        this.copyRight = copyRight;
    }

    public String getCopyRight() {
        return copyRight;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setDiscribe(String discribe) {
        this.discribe = discribe;
    }

    public String getDiscribe() {
        return discribe;
    }

    public void setIndentifyCode(String indentifyCode) {
        this.indentifyCode = indentifyCode;
    }

    public String getIndentifyCode() {
        return indentifyCode;
    }

}
