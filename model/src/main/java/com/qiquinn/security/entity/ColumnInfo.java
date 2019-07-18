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

    private static final long serialVersionUID = -2708653783151699375L;

    public ColumnInfo() {
    }

    public ColumnInfo(Integer id, String name, Integer parentId) {

        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}