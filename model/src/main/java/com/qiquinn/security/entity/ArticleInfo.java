package com.qiquinn.security.entity;

import com.qiquinn.security.utils.verdifyparam.customeranotation.DateVertify;
import com.qiquinn.security.utils.verdifyparam.customeranotation.TrueOrFalse;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * create by QiQuinn
 */
public class ArticleInfo implements Serializable
{
    private Integer id;
    @NotEmpty(message = "title不能为空")
    private String title;
    @NotEmpty(message = "文章内容不能为空")
    private String content;
    //@NotEmpty只能用在string上，@NotNull可用
    @TrueOrFalse(message = "置顶参数不合法")
    @NotNull(message = "是否置顶不能为空")
    private Integer isHander;
    @NotNull(message = "所属栏目不能为空")
    private Integer columnId;
//  @Pattern(regexp = "((((19|20)\\d{2})-(0?(1|[3-9])|1[012])-(0?[1-9]|[12]\\d|30))|(((19|20)\\d{2})-(0?[13578]|1[02])-31)|(((19|20)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-29))$")
    @DateVertify(message = "日期参数不合法")
    private String createDate;
//  @Pattern(regexp = "((((19|20)\\d{2})-(0?(1|[3-9])|1[012])-(0?[1-9]|[12]\\d|30))|(((19|20)\\d{2})-(0?[13578]|1[02])-31)|(((19|20)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-29))$")
    @DateVertify(message = "日期参数不合法")
    private String updataDate;
    private Integer pageViews;

    @Override
    public String toString() {
        return "ArticleInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", isHeader=" + isHander +
                ", culomnId=" + columnId +
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
        return columnId;
    }

    public void setCulomnId(Integer culomnId) {
        this.columnId = culomnId;
    }
}
