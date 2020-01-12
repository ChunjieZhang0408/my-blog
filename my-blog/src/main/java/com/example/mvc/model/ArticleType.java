package com.example.mvc.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZhangChunjie
 * @since 2020-01-12
 */
@TableName("article_type")
public class ArticleType implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String name;

    private String description;

    private String createTime;

    private Integer status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ArticleType{" +
        "id=" + id +
        ", name=" + name +
        ", description=" + description +
        ", createTime=" + createTime +
        ", status=" + status +
        "}";
    }
}
