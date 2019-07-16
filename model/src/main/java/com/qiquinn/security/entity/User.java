package com.qiquinn.security.entity;

import com.qiquinn.security.utils.verdifyparam.customeranotation.DateVertify;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/11
 * @Modified By:
 */

public class User implements Serializable
{
    private static final long serialVersionUID = -3210884885630038713L;
    private Integer id;
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "密码不能为空")
    private String password;
    private String nikename;
    private String sex;
    private String hibit;
    @DateVertify
    private String birthday;
    private Integer age;
    private String createDate;
    private String modifyDate;
    private String picUrl;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nikename='" + nikename + '\'' +
                ", sex='" + sex + '\'' +
                ", hibit='" + hibit + '\'' +
                ", birthday='" + birthday + '\'' +
                ", age=" + age +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHibit() {
        return hibit;
    }

    public void setHibit(String hibit) {
        this.hibit = hibit;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
