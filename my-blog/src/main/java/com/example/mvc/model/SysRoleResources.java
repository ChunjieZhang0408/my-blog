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
@TableName("sys_role_resources")
public class SysRoleResources implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String roleId;

    private String resourcesId;

    /**
     * 添加时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(String resourcesId) {
        this.resourcesId = resourcesId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SysRoleResources{" +
        "id=" + id +
        ", roleId=" + roleId +
        ", resourcesId=" + resourcesId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
