package com.zut.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Date 2023-04-14 19:43 星期五
 * @Author: 聂建强
 * @Description:
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_role")
public class Role extends BaseEntity implements Serializable {

    /**
     * 主键ID
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 客户端编码
     */
    @TableField("client_code")
    private String clientCode;

    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;

    /**
     * 角色编码
     */
    @TableField("role_code")
    private String roleCode;


    /**
     * 排序
     */
    @TableField("role_sort")
    private String roleSort;


    /**
     * 角色状态：0-正常；1-停用
     */
    @TableField("status")
    private Integer status;


    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


}
