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
@TableName("article_comment")
public class ArticleComment implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String commentContent;

    /**
     * 回复评论id
     */
    private String replyCommentId;

    private String commentUserId;

    /**
     * 支持（赞）
     */
    private Integer support;

    /**
     * 反对（踩）
     */
    private Integer oppose;

    private String commentUserName;

    /**
     * 用户头像地址
     */
    private String commentUserAvatar;

    /**
     * 评论时间
     */
    private String createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getReplyCommentId() {
        return replyCommentId;
    }

    public void setReplyCommentId(String replyCommentId) {
        this.replyCommentId = replyCommentId;
    }

    public String getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public Integer getSupport() {
        return support;
    }

    public void setSupport(Integer support) {
        this.support = support;
    }

    public Integer getOppose() {
        return oppose;
    }

    public void setOppose(Integer oppose) {
        this.oppose = oppose;
    }

    public String getCommentUserName() {
        return commentUserName;
    }

    public void setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
    }

    public String getCommentUserAvatar() {
        return commentUserAvatar;
    }

    public void setCommentUserAvatar(String commentUserAvatar) {
        this.commentUserAvatar = commentUserAvatar;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ArticleComment{" +
        "id=" + id +
        ", commentContent=" + commentContent +
        ", replyCommentId=" + replyCommentId +
        ", commentUserId=" + commentUserId +
        ", support=" + support +
        ", oppose=" + oppose +
        ", commentUserName=" + commentUserName +
        ", commentUserAvatar=" + commentUserAvatar +
        ", createTime=" + createTime +
        "}";
    }
}
