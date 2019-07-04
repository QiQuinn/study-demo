package com.qiquinn.security.entity;

import java.io.Serializable;

public class ArticleInfo implements Serializable
{
    private Integer id;
    private String title;
    private String content;
    private Integer isHander;
    private Integer culomnId;
    private String createDate;
    private String updataDate;
    private Integer pageViews;

    @Override
    public String toString() {
        return "ArticleInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", isHeader=" + isHander +
                ", culomnId=" + culomnId +
                ", createDate='" + createDate + '\'' +
                ", updataDate='" + updataDate + '\'' +
                ", pageViews=" + pageViews +
                '}';
    }

    public Integer getPageViews() {
        return pageViews;
    }

    public void setPageViews(Integer pageViews) {
        this.pageViews = pageViews;
    }

    public ArticleInfo() {
    }

    public String getUpdataDate() {
        return updataDate;
    }

    public void setUpdataDate(String updataDate) {
        this.updataDate = updataDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsHeader() {
        return isHander;
    }

    public void setIsHeader(Integer isHeader) {
        this.isHander = isHeader;
    }

    public Integer getCulomnId() {
        return culomnId;
    }

    public void setCulomnId(Integer culomnId) {
        this.culomnId = culomnId;
    }
}
