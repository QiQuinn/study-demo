package com.qiquinn.security.entity;


import java.io.Serializable;

public class ColumnInfo implements Serializable {
    /**
     * 栏目ID
     * */
    private Integer id;
    /**
     * 栏目名称
     * */
    private String name;
    /**
     * 父栏目ID
     * */
    private Integer parentId;
    
    public ColumnInfo(){}

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    
    public Integer getParentId() {
        return parentId;
    }

}